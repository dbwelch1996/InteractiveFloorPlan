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

        PageSwapper pageSwapper = new PageSwapper(furnitureManager);
        // Main panel that will hold both the sidebar and the tabbed pane
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Add the tabbed pane to the main panel
        mainPanel.add(pageSwapper, BorderLayout.CENTER);

        

        // Center panel containing canvas and sidebar
        JPanel centerPanel = new JPanel(new BorderLayout());


        // Create a scroll pane to contain the drawing panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(1500, 1000)); // Set preferred size for the visible area

        // Create the drawing panel
        drawingPanel = new DrawingPanel(false);
        drawingPanel.setPreferredSize(new Dimension(1500, 1500)); // Set size of the drawing canvas
        scrollPane.setViewportView(drawingPanel); // Set the drawing panel as the viewport of the scroll pane

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the sidebar with furniture

        Sidebar sidebar = new Sidebar(furnitureManager); // Pass the furnitureManager to Sidebar
        mainPanel.add(sidebar, BorderLayout.WEST);

        // Menu bar
        // Assuming you want a single menu bar for the application window and not individual ones for each tab
        menuBar = new TopMenuBar(pageSwapper); // Modify TopMenuBar to handle a possibly null DrawingPanel or update logic accordingly
        setJMenuBar(menuBar);

        setContentPane(mainPanel); // Set the main panel as the content pane of the frame
        pack();

        // Set a minimum size for the frame and other properties
        setMinimumSize(new Dimension(1100, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FloorDesignApp app = new FloorDesignApp();
            app.setVisible(true);
        });
    }
}
