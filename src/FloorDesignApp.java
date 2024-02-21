import javax.swing.*;
import java.awt.*;

public class FloorDesignApp extends JFrame {
    private boolean isGridView = false;
    private DrawingPanel drawingPanel;

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
        JToolBar toolBar = new Toolbar();

        mainPanel.add(toolBar, BorderLayout.NORTH);

        // Center panel containing canvas and sidebar
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new Sidebar(), BorderLayout.WEST);

        drawingPanel = new DrawingPanel(isGridView);
        centerPanel.add(drawingPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FloorDesignApp().setVisible(true));
    }
}
