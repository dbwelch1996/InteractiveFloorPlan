package com.ECS160.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Sidebar extends JPanel {
    private FurnitureManager furnitureManager;
    private static final int GRID_COLS = 3; // Set the number of columns to 3

    public Sidebar(FurnitureManager furnitureManager) {
        this.furnitureManager = furnitureManager;
        setLayout(new GridLayout(0, GRID_COLS, 10, 10)); // Set the layout to grid with 3 columns
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(200, 600)); // Adjust the size as needed

        populateWithFurniture(); // Populate the sidebar with furniture items
    }

    private void populateWithFurniture() {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1); // Create a simple line border

        for (Furniture furniture : furnitureManager.getFurnitureList()) {
            ImageIcon icon = new ImageIcon(furniture.getImagePath());
            Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Scale the image
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel furnitureLabel = new JLabel(scaledIcon);
            furnitureLabel.setBackground(Color.WHITE); // Set background color to white
            furnitureLabel.setOpaque(true); // Make the label background visible
            furnitureLabel.setBorder(border); // Set the border for each furniture label
            furnitureLabel.setToolTipText(furniture.getName()); // Show furniture name on hover

            add(furnitureLabel);
        }
    }
}
