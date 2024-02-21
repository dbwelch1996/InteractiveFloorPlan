import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel {

    public Sidebar() {
        setPreferredSize(new Dimension(150, 600));
        // Add your sidebar components here
        // Example:
        JButton bedButton = new JButton("Bed");
        JButton chairButton = new JButton("Chair");
        JButton tableButton = new JButton("Table");
        add(bedButton);
        add(chairButton);
        add(tableButton);
    }
}
