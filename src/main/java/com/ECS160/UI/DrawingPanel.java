package com.ECS160.UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DnDConstants;
import java.awt.datatransfer.Transferable;

public class DrawingPanel extends JPanel {
    private List<Shape> shapes;
    private Shape currentShape;
    private boolean isGridView;
    private BufferedImage gridImage;
    private List<Furniture> placedFurniture;
    private FurnitureManager furnitureManager;
    private Furniture draggedFurniture;
    private Point lastMousePosition;
    private boolean eraseMode = false; // Flag to indicate erase mode

    public DrawingPanel(boolean isGridView) {
        this.isGridView = isGridView;
        setBackground(Color.WHITE);
        shapes = new ArrayList<>();
        furnitureManager = new FurnitureManager();
        placedFurniture = new ArrayList<>();
        setupMouseListeners();
        setupDropTarget();
        setFocusable(true);
        setupKeyListeners();
    }

    private void setupKeyListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (draggedFurniture != null) {
                    int rotationIncrement = 5;
                    double scaleIncrement = 0.1;
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            // Rotate counter-clockwise
                            draggedFurniture.setRotationAngle(draggedFurniture.getRotationAngle() - rotationIncrement);
                            break;
                        case KeyEvent.VK_RIGHT:
                            // Rotate clockwise
                            draggedFurniture.setRotationAngle(draggedFurniture.getRotationAngle() + rotationIncrement);
                            break;
                        case KeyEvent.VK_UP:
                            // Scale up
                            draggedFurniture.setScaleFactor(draggedFurniture.getScaleFactor() + scaleIncrement);
                            break;
                        case KeyEvent.VK_DOWN:
                            // Scale down
                            draggedFurniture.setScaleFactor(draggedFurniture.getScaleFactor() - scaleIncrement);
                            break;
                        case KeyEvent.VK_R:
                            // Add 90 then snap to the nearest 90 degree increment
                            int newAngle = (int)draggedFurniture.getRotationAngle() + 90;
                            // Normalize the angle between 0 and 359 degrees
                            newAngle %= 360;
                            // Snap to the nearest 90 degrees increment
                            int angleRemainder = newAngle % 90;
                            if (angleRemainder >= 45) {
                                newAngle += (90 - angleRemainder); // Snap up to the next increment
                            } else {
                                newAngle -= angleRemainder; // Snap down to the previous increment
                            }

                            draggedFurniture.setRotationAngle(newAngle);
                            break;
                        case KeyEvent.VK_BACK_SPACE:
                            // Remove the selected furniture item
                            placedFurniture.remove(draggedFurniture);
                            draggedFurniture = null; // Clear the reference to avoid re-deletion
                            repaint(); // Redraw to reflect the changes on the UI
                            break;
                    }
                    repaint();
                }
            }
        });
    }

    private void setupMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow(); // Request focus when the mouse is pressed

                if (eraseMode) {
                    // Determine if a furniture item or shape is clicked and remove it
                    Furniture toErase = getFurnitureAt(e.getPoint());
                    if (toErase != null) {
                        placedFurniture.remove(toErase);
                    } else {
                        // Create a small rectangle around the click point to detect shape intersection
                        Rectangle clickBounds = new Rectangle(e.getX() - 2, e.getY() - 2, 40, 40);
                        Shape toRemove = null;
                        for (Shape shape : shapes) {
                            // Check if the shape intersects the click bounds
                            if (shape.intersects(clickBounds)) {
                                toRemove = shape;
                                break;
                            }
                        }
                        if (toRemove != null) {
                            shapes.remove(toRemove);
                        }
                    }
                    repaint();
                } else {
                    // Handle normal behavior when not in erase mode
                    Furniture selected = getFurnitureAt(e.getPoint());
                    if (selected != null) {
                        draggedFurniture = selected;
                        lastMousePosition = e.getPoint();
                    } else {
                        currentShape = new Line2D.Double(e.getX(), e.getY(), e.getX(), e.getY());
                        shapes.add(currentShape);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!eraseMode) {
                    draggedFurniture = null;
                    lastMousePosition = null;
                    currentShape = null;
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!eraseMode && draggedFurniture != null && lastMousePosition != null) {
                    int dx = e.getX() - lastMousePosition.x;
                    int dy = e.getY() - lastMousePosition.y;
                    draggedFurniture.moveBy(dx, dy);
                    lastMousePosition = e.getPoint();
                    repaint();
                } else if (currentShape != null && !eraseMode) {
                    Line2D line = (Line2D) currentShape;
                    line.setLine(line.getX1(), line.getY1(), e.getX(), e.getY());
                    repaint();
                }
            }
        });
    }


    private Furniture getFurnitureAt(Point point) {
        for (Furniture furniture : placedFurniture) {
            Rectangle bounds = new Rectangle(furniture.getX(), furniture.getY(),
                    (int)(50 * furniture.getScaleFactor()), (int)(50 * furniture.getScaleFactor())); // Assuming 50x50 is the furniture size
            if (bounds.contains(point)) {
                return furniture;
            }
        }
        return null;
    }

    private void setupDropTarget() {
        setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                dtde.acceptDrop(DnDConstants.ACTION_COPY);
                if (processDrop(dtde)) {
                    dtde.dropComplete(true);
                } else {
                    dtde.dropComplete(false);
                }
                repaint();
            }

            private boolean processDrop(DropTargetDropEvent dtde) {
                Transferable transferable = dtde.getTransferable();
                if (transferable.isDataFlavorSupported(FurnitureTransferable.FURNITURE_FLAVOR)) {
                    try {
                        Furniture furniture = (Furniture) transferable.getTransferData(FurnitureTransferable.FURNITURE_FLAVOR);
                        Point dropPoint = dtde.getLocation();
                        Furniture newFurniture = new Furniture(furniture.getName(), furniture.getImagePath());
                        newFurniture.setPosition(dropPoint);
                        placedFurniture.add(newFurniture);
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                return false;
            }
        });
    }

    public void toggleGridView() {
        isGridView = !isGridView;
        if (isGridView) {
            createGridImage();
        } else {
            gridImage = null;
        }
        repaint();
    }
    public void saveDrawingPanel() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        paint(g2d);
        g2d.dispose();

        try {
            File outputFile = new File("saved_drawing.png");
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        shapes.clear();
        placedFurniture.clear();
        repaint();
    }

    private void createGridImage() {
        int gridSize = 25;
        gridImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = gridImage.createGraphics();
        g2d.setColor(Color.LIGHT_GRAY);

        for (int x = 0; x < getWidth(); x += gridSize) {
            g2d.drawLine(x, 0, x, getHeight());
        }

        for (int y = 0; y < getHeight(); y += gridSize) {
            g2d.drawLine(0, y, getWidth(), y);
        }

        g2d.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        float lineThickness = 2.5f;  // Example line thickness
        g2d.setStroke(new BasicStroke(lineThickness));

        if (isGridView && gridImage != null) {
            g2d.drawImage(gridImage, 0, 0, null);
        }

        for (Shape shape : shapes) {
            g2d.draw(shape);
        }

        for (Furniture furniture : placedFurniture) {
            Image image = furniture.getImage();
            if (image != null) {
                g2d = (Graphics2D) g.create();
                double rotationRadians = Math.toRadians(furniture.getRotationAngle());
                g2d.rotate(rotationRadians, furniture.getX() + 50 / 2, furniture.getY() + 50 / 2); // Assuming furniture size is 50x50
                g2d.drawImage(image, furniture.getX(), furniture.getY(),
                        (int)(50 * furniture.getScaleFactor()), (int)(50 * furniture.getScaleFactor()), this);
                g2d.dispose();
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 300);
    }


    public void setEraseMode(boolean eraseMode) {
        this.eraseMode = eraseMode;
        if (eraseMode) {
            // Change cursor to eraser icon
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image eraserImage = toolkit.getImage("src/main/java/com/ECS160/UI/eraser.png");

            // Scale the image to a smaller size, e.g., 32x32 pixels
            Image scaledEraserImage = eraserImage.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            Cursor eraserCursor = toolkit.createCustomCursor(scaledEraserImage, new Point(0, 0), "eraser");
            setCursor(eraserCursor);
        } else {
            // Revert cursor to default
            setCursor(Cursor.getDefaultCursor());
        }
    }
    public boolean isEraseMode() {
        return eraseMode;
    }


}
