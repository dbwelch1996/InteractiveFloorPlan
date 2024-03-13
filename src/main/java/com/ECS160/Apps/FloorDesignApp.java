package com.ECS160.Apps;

import com.ECS160.UI.*;

import javax.swing.*;
import java.awt.*;

public class FloorDesignApp extends JFrame {
    private JTabbedPane tabbedPane;
    private TopMenuBar menuBar;
    private FurnitureManager furnitureManager;

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

        furnitureManager = new FurnitureManager();
        // Assuming furnitureManager.loadFurnitureImages(String path) is implemented
        furnitureManager.loadFurnitureImages("src/main/java/com/ECS160/Icons");

        tabbedPane = new JTabbedPane();

        // Create multiple pages (tabs) with a DrawingPanel in each
        for (int i = 1; i <= 3; i++) {
            JPanel pagePanel = createPage();
            tabbedPane.addTab("Floor " + i, pagePanel);
        }

        // Initialize the menu bar
        menuBar = new TopMenuBar(null); // Assuming null is a valid initial value
        setJMenuBar(menuBar);

        // Correctly set up the TopMenuBar with the initially selected panel
        setupMenuBarWithInitialPanel();

        // Add the sidebar
        Sidebar sidebar = new Sidebar(furnitureManager);
        getContentPane().add(sidebar, BorderLayout.WEST);

        // Add the tabbedPane to the main content pane
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        pack();
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupMenuBarWithInitialPanel() {
        if (menuBar != null && tabbedPane.getComponentCount() > 0) {
            Component comp = ((JScrollPane) ((JPanel) tabbedPane.getComponentAt(0)).getComponent(0)).getViewport().getView();
            if (comp instanceof DrawingPanel) {
                menuBar.setDrawingPanel((DrawingPanel) comp);
            }
        }
    }


    private JPanel createPage() {
        JPanel panel = new JPanel(new BorderLayout());

        // Create a scroll pane with a drawing panel inside
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(600, 400));

        DrawingPanel drawingPanel = new DrawingPanel(false);
        drawingPanel.setPreferredSize(new Dimension(1000, 1000));
        scrollPane.setViewportView(drawingPanel);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        // Create the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new FloorDesignApp().setVisible(true));
    }
}