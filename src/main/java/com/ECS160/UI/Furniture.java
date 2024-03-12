package com.ECS160.UI;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class Furniture {
    private String name;
    private String imagePath;
    private Point position; // Position of the furniture in the drawing area
    private double rotationAngle; // Stores the rotation angle in degrees

    private double scaleFactor = 1.0; // Default scale

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
    public void setRotationAngle(double angle) {
        this.rotationAngle = angle;
    }

    public double getRotationAngle() {
        return rotationAngle;
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
    public void moveBy(int dx, int dy) {
        position.translate(dx, dy); // Update the position
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

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    // Method to scale the image
    public Image getScaledImage() {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        int scaledWidth = (int)(image.getWidth(null) * scaleFactor);
        int scaledHeight = (int)(image.getHeight(null) * scaleFactor);
        return image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
    }
}


