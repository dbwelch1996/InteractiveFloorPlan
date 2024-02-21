import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private boolean isGridView;

    public DrawingPanel(boolean isGridView) {
        this.isGridView = isGridView;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isGridView) {
            // Draw grid lines
            g.setColor(Color.LIGHT_GRAY);
            for (int i = 0; i < getWidth(); i += 20) {
                g.drawLine(i, 0, i, getHeight());
            }
            for (int i = 0; i < getHeight(); i += 20) {
                g.drawLine(0, i, getWidth(), i);
            }
        }
    }

    public void setGridView(boolean isGridView) {
        this.isGridView = isGridView;
        repaint();
    }
}
