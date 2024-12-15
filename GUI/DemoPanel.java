package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import DataStructure.Stack;
import DataStructure.Queue;
import DataStructure.DataStructure;
import DataStructure.ListStruct;
import javax.swing.Timer;

class DemoPanel extends JPanel {
    private DataStructure dataStructure;
    private JTextArea outputArea;
    private JTextField inputField;
    private JPanel visualizationPanel;

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    

    public DemoPanel(DataStructure dataStructure, String name) {
        this.dataStructure = dataStructure;
        setName(name);
        setLayout(new BorderLayout());

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(150, 30)); // Increase the size of the input field
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter Value: "));
        inputPanel.add(inputField);
        add(inputPanel, BorderLayout.NORTH);


        visualizationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawElements(g);
            }
        };


        visualizationPanel.setPreferredSize(new Dimension(400, 100));
        add(visualizationPanel, BorderLayout.SOUTH);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6, 5, 5));
        JButton createButton = new JButton("Create");
        JButton insertButton = new JButton("Insert");
        JButton sortButton = new JButton("Sort");
        JButton findButton = new JButton("Find");
        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");

        createButton.addActionListener(e -> {
            dataStructure.getElements().clear();
            outputArea.setText(name + " created\n");
            repaintVisualization();
        });

        insertButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                animateInsertion(value); // Call animation for insertion
                outputArea.append("Inserted: " + value + "\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                inputField.setText(""); // Clear input
            }
        });
        

        sortButton.addActionListener(e -> {
            dataStructure.sort();
            outputArea.append(name + " sorted: " + dataStructure.getElements() + "\n");
            repaintVisualization();
        });

        findButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                outputArea.append("Element " + value + (dataStructure.find(value) ? " found\n" : " not found\n"));
            } catch (NumberFormatException ex) {
                showError("Please enter a valid integer.");
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                dataStructure.delete(value);
                outputArea.append("Deleted: " + value + "\n");
                repaintVisualization();
            } catch (NumberFormatException ex) {
                showError("Please enter a valid integer.");
            } finally {
                inputField.setText(""); 
            }
        });

        backButton.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());

        buttonPanel.add(createButton);
        buttonPanel.add(insertButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(findButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void drawElements(Graphics g) {
        java.util.List<Integer> elements = dataStructure.getElements();
        int x = 10, y = 20;
        int panelHeight = 50 + (elements.size() / 8) * 50; // Dynamically adjust height 
    
        visualizationPanel.setPreferredSize(new Dimension(getWidth(), panelHeight));
        visualizationPanel.revalidate();
    
        for (int value : elements) {
            g.drawRect(x, y, 40, 40);
            g.drawString(String.valueOf(value), x + 15, y + 25);
            x += 50;
            if (x > getWidth() - 50) {
                x = 10;
                y += 50;
            }
        }
    }    

    private void repaintVisualization() {
        visualizationPanel.repaint();
    }

    private void animateInsertion(int value) {
        int panelWidth = visualizationPanel.getWidth();
        int x = 0; // Start from the leftmost side
        int targetX = 10 + dataStructure.getElements().size() * 50; // Final position
    
        Timer timer = new Timer(20, null); // 20ms delay between frames
        timer.addActionListener(new ActionListener() {
            int currentX = x;
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g = visualizationPanel.getGraphics();
                g.clearRect(0, 0, visualizationPanel.getWidth(), visualizationPanel.getHeight());
                drawElements(g); // Draw the current state
    
                // Draw the animated element
                g.setColor(Color.RED);
                g.drawRect(currentX, 20, 40, 40);
                g.drawString(String.valueOf(value), currentX + 15, 40);
    
                if (currentX >= targetX) { // Stop animation when target is reached
                    timer.stop();
                    dataStructure.insert(value); // Finalize insertion in the data structure
                    repaintVisualization(); // Redraw everything
                }
                currentX += 10; // Move the rectangle to the right
            }
        });
        timer.start(); // Start the animation
    }
    
    
}

