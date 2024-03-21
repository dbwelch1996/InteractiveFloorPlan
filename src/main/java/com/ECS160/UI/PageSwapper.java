package com.ECS160.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class PageSwapper extends JPanel {
    private JTabbedPane tabbedPane;

    private JPanel controlPanel;
    private FurnitureManager furnitureManager;
    private int pageCount;
    private int initialPageCount;

    private boolean eraseMode = false;

    public PageSwapper(FurnitureManager furnitureManager) {
        this.furnitureManager = furnitureManager;
        this.initialPageCount = 1;
        this.pageCount = initialPageCount; // Initialize page count
        setLayout(new BorderLayout());

        controlPanel = getControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        initializeTabs();
    }

    private void initializeTabs() {
        for (int i = 1; i <= initialPageCount; i++) {
            JPanel pagePanel = createPage(i); // Create a panel for each tab/page
            tabbedPane.addTab("Page " + i, pagePanel);
        }
        add(tabbedPane, BorderLayout.CENTER);
    }


    private JPanel createPage(int pageNumber) {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Example setup for a drawing panel in each tab
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(600, 400)); // Set preferred size

        DrawingPanel drawingPanel = new DrawingPanel(false);
        drawingPanel.setPreferredSize(new Dimension(1000,
                1000));
        drawingPanel.setEraseMode(eraseMode);
        scrollPane.setViewportView(drawingPanel);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // You can add more components specific to each page here

        return mainPanel;
    }

    public void addPage() {
        pageCount++;
        JPanel pagePanel = createPage(pageCount); // Modified to use pageCount
        tabbedPane.addTab("Page " + pageCount, pagePanel);
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1); // Switch to the new tab immediately
    }

    public void renameCurrentPage(String newTitle) {
        int selectedIndex = tabbedPane.getSelectedIndex();
        if (selectedIndex != -1) {
            tabbedPane.setTitleAt(selectedIndex, newTitle);
        }
    }

    public JPanel getControlPanel() {
        JPanel controlPanel = new JPanel(new BorderLayout());

        JButton addPageButton = new JButton("Add Page");
        addPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPage();
            }
        });
        controlPanel.add(addPageButton, BorderLayout.EAST);

        JButton renamePageButton = new JButton("Rename Current Page");
        renamePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newTitle = JOptionPane.showInputDialog(PageSwapper.this, "Enter new title:");
                if (newTitle != null && !newTitle.isEmpty()) {
                    renameCurrentPage(newTitle);
                }
            }
        });
        controlPanel.add(renamePageButton, BorderLayout.WEST);

        return controlPanel;
    }

    private DrawingPanel getCurrentDrawingPanel() {
        int selectedIndex = tabbedPane.getSelectedIndex();
        if (selectedIndex != -1) {
            JPanel mainPanel = (JPanel) tabbedPane.getComponentAt(selectedIndex);
            JScrollPane scrollPane = (JScrollPane) mainPanel.getComponent(0);
            return (DrawingPanel) scrollPane.getViewport().getView();
        }
        return null;
    }

    public void clear() {
        getCurrentDrawingPanel().clear();
    }

    public void toggleGridView() {
        getCurrentDrawingPanel().toggleGridView();
    }

    public void toggleEraseMode() {
        eraseMode = !eraseMode;
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            JPanel mainPanel = (JPanel) tabbedPane.getComponentAt(i);
            JScrollPane scrollPane = (JScrollPane) mainPanel.getComponent(0);
            DrawingPanel drawingPanel = (DrawingPanel) scrollPane.getViewport().getView();
            drawingPanel.setEraseMode(eraseMode);
        }
    }

    public boolean getEraseMode() {
        return eraseMode;
    }


}
