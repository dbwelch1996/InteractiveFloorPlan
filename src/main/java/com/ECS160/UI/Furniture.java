package com.ECS160.UI;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class Furniture {
    private String name;
    private String imagePath;
    private Point position; // Position of the furniture in the drawing area

    public Furniture(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
        this.position = new Point(0, 0); // Default position
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    // Method to get the image
    public Image getImage() {
        ImageIcon icon = new ImageIcon(imagePath);
        return icon.getImage();
    }

    // Methods to get x and y coordinates
    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }
}
