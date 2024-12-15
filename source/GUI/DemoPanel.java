package source.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Queue;


import source.DataStructure.DataStructure;
import source.DataStructure.ListStruct;

class DemoPanel extends JPanel {
    private DataStructure dataStructure;
    private JTextArea outputArea;
    private JTextField inputField;
    private JPanel visualizationPanel;

    public DemoPanel(DataStructure dataStructure, String name) {
        this.dataStructure = dataStructure;
        setName(name);
        setLayout(new BorderLayout());

        // Output Area
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.EAST);

        // Input Panel
        // inputField = new JTextField();
        // inputField.setPreferredSize(new Dimension(150, 30));
        // JPanel inputPanel = new JPanel(new FlowLayout());
        // inputPanel.add(new JLabel("Enter Value: "));
        // inputPanel.add(inputField);
        // add(inputPanel, BorderLayout.NORTH);

        // Index Panel
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(100, 30)); // Input for the value
        JTextField indexField = new JTextField();
        indexField.setPreferredSize(new Dimension(50, 30)); // Input for the index

        JPanel inputPanel = new JPanel(new FlowLayout());
        
        JLabel valueLabel = new JLabel("Value: ");
        JLabel indexLabel = new JLabel("Index: "); 

        inputPanel.add(valueLabel);
        inputPanel.add(inputField);
        inputPanel.add(indexLabel);
        inputPanel.add(indexField);
        add(inputPanel, BorderLayout.NORTH);

        String type = dataStructure.getType();       // set index not visible for stack and queue
        if (type == "Stack" || type == "Queue") {
            indexField.setVisible(false);
            indexLabel.setVisible(false);
        }

        // Visualization Panel
        visualizationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawElements(g);
            }
        };
        visualizationPanel.setPreferredSize(new Dimension(500, 200));
        visualizationPanel.setBackground(Color.WHITE);
        add(visualizationPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6, 5, 5));
        JButton createButton = new JButton("Create");
        JButton insertButton = new JButton("Insert");
        JButton sortButton = new JButton("Sort");
        JButton findButton = new JButton("Find");
        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");

        // Button Listeners
        createButton.addActionListener(e -> {
            dataStructure.getElements().clear();
            outputArea.setText(name + " created\n");
            repaintVisualization();
        });

                //String type = dataStructure.getType();       
        insertButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());

                if (type == "Stack" || type == "Queue") {
                    animateStackQueueInsertion(value); 
                    outputArea.append("Inserted: " + value + " into the Stack\n");
                } 
                else if (type == "List") {
                    String indexText = indexField.getText().trim();

                    if (!indexText.isEmpty()) {
                        int index = Integer.parseInt(indexText);
                        ((ListStruct) dataStructure).insert(value, index); 
                        outputArea.append("Inserted: " + value + " at index " + index + " in the List\n");
                    } 
                    else {
                        ((ListStruct) dataStructure).insert(value); // .append()
                        outputArea.append("Inserted: " + value + " into the List\n");
                    }
                    animateListInsertion(value); 
                }
        
                repaintVisualization(); 


            } catch (NumberFormatException ex) {
                showError("Please enter valid integers for value and index.");
            } catch (IndexOutOfBoundsException ex) {
                showError("Invalid index: " + ex.getMessage());
            } finally {
                inputField.setText(""); 
                if (indexField != null) {
                    indexField.setText(""); 
                }
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

                        //String type = dataStructure.getType();       
        deleteButton.addActionListener(e -> {
            try {
                if (type == "Stack") {
                    animateStackDelete();
                    outputArea.append("Deleted the top element from the Stack\n");
                } 
                else if (type == "Queue") {
                    animateQueueDelete();
                    outputArea.append("Deleted the front element from the Queue\n");
                } 
                else if (type == "List") {
                    String indexText = indexField.getText().trim();
                    if (!indexText.isEmpty()) {
                        int index = Integer.parseInt(indexText);
                        animateListDelete(index);
                        outputArea.append("Deleted value at index " + index + " in the List\n");
                    } else {
                        showError("Please enter a valid index for deletion.");
                    }
                } else {
                    showError("Unsupported data structure for deletion.");
                }
            } catch (NumberFormatException ex) {
                showError("Please enter a valid integer index.");
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

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }





    // visual animation
    private void drawElements(Graphics g) {
        java.util.List<Integer> elements = dataStructure.getElements();
        int x = 10; 
        int y = 20; 
        int boxWidth = 40; 
        int boxHeight = 40; 
        int spacing = 10; 

        for (int value : elements) {
            g.setColor(Color.LIGHT_GRAY); // Box color
            g.fillRect(x, y, boxWidth, boxHeight); 
            g.setColor(Color.BLACK); // Border and text color
            g.drawRect(x, y, boxWidth, boxHeight); 
            g.drawString(String.valueOf(value), x + 15, y + 25); // Draw value inside the box

            x += boxWidth + spacing; // Move to next position
            if (x > visualizationPanel.getWidth() - boxWidth) { // Wrap to next row
                x = 10;
                y += boxHeight + spacing;
            }
        }
    }

    private void repaintVisualization() {
        visualizationPanel.repaint();
    }

    private void animateStackQueueInsertion(int value) {
        int targetX = 10 + dataStructure.getElements().size() * 50; // Final position
        final int[] currentX = {0}; 
    
        javax.swing.Timer timer = new javax.swing.Timer(40, (ActionEvent e) -> { // Explicit javax.swing.Timer
            Graphics g = visualizationPanel.getGraphics();
            g.clearRect(0, 0, visualizationPanel.getWidth(), visualizationPanel.getHeight());
            drawElements(g); 
    
            g.setColor(Color.RED);
            g.drawRect(currentX[0], 20, 40, 40);
            g.drawString(String.valueOf(value), currentX[0] + 15, 40);
    
            if (currentX[0] >= targetX) { 
                ((javax.swing.Timer) e.getSource()).stop();
                dataStructure.insert(value); 
                repaintVisualization(); 
            }
            currentX[0] += 10; 
        });
        timer.start(); 
    }

    private void animateListInsertion(int value) {
        String type = dataStructure.getType();       
        if(!(type == "List")){
            dataStructure.insert(value);    // data insertino
        }
    
        javax.swing.Timer timer = new javax.swing.Timer(20, (ActionEvent e) -> {
            ((javax.swing.Timer) e.getSource()).stop(); 
            repaintVisualization(); 
        });
        timer.setRepeats(false); // execute the action once
        timer.start(); 
    }

    private void animateStackDelete() {
        if (dataStructure.getElements().isEmpty()) {
            showError("Stack is empty. Nothing to delete.");
            return;
        }
    
        int valueToDelete = dataStructure.getElements().get(dataStructure.getElements().size() - 1); // Top element
    
        final int startY = 20; 
        final int targetY = -40; 
    
        javax.swing.Timer timer = new javax.swing.Timer(20, null); 
        timer.addActionListener(new ActionListener() {
            int currentY = startY;
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g = visualizationPanel.getGraphics();
                g.clearRect(0, 0, visualizationPanel.getWidth(), visualizationPanel.getHeight());
                drawElements(g); 
    
                g.setColor(Color.RED);
                g.drawRect(10 + (dataStructure.getElements().size() - 1) * 50, currentY, 40, 40);
                g.drawString(String.valueOf(valueToDelete), 10 + (dataStructure.getElements().size() - 1) * 50 + 15, currentY + 25);
    
                if (currentY <= targetY) { 
                    ((javax.swing.Timer) e.getSource()).stop();
                    dataStructure.delete(valueToDelete); // data deletion
                    repaintVisualization(); 
                }
                currentY -= 10; // Move the rectangle upwards
            }
        });
        timer.start(); 
    }
    
    private void animateQueueDelete() {
        if (dataStructure.getElements().isEmpty()) {
            showError("Queue is empty. Nothing to delete.");
            return;
        }
        int valueToDelete = dataStructure.getElements().get(0); // First element
    
        final int startX = 10; 
        final int targetX = -50; 
    
        javax.swing.Timer timer = new javax.swing.Timer(40, null); 
        timer.addActionListener(new ActionListener() {
            int currentX = startX;
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g = visualizationPanel.getGraphics();
                g.clearRect(0, 0, visualizationPanel.getWidth(), visualizationPanel.getHeight());
                drawElements(g); 
    
                g.setColor(Color.RED);
                g.drawRect(currentX, 20, 40, 40);
                g.drawString(String.valueOf(valueToDelete), currentX + 15, 45);
    
                if (currentX <= targetX) { 
                    ((javax.swing.Timer) e.getSource()).stop();
                    dataStructure.delete(valueToDelete); // data deletion
                    repaintVisualization(); 
                }
                currentX -= 10; 
            }
        });
        timer.start(); 
    }

    private void animateListDelete(int index) {
        if (index < 0 || index >= dataStructure.getElements().size()) {
            showError("Invalid index: " + index + ". Index must be between 0 and " + (dataStructure.getElements().size() - 1));
            return;
        }
    
        int valueToDelete = dataStructure.getElements().get(index); 
    
        final int startX = 10 + index * 50; 
        final int targetX = startX - 50; 
    
        javax.swing. Timer timer = new javax.swing.Timer(40, null); 
        timer.addActionListener(new ActionListener() {
            int currentX = startX;
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g = visualizationPanel.getGraphics();
                g.clearRect(0, 0, visualizationPanel.getWidth(), visualizationPanel.getHeight());
                drawElements(g); // draw all except deleted element
    
                g.setColor(Color.RED);
                g.drawRect(currentX, 20, 40, 40);
                g.drawString(String.valueOf(valueToDelete), currentX + 15, 45);
    
                if (currentX <= targetX) { 
                    ((javax.swing.Timer) e.getSource()).stop();
                    dataStructure.getElements().remove(index); // data deletion
                    repaintVisualization(); 
                }
                currentX -= 10; 
            }
        });
        timer.start(); 
    }
    
    

}
