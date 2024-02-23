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

    // Getters and setters for type, color, texture, etc.
}
