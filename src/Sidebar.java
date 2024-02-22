// Sidebar.java
import javax.swing.*;
import java.awt.*;

public class Sidebar extends JLayeredPane {

    public Sidebar() {
        setPreferredSize(new Dimension(200, 600));

        // Create and customize sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(Color.LIGHT_GRAY);
        sidebarPanel.setPreferredSize(new Dimension(200, 600));
        sidebarPanel.setLayout(new GridLayout(0, 1, 0, 10)); // Vertical layout with 10px spacing between components

        // Create and add sidebar items
        JLabel item1 = new JLabel("Object 1");
        JLabel item2 = new JLabel("Object 2");
        JLabel item3 = new JLabel("Object 3");

        // Customize appearance of sidebar items (optional)
        item1.setForeground(Color.WHITE);
        item2.setForeground(Color.WHITE);
        item3.setForeground(Color.WHITE);

        sidebarPanel.add(item1);
        sidebarPanel.add(item2);
        sidebarPanel.add(item3);

        // Add sidebar panel to the layered pane
        add(sidebarPanel, JLayeredPane.DEFAULT_LAYER);
    }
}
