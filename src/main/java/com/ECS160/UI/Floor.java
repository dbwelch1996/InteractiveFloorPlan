package com.ECS160.UI;

import javax.swing.*;
import java.awt.*;

public class Floor extends JPanel {
    private String type;
    private Color color;

    public Floor(String type, Color color) {
        this.type = type;
        this.color = color;
        setBackground(color);
    }
}