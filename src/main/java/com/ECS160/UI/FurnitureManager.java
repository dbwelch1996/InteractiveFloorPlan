package com.ECS160.UI;

import com.ECS160.UI.*;

import java.util.ArrayList;
import java.util.List;

public class FurnitureManager {
    private List<Furniture> furnitureList;

    public FurnitureManager() {
        furnitureList = new ArrayList<>();
        // Add furniture items here or load them from a data source
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    public void addFurniture(Furniture furniture) {
        furnitureList.add(furniture);
    }
}
