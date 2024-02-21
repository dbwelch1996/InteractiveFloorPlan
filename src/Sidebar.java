import javax.swing.*;

public class Sidebar extends JPanel {
    public Sidebar() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Example objects (you can replace these with your draggable objects)
        JButton bedButton = new JButton("Bed");
        JButton chairButton = new JButton("Chair");
        JButton tableButton = new JButton("Table");

        add(bedButton);
        add(chairButton);
        add(tableButton);
    }
}
