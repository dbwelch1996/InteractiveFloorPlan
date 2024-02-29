package com.ECS160.Apps;

import com.ECS160.UI.*;

import javax.swing.*;
import java.awt.*;


public class FloorDesignApp extends JFrame {

    private DrawingPanel drawingPanel;
    private TopMenuBar menuBar; 

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

        JTabbedPane tabbedPane = new JTabbedPane(); // Create a tabbed pane

        // Create multiple pages (tabs)
        for (int i = 1; i <= 3; i++) {
            JPanel pagePanel = createPage(); // Create a panel for each page
            tabbedPane.addTab("Page " + i, pagePanel); // Add the panel to the tabbed pane
        }



        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Center panel containing canvas and sidebar
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Create a scroll pane to contain the drawing panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(600, 400)); // Set preferred size for the visible area

        // Create the drawing panel
        drawingPanel = new DrawingPanel(false);
        drawingPanel.setPreferredSize(new Dimension(1000, 1000)); // Set size of the drawing canvas
        scrollPane.setViewportView(drawingPanel); // Set the drawing panel as the viewport of the scroll pane

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the sidebar
        centerPanel.add(new Sidebar(), BorderLayout.WEST);

        // Menu bar - Pass the DrawingPanel instance to the MenuBar constructor
        menuBar = new TopMenuBar(drawingPanel);
        setJMenuBar(menuBar);



        setContentPane(tabbedPane); // Set the tabbed pane as the content pane
        pack();

        // Set a minimum size for the frame
        setMinimumSize(new Dimension(800, 600));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Method to create a panel for each page
    private JPanel createPage() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Center panel containing canvas and sidebar
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Create a scroll pane to contain the drawing panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(600, 400)); // Set preferred size for the visible area

        // Create the drawing panel
        DrawingPanel drawingPanel = new DrawingPanel(false);
        drawingPanel.setPreferredSize(new Dimension(1000, 1000)); // Set size of the drawing canvas
        scrollPane.setViewportView(drawingPanel); // Set the drawing panel as the viewport of the scroll pane

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the sidebar
        centerPanel.add(new Sidebar(), BorderLayout.WEST);

        // Menu bar - Pass the DrawingPanel instance to the MenuBar constructor
        TopMenuBar menuBar = new TopMenuBar(drawingPanel);
        setJMenuBar(menuBar);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FloorDesignApp app = new FloorDesignApp();
            app.setVisible(true);
        });
    }
}
