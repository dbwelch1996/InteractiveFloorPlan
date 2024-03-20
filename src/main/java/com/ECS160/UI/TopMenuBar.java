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

        public TopMenuBar(PageSwapper pageSwapper) {
            this.pageSwapper = pageSwapper;

            // File Menu
            JMenu fileMenu = new JMenu("File");
            JMenuItem saveItem = new JMenuItem("Save");
            JMenuItem loadItem = new JMenuItem("Load");
            JMenuItem clearItem = new JMenuItem("Clear");
            JMenuItem exitItem = new JMenuItem("Exit");

            saveItem.addActionListener(e -> saveAction());
            loadItem.addActionListener(e -> System.out.println("Load action"));
            exitItem.addActionListener(e -> exitAction());
            clearItem.addActionListener(e -> clearAction()); // Set action for clearItem

            fileMenu.add(saveItem);
            fileMenu.add(loadItem);
            fileMenu.addSeparator();
            fileMenu.add(clearItem);
            fileMenu.addSeparator();
            fileMenu.add(exitItem);

            add(fileMenu);

            // Edit Menu
            JMenu editMenu = new JMenu("Edit");
            JMenuItem undoItem = new JMenuItem("Undo");
            JMenuItem redoItem = new JMenuItem("Redo");

            undoItem.addActionListener(e -> undoAction());
            redoItem.addActionListener(e -> redoAction());

            editMenu.add(undoItem);
            editMenu.add(redoItem);

            add(editMenu);

            // View Menu
            JMenu viewMenu = new JMenu("View");
            JMenuItem gridViewItem = new JMenuItem("Grid View");

            gridViewItem.addActionListener(e -> pageSwapper.toggleGridView()); // Directly call the pageSwapper's method

            viewMenu.add(gridViewItem);

            add(viewMenu);

            // Help Menu
            JMenu helpMenu = new JMenu("Help");
            JMenuItem aboutItem = new JMenuItem("About");

            aboutItem.addActionListener(e -> viewUserManual());

            helpMenu.add(aboutItem);

            add(helpMenu);
        }
        private void viewUserManual() {
            try {
                String pdfPath = "/Users/dwelch/Desktop/Code/School/ECS160/Final Projec/doc/User Manual.pdf"; // Specify the correct path to the PDF file
                PDDocument document = Loader.loadPDF(new File(pdfPath));
                PDFRenderer renderer = new PDFRenderer(document);

                // Render the first page of the PDF to an image (you can implement a PDF viewer for multiple pages)
                BufferedImage image = renderer.renderImage(0);

                // Display the image in a JLabel inside a JScrollPane
                JLabel label = new JLabel(new ImageIcon(image));
                JScrollPane scrollPane = new JScrollPane(label);
                scrollPane.setPreferredSize(new Dimension(600, 800));

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

        // Setter method to set the pageSwapper instance
        public void setPageSwapper(PageSwapper pageSwapper) {
            this.pageSwapper = pageSwapper;
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
            System.out.println("Save action");
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