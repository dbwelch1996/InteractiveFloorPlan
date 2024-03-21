package com.ECS160.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Sidebar extends JPanel {
    private FurnitureManager furnitureManager;
    private PageSwapper pageSwapper;
    private List<JLabel> furnitureLabels;
    private MouseAdapter mouseAdapter;
    private boolean isDraggingEnabled = true;
    public Sidebar(FurnitureManager furnitureManager, PageSwapper pageSwapper) {
        this.furnitureManager = furnitureManager;
        setLayout(new GridLayout(0, 3, 10, 10));
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(200, 600));
        this.furnitureLabels = new ArrayList<>();
        this.pageSwapper = pageSwapper;

        populateWithFurniture();
    }

    private void populateWithFurniture() {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        FurnitureTransferHandler transferHandler = new FurnitureTransferHandler();

        mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isDraggingEnabled) {
                    JComponent c = (JComponent) e.getSource();
                    TransferHandler handler = c.getTransferHandler();
                    handler.exportAsDrag(c, e, TransferHandler.COPY);
                }
            }
        };

        for (Furniture furniture : furnitureManager.getFurnitureList()) {
            ImageIcon icon = new ImageIcon(furniture.getImagePath());
            Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            JLabel furnitureLabel = new JLabel(new ImageIcon(scaledImage));
            furnitureLabel.setBorder(border);
            furnitureLabel.setOpaque(true);
            furnitureLabel.setBackground(Color.WHITE);
            furnitureLabel.setToolTipText(furniture.getName());
            furnitureLabel.setTransferHandler(transferHandler);
            furnitureLabel.addMouseListener(mouseAdapter);
            furnitureLabel.putClientProperty("furniture", furniture);
            furnitureLabels.add(furnitureLabel);
            add(furnitureLabel);
        }
    }

    public void setEraseMode(boolean eraseMode) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor cursor;
        if (eraseMode) {
            Image cancelImage = toolkit.getImage("src/main/java/com/ECS160/UI/cancelIcon.png");
            cursor = toolkit.createCustomCursor(cancelImage, new Point(0, 0), "cancel");
            disableFurnitureInteraction();
            // Scale the image to a smaller size, e.g., 32x32 pixels
            Image scaledEraserImage = cancelImage.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            Cursor cancelCursor = toolkit.createCustomCursor(scaledEraserImage, new Point(0, 0), "eraser");
            setCursor(cancelCursor);
        } else {
            cursor = Cursor.getDefaultCursor();
            enableFurnitureInteraction();
            setCursor(cursor);
        }

    }

    private void disableFurnitureInteraction() {
        for (JLabel label : furnitureLabels) {
            label.setEnabled(false); // Disable each furniture label
        }
        this.isDraggingEnabled = false; // Update the dragging flag
    }

    private void enableFurnitureInteraction() {
        for (JLabel label : furnitureLabels) {
            label.setEnabled(true); // Enable each furniture label
        }
        this.isDraggingEnabled = true; // Update the dragging flag
    }
}
