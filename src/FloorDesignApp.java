import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

        // Create a scroll pane to contain the drawing panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(600, 400)); // Set preferred size for the visible area

        // Create the drawing panel
        drawingPanel = new DrawingPanel(false);
        drawingPanel.setPreferredSize(new Dimension(1000, 1000)); // Set size of the drawing canvas
        scrollPane.setViewportView(drawingPanel); // Set the drawing panel as the viewport of the scroll pane

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the sidebar
        centerPanel.add(new Sidebar(), BorderLayout.WEST);

        // Menu bar
        menuBar = new MenuBar();
        ActionListener listener = (e) -> {
            drawingPanel.toggleGridView();
        };
        menuBar.setGridViewToggleListener(listener);
        setJMenuBar(menuBar);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();

        // Set a minimum size for the frame
        setMinimumSize(new Dimension(800, 600));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FloorDesignApp app = new FloorDesignApp();
            app.setVisible(true);
        });
    }
}
