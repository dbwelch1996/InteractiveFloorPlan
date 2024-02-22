// MenuBar.java
import javax.swing.*;

public class MenuBar extends JMenuBar {

    private Runnable gridViewToggleListener;

    public MenuBar() {
        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem exitItem = new JMenuItem("Exit");

        saveItem.addActionListener(e -> saveAction());
        loadItem.addActionListener(e -> System.out.println("Load action"));
        exitItem.addActionListener(e -> exitAction());

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        add(fileMenu);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem undoItem = new JMenuItem("Undo");
        JMenuItem redoItem = new JMenuItem("Redo");

        undoItem.addActionListener(e -> undoAction());
        redoItem.addActionListener(e -> redoAction());

        editMenu.add(undoItem);
        editMenu.add(redoItem);

        add(editMenu);

        // View Menu
        JMenu viewMenu = new JMenu("View");
        JMenuItem gridViewItem = new JMenuItem("Grid View");

        gridViewItem.addActionListener(e -> toggleGridView());

        viewMenu.add(gridViewItem);

        add(viewMenu);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");

        aboutItem.addActionListener(e -> System.out.println("About action"));

        helpMenu.add(aboutItem);

        add(helpMenu);
    }

    // Method to toggle grid view
    private void toggleGridView() {
        if (gridViewToggleListener != null) {
            gridViewToggleListener.run();
        }
    }

    // Method to set the listener for grid view toggle
    public void setGridViewListener(Runnable listener) {
        this.gridViewToggleListener = listener;
    }

    private void saveAction() {
        System.out.println("Save action");
    }

    private void exitAction() {
        System.out.println("Exit action");
        System.exit(0);
    }

    private void undoAction() {
        System.out.println("Undo action");
    }

    private void redoAction() {
        System.out.println("Redo action");
    }
}
