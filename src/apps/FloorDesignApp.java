import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FloorDesignApp extends JFrame {

    private boolean isGridView = false;
    private int gridSize = 20;

    public FloorDesignApp() {
        super("Interactive Floor Design");
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Top toolbar
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton fileButton = new JButton("File");
        JButton editButton = new JButton("Edit");
        JButton viewButton = new JButton("View");
        JButton helpButton = new JButton("Help");

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

        // Edit menu
        JPopupMenu editMenu = new JPopupMenu();
        JMenuItem undoItem = new JMenuItem("Undo");
        JMenuItem redoItem = new JMenuItem("Redo");
        editMenu.add(undoItem);
        editMenu.add(redoItem);

        editButton.addActionListener(e -> editMenu.show(editButton, 0, editButton.getHeight()));

        // View menu
        JPopupMenu viewMenu = new JPopupMenu();
        JMenuItem gridViewItem = new JMenuItem("Grid View");
        viewMenu.add(gridViewItem);

        viewButton.addActionListener(e -> viewMenu.show(viewButton, 0, viewButton.getHeight()));

        // Help menu
        JPopupMenu helpMenu = new JPopupMenu();
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        helpButton.addActionListener(e -> helpMenu.show(helpButton, 0, helpButton.getHeight()));

        toolBar.add(fileButton);
        toolBar.addSeparator();
        toolBar.add(editButton);
        toolBar.addSeparator();
        toolBar.add(viewButton);
        toolBar.addSeparator();
        toolBar.add(helpButton);

        mainPanel.add(toolBar, BorderLayout.NORTH);

        // Left sidebar with draggable objects
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));

        // Example objects (you can replace these with your draggable objects)
        JButton bedButton = new JButton("Bed");
        JButton chairButton = new JButton("Chair");
        JButton tableButton = new JButton("Table");

        sidebarPanel.add(bedButton);
        sidebarPanel.add(chairButton);
        sidebarPanel.add(tableButton);

        // Scroll pane for the sidebar
        JScrollPane sidebarScrollPane = new JScrollPane(sidebarPanel);
        sidebarScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sidebarScrollPane.setPreferredSize(new Dimension(150, 600));

        mainPanel.add(sidebarScrollPane, BorderLayout.WEST);

        // Drawing panel
        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (isGridView) {
                    // Draw grid lines
                    g.setColor(Color.LIGHT_GRAY);
                    for (int i = 0; i < getWidth(); i += gridSize) {
                        g.drawLine(i, 0, i, getHeight());
                    }
                    for (int i = 0; i < getHeight(); i += gridSize) {
                        g.drawLine(0, i, getWidth(), i);
                    }
                }
            }
        };
        drawingPanel.setPreferredSize(new Dimension(800, 600));
        drawingPanel.setBackground(Color.WHITE);

        mainPanel.add(drawingPanel, BorderLayout.CENTER);

        // Menu actions
        saveItem.addActionListener(e -> System.out.println("Save action"));
        loadItem.addActionListener(e -> System.out.println("Load action"));
        exitItem.addActionListener(e -> System.out.println("Exit action"));

        undoItem.addActionListener(e -> System.out.println("Undo action"));
        redoItem.addActionListener(e -> System.out.println("Redo action"));

        gridViewItem.addActionListener(e -> {
            isGridView = !isGridView;
            drawingPanel.repaint();
        });

        aboutItem.addActionListener(e -> System.out.println("About action"));

        setContentPane(mainPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FloorDesignApp().setVisible(true));
    }
}
