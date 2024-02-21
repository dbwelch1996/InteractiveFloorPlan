import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JToolBar {
    private JButton fileButton;
    private JButton editButton;
    private JButton viewButton;
    private JButton helpButton;
    private JMenuItem gridViewItem;

    public Toolbar() {
        fileButton = new JButton("File");
        editButton = new JButton("Edit");
        viewButton = new JButton("View");
        helpButton = new JButton("Help");

        // File menu
        JPopupMenu fileMenu = new JPopupMenu();
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        fileButton.addActionListener(e -> fileMenu.show(fileButton, 0, fileButton.getHeight()));

        // View menu
        JPopupMenu viewMenu = new JPopupMenu();
        gridViewItem = new JMenuItem("Grid View");
        viewMenu.add(gridViewItem);

        viewButton.addActionListener(e -> viewMenu.show(viewButton, 0, viewButton.getHeight()));

        // Menu actions
        saveItem.addActionListener(e -> System.out.println("Save action"));
        exitItem.addActionListener(e -> System.out.println("Exit action"));

        add(fileButton);
        addSeparator();
        add(editButton);
        addSeparator();
        add(viewButton);
        addSeparator();
        add(helpButton);
    }
}
