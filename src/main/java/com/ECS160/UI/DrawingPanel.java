package com.ECS160.UI;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;

import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {
    private List<Shape> shapes;
    private Shape currentShape;
    private boolean isGridView;
    private BufferedImage gridImage;
    private List<Furniture> placedFurniture;
    private FurnitureManager furnitureManager;
    private Furniture draggedFurniture;
    private Point lastMousePosition;

    public DrawingPanel(boolean isGridView) {
        this.isGridView = isGridView;
        setBackground(Color.WHITE);
        shapes = new ArrayList<>();
        furnitureManager = new FurnitureManager();
        placedFurniture = new ArrayList<>();

        setupMouseListeners();
        setupDropTarget();
    }

    private void setupMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentShape = new Line2D.Double(e.getX(), e.getY(), e.getX(), e.getY());
                shapes.add(currentShape);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentShape = null;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (currentShape != null) {
                    Line2D line = (Line2D) currentShape;
                    line.setLine(line.getX1(), line.getY1(), e.getX(), e.getY());
                    repaint();
                }
            }
        });
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
                        furniture.setPosition(dropPoint);
                        furnitureManager.getFurnitureList().add(furniture);
                        placedFurniture.add(furniture);
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

    public void clear() {
        shapes.clear();
        placedFurniture.clear();
        repaint();
    }

    private void createGridImage() {
        int gridSize = 20;
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

        if (isGridView && gridImage != null) {
            g2d.drawImage(gridImage, 0, 0, null);
        }

        for (Shape shape : shapes) {
            g2d.draw(shape);
        }

        int furnitureWidth = 50; // Set the desired width
        int furnitureHeight = 50; // Set the desired height

        for (Furniture furniture : placedFurniture) {
            Image image = furniture.getImage();
            if (image != null) {
                // Scale and draw the image
                g2d.drawImage(image, furniture.getX(), furniture.getY(), furnitureWidth, furnitureHeight, this);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 300);
    }
}
