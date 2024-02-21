import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {

    private boolean isGridView;

    public DrawingPanel(boolean isGridView) {
        this.isGridView = isGridView;
    }

    public void setGridView(boolean isGridView) {
        this.isGridView = isGridView;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isGridView) {
            // Draw grid lines
            g.setColor(Color.LIGHT_GRAY);
            int gridSize = 20;
            for (int i = 0; i < getWidth(); i += gridSize) {
                g.drawLine(i, 0, i, getHeight());
            }
            for (int i = 0; i < getHeight(); i += gridSize) {
                g.drawLine(0, i, getWidth(), i);
            }
        }
    }
}
