package com.ECS160.UI;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Furniture implements Iterable<Furniture> {
    private String name;
    private String imagePath;

    public Furniture(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
    public String getName(){
        return name;
    }

    @Override
    public Iterator<Furniture> iterator() {
        List<Furniture> list = new ArrayList<>();
        list.add(this);
        return list.iterator();
    }
}
