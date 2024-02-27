package com.ECS160.UI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TopMenuBar extends JMenuBar {

    private ActionListener gridViewToggleListener;
    private DrawingPanel drawingPanel;

    public TopMenuBar(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem clearItem = new JMenuItem("Clear");
        JMenuItem exitItem = new JMenuItem("Exit");

        saveItem.addActionListener(e -> saveAction());
        loadItem.addActionListener(e -> System.out.println("Load action"));
        exitItem.addActionListener(e -> exitAction());
        clearItem.addActionListener(e -> clearAction()); // Set action for clearItem

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(clearItem);
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

        gridViewItem.addActionListener(e -> drawingPanel.toggleGridView()); // Directly call the DrawingPanel's method

        viewMenu.add(gridViewItem);

        add(viewMenu);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");

        aboutItem.addActionListener(e -> System.out.println("About action"));

        helpMenu.add(aboutItem);

        add(helpMenu);
    }

    // Setter method to set the DrawingPanel instance
    public void setDrawingPanel(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    // Method to trigger grid view toggle action
    public void toggleGridView() {
        if (drawingPanel != null) {
            drawingPanel.toggleGridView();
        }
    }


    // Method to clear the drawing panel
    private void clearAction() {
        if (drawingPanel != null) {
            drawingPanel.clear();
        }
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