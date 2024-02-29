package com.ECS160.UI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FurnitureManager {
    private List<Furniture> furnitureList;

    public FurnitureManager() {
        furnitureList = new ArrayList<>();
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    // Method to load furniture images from a directory
    public void loadFurnitureImages(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && isImageFile(file)) {
                    String name = extractNameWithoutExtension(file.getName());
                    String imagePath = file.getAbsolutePath();
                    furnitureList.add(new Furniture(name, imagePath));
                }
            }
        }
    }

    // Helper method to check if the file is an image
    private boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".gif");
    }

    // Helper method to extract the file name without extension
    private String extractNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }
}
