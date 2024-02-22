// DrawingPanel.java
import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {

    private boolean isGridView;
    private GridViewListener gridViewListener;

    public DrawingPanel(boolean isGridView) {
        this.isGridView = isGridView;
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isGridView) {
            // Draw grid lines
            drawGrid(g);
        }
        // Add any custom drawing logic here
    }

    private void drawGrid(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);

        // Draw vertical lines
        for (int x = 0; x < getWidth(); x += 20) {
            g2d.drawLine(x, 0, x, getHeight());
        }

        // Draw horizontal lines
        for (int y = 0; y < getHeight(); y += 20) {
            g2d.drawLine(0, y, getWidth(), y);
        }
    }

    public void setGridView(boolean isGridView) {
        this.isGridView = isGridView;
        repaint();
    }

    // Method to set GridViewListener
    public void setGridViewListener(GridViewListener listener) {
        this.gridViewListener = listener;
    }

    // Method to toggle grid view
    public void toggleGridView() {
        isGridView = !isGridView;
        if (gridViewListener != null) {
            gridViewListener.gridViewToggled(isGridView);
        }
        repaint();
    }
}
