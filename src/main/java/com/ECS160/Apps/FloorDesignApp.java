package com.ECS160.Apps;

import com.ECS160.UI.*;

import javax.swing.*;
import java.awt.*;

public class FloorDesignApp extends JFrame {

    private DrawingPanel drawingPanel;
    private TopMenuBar menuBar;
    private FurnitureManager furnitureManager; // Add FurnitureManager

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

        // Initialize the FurnitureManager and load furniture images
        furnitureManager = new FurnitureManager();

        furnitureManager.loadFurnitureImages("src/main/java/com/ECS160/Icons"); // Provide the correct path


        JTabbedPane tabbedPane = new JTabbedPane(); // Create a tabbed pane

        // Create multiple pages (tabs)
        for (int i = 1; i <= 3; i++) {
            JPanel pagePanel = createPage(); // Create a panel for each page
            tabbedPane.addTab("Page " + i, pagePanel); // Add the panel to the tabbed pane
        }

        // Main panel that will hold both the sidebar and the tabbed pane
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Add the tabbed pane to the main panel
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Center panel containing canvas and sidebar
        JPanel centerPanel = new JPanel(new BorderLayout());


        // Create a scroll pane to contain the drawing panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(1100, 800)); // Set preferred size for the visible area

        // Create the drawing panel
        drawingPanel = new DrawingPanel(false);
        drawingPanel.setPreferredSize(new Dimension(1000, 1000)); // Set size of the drawing canvas
        scrollPane.setViewportView(drawingPanel); // Set the drawing panel as the viewport of the scroll pane

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the sidebar with furniture

        Sidebar sidebar = new Sidebar(furnitureManager); // Pass the furnitureManager to Sidebar
        mainPanel.add(sidebar, BorderLayout.WEST);

        // Menu bar
        // Assuming you want a single menu bar for the application window and not individual ones for each tab
        menuBar = new TopMenuBar(null); // Modify TopMenuBar to handle a possibly null DrawingPanel or update logic accordingly
        setJMenuBar(menuBar);

        setContentPane(mainPanel); // Set the main panel as the content pane of the frame
        pack();

        // Set a minimum size for the frame and other properties
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    // Method to create a panel for each page without its own sidebar
    private JPanel createPage() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Scroll pane to contain the drawing panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(600, 400)); // Set preferred size for the visible area

        // Drawing panel
        DrawingPanel drawingPanel = new DrawingPanel(false);
        drawingPanel.setPreferredSize(new Dimension(1000, 1000)); // Set size of the drawing canvas
        scrollPane.setViewportView(drawingPanel); // Add the drawing panel to the scroll pane

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FloorDesignApp app = new FloorDesignApp();
            app.setVisible(true);
        });
    }
}
