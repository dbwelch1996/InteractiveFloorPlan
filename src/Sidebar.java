import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sidebar extends JPanel {
    private int offsetX, offsetY;

    public Sidebar() {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(200, 600)); // Adjust the size as needed

        // Add mouse listeners to handle drag events
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                offsetX = e.getX();
                offsetY = e.getY();
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int newX = e.getXOnScreen() - offsetX;
                int newY = e.getYOnScreen() - offsetY;
                setLocation(newX, newY);
            }
        });
    }
}
