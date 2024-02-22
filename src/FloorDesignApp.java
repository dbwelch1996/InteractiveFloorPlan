// FloorDesignApp.java
import javax.swing.*;
import java.awt.*;

public class FloorDesignApp extends JFrame {

    private DrawingPanel drawingPanel;
    private MenuBar menuBar;

    public FloorDesignApp() {
        super("Interactive Floor Design");
        initUI();
    }

    private void initUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Center panel containing canvas and sidebar
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new Sidebar(), BorderLayout.WEST);

        drawingPanel = new DrawingPanel(false); // Initialize drawingPanel
        centerPanel.add(drawingPanel, BorderLayout.CENTER);

        // Menu bar
        menuBar = new MenuBar();
        menuBar.setGridViewListener(this::toggleGridView); // Set the toggleGridView method as listener
        setJMenuBar(menuBar);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();

        // Set a minimum size for the frame
        setMinimumSize(new Dimension(800, 600));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Method to toggle grid view
    private void toggleGridView() {
        drawingPanel.toggleGridView(); // Call toggleGridView method in DrawingPanel
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FloorDesignApp app = new FloorDesignApp();
            app.setVisible(true);
        });
    }
}
