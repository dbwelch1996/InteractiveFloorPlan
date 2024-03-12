package com.ECS160.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sidebar extends JPanel {
    private FurnitureManager furnitureManager;

    public Sidebar(FurnitureManager furnitureManager) {
        this.furnitureManager = furnitureManager;
        setLayout(new GridLayout(0, 3, 10, 10));
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(200, 600));

        populateWithFurniture();
    }

    private void populateWithFurniture() {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        FurnitureTransferHandler transferHandler = new FurnitureTransferHandler();

        for (Furniture furniture : furnitureManager.getFurnitureList()) {
            ImageIcon icon = new ImageIcon(furniture.getImagePath());
            Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            JLabel furnitureLabel = new JLabel(new ImageIcon(scaledImage));
            furnitureLabel.setBorder(border);
            furnitureLabel.setOpaque(true);
            furnitureLabel.setBackground(Color.WHITE);
            furnitureLabel.setToolTipText(furniture.getName());
            furnitureLabel.setTransferHandler(transferHandler);

            // Associate the furniture with the label
            furnitureLabel.putClientProperty("furniture", furniture);

            furnitureLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JComponent c = (JComponent) e.getSource();
                    TransferHandler handler = c.getTransferHandler();
                    handler.exportAsDrag(c, e, TransferHandler.COPY);
                }
            });

            add(furnitureLabel);
        }
    }
}
