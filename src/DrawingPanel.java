import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {

    private List<Shape> shapes;
    private Shape currentShape;
    private boolean isGridView;

    public DrawingPanel(boolean isGridView) {
        this.isGridView = isGridView;
        setBackground(Color.WHITE);
        shapes = new ArrayList<>();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentShape = new Line2D.Double(e.getX(), e.getY(), e.getX(), e.getY());
                shapes.add(currentShape);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
           @Override
public void mouseDragged(MouseEvent e) {
    if (currentShape != null) {
        int x1 = (int) ((Line2D) currentShape).getX1();
        int y1 = (int) ((Line2D) currentShape).getY1();
        int x2 = e.getX();
        int y2 = e.getY();

        ((Line2D) currentShape).setLine(x1, y1, x2, y2);
        repaint();
    }
}
        });        

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                currentShape = null;
            }
        });
    }

    public void toggleGridView() {
        isGridView = !isGridView;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set the stroke width
        int strokeWidth = 3; // Adjust this value as needed
        g2d.setStroke(new BasicStroke(strokeWidth));

        // Draw stored shapes
        for (Shape shape : shapes) {
            g2d.draw(shape);
        }

        // Draw grid if enabled
        if (isGridView) {
            int gridSize = 20; // Define grid size
            g2d.setColor(Color.LIGHT_GRAY);
            for (int x = 0; x < getWidth(); x += gridSize) {
                g2d.drawLine(x, 0, x, getHeight());
            }
            for (int y = 0; y < getHeight(); y += gridSize) {
                g2d.drawLine(0, y, getWidth(), y);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 300);
    }
}
