import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FloorDesignApp extends JFrame {

    private boolean isGridView = false;
    private DrawingPanel drawingPanel;

    public FloorDesignApp() {
        super("Interactive Floor Design");
        initUI();
    }

    private void initUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();

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

        menuBar.add(fileMenu);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem undoItem = new JMenuItem("Undo");
        JMenuItem redoItem = new JMenuItem("Redo");

        undoItem.addActionListener(e -> undoAction());
        redoItem.addActionListener(e -> redoAction());

        editMenu.add(undoItem);
        editMenu.add(redoItem);

        menuBar.add(editMenu);

        // View Menu
        JMenu viewMenu = new JMenu("View");
        JMenuItem gridViewItem = new JMenuItem("Grid View");

        gridViewItem.addActionListener(e -> {
            isGridView = !isGridView;
            drawingPanel.setGridView(isGridView);
        });

        viewMenu.add(gridViewItem);

        menuBar.add(viewMenu);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");

        aboutItem.addActionListener(e -> System.out.println("About action"));

        helpMenu.add(aboutItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        // Center panel containing canvas and sidebar
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new Sidebar(), BorderLayout.WEST);

        drawingPanel = new DrawingPanel(isGridView);
        centerPanel.add(drawingPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Placeholder for other methods
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FloorDesignApp app = new FloorDesignApp();
            app.setVisible(true);
        });
    }
}
