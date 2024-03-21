    package com.ECS160.UI;

    import org.apache.pdfbox.Loader;
    import org.apache.pdfbox.pdmodel.PDDocument;
    import org.apache.pdfbox.rendering.PDFRenderer;
    import javax.imageio.ImageIO;
    import javax.swing.*;
    import java.awt.*;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.IOException;
    import javax.swing.*;
    import java.awt.event.ActionListener;

    public class TopMenuBar extends JMenuBar {

        private ActionListener gridViewToggleListener;
        private PageSwapper pageSwapper;
        private DrawingPanel drawingPanel; // Reference to the DrawingPanel
        private JCheckBoxMenuItem eraseModeItem; // Checkable menu item for erase mode
        private Sidebar sidebar;

        public TopMenuBar(PageSwapper pageSwapper, DrawingPanel drawingPanel, Sidebar sidebar) {
            this.pageSwapper = pageSwapper;
            this.drawingPanel = drawingPanel; // Set the DrawingPanel
            this.sidebar = sidebar;

            // File Menu
            JMenu fileMenu = new JMenu("File");
            JMenuItem saveItem = new JMenuItem("Save");

            JMenuItem clearItem = new JMenuItem("Clear");
            JMenuItem exitItem = new JMenuItem("Exit");

            saveItem.addActionListener(e -> saveAction());
            exitItem.addActionListener(e -> exitAction());
            clearItem.addActionListener(e -> clearAction()); // Set action for clearItem

            fileMenu.add(saveItem);
            fileMenu.addSeparator();
            fileMenu.add(clearItem);
            fileMenu.addSeparator();
            fileMenu.add(exitItem);

            add(fileMenu);

            // Edit Menu
            JMenu editMenu = new JMenu("Edit");

            eraseModeItem = new JCheckBoxMenuItem("Erase Mode");
            eraseModeItem.addActionListener(e -> this.toggleEraseMode());
            editMenu.add(eraseModeItem);

            add(editMenu);

            // View Menu
            JMenu viewMenu = new JMenu("View");
            JMenuItem gridViewItem = new JMenuItem("Grid View");
            gridViewItem.addActionListener(e -> pageSwapper.toggleGridView()); // Directly call the pageSwapper's method
            viewMenu.add(gridViewItem);
            add(viewMenu);

            JMenu helpMenu = new JMenu("Help");
            JMenuItem userManualItem = new JMenuItem("User Manual");
            userManualItem.addActionListener(e -> viewUserManual());
            helpMenu.add(userManualItem);
            add(helpMenu);
        }

        private void toggleEraseMode() {
            if (pageSwapper != null) {
                pageSwapper.toggleEraseMode();
                eraseModeItem.setSelected(pageSwapper.getEraseMode()); // Update the check state to reflect the current mode
                sidebar.setEraseMode(pageSwapper.getEraseMode());
            }
        }
        private void viewUserManual() {
            try {
                String pdfPath = "doc/User Manual.pdf";
                PDDocument document = Loader.loadPDF(new File(pdfPath));
                PDFRenderer renderer = new PDFRenderer(document);

                // Panel to hold all the pages
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                // Render each page and add to the panel
                for (int i = 0; i < document.getNumberOfPages(); i++) {
                    BufferedImage image = renderer.renderImage(i);
                    JLabel label = new JLabel(new ImageIcon(image));
                    panel.add(label);
                }

                // Scroll pane to allow scrolling through the pages
                JScrollPane scrollPane = new JScrollPane(panel);
                scrollPane.setPreferredSize(new Dimension(600, 1000));

                // Create a JFrame to display the PDF
                JFrame frame = new JFrame("User Manual");
                frame.add(scrollPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                document.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                // Handle the exception as needed
            }
        }


        // Method to trigger grid view toggle action
        public void toggleGridView() {
            if (pageSwapper != null) {
                pageSwapper.toggleGridView();
            }
        }


        // Method to clear the drawing panel
        private void clearAction() {
            if (pageSwapper != null) {
                pageSwapper.clear();
            }
        }

        private void saveAction() {
            if (drawingPanel != null) {
                drawingPanel.saveDrawingPanel(); // Call the method to save the drawing panel
            }
        }

        private void exitAction() {
            System.out.println("Exit action");
            System.exit(0);
        }

        private void undoAction() {
            System.out.println("Undo action");
        }

        private void redoAction() {
            System.out.println("Redo action");
        }
    }