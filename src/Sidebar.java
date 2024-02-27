import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sidebar extends JPanel {
    private int offsetX, offsetY;
    private Cursor dragCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

    public Sidebar() {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(200, 600)); // Adjust the size as needed

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                offsetX = e.getX();
                offsetY = e.getY();
                setCursor(dragCursor); // Change cursor when dragging starts
            }

            public void mouseReleased(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor()); // Restore default cursor when dragging ends
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point newLocation = getLocation();
                newLocation.translate(e.getX() - offsetX, e.getY() - offsetY);
                
                // Clamp movement within the bounds of the parent container (e.g., JFrame)
                Container parent = getParent();
                int maxX = parent.getWidth() - getWidth();
                int maxY = parent.getHeight() - getHeight();
                newLocation.x = Math.max(0, Math.min(newLocation.x, maxX));
                newLocation.y = Math.max(0, Math.min(newLocation.y, maxY));
                
                setLocation(newLocation);
            }
        });
    }
}
