package source.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import source.DataStructure.DataStructure;
import source.DataStructure.ListStruct;

public class DemoPanel extends JPanel {
    private DataStructure dataStructure;
    private JTextArea outputArea;
    private JTextField inputField;
    private JPanel visualizationPanel;
    private AnimationHandler animationHandler;

    public DemoPanel(DataStructure dataStructure, String name) {
        this.dataStructure = dataStructure;
        setName(name);
        setLayout(new BorderLayout());

        // Output Area
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.EAST);

        // Index Panel
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(100, 30));
        JTextField indexField = new JTextField();
        indexField.setPreferredSize(new Dimension(50, 30));

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel valueLabel = new JLabel("Value: ");
        JLabel indexLabel = new JLabel("Index: ");

        inputPanel.add(valueLabel);
        inputPanel.add(inputField);
        inputPanel.add(indexLabel);
        inputPanel.add(indexField);
        add(inputPanel, BorderLayout.NORTH);

        String type = dataStructure.getType();
        if (type.equals("Stack") || type.equals("Queue")) {
            indexField.setVisible(false);
            indexLabel.setVisible(false);
        }

        // Visualization Panel
        visualizationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                animationHandler.drawElements(g); 
            }
        };
        visualizationPanel.setPreferredSize(new Dimension(500, 200));
        visualizationPanel.setBackground(Color.WHITE);
        add(visualizationPanel, BorderLayout.CENTER);

        // Initialize AnimationHandler 
        animationHandler = new AnimationHandler(dataStructure, this, visualizationPanel);

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
            outputArea.setText("New " + name + " created\n");
            repaintVisualization();
        });

        insertButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                if (type.equals("Stack") || type.equals("Queue")) {
                    animationHandler.animateStackQueueInsertion(value);
                    outputArea.append("Inserted: " + value + " into the " + type + "\n");
                } else if (type.equals("List")) {
                    String indexText = indexField.getText().trim();
                    if (!indexText.isEmpty()) {
                        int index = Integer.parseInt(indexText);
                        ((ListStruct) dataStructure).insert(value, index);
                        outputArea.append("Inserted: " + value + " at index " + index + " in the List\n");
                    } else {
                        ((ListStruct) dataStructure).insert(value);
                        outputArea.append("Inserted: " + value + " into the List\n");
                    }
                    animationHandler.animateListInsertion(value);
                }
                repaintVisualization();
            } catch (NumberFormatException ex) {
                showError("Please enter valid integers for value and index.");
            } catch (IndexOutOfBoundsException ex) {
                showError("Invalid index: " + ex.getMessage());
            } finally {
                inputField.setText("");
                indexField.setText("");
            }
        });

        sortButton.addActionListener(e -> animationHandler.animateSort(outputArea, name));

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
                if (type.equals("Stack")) {
                    animationHandler.animateStackDelete();
                    outputArea.append("Deleted the top element from the Stack\n");
                } else if (type.equals("Queue")) {
                    animationHandler.animateQueueDelete();
                    outputArea.append("Deleted the front element from the Queue\n");
                } else if (type.equals("List")) {
                    String indexText = indexField.getText().trim();
                    if (!indexText.isEmpty()) {
                        int index = Integer.parseInt(indexText);
                        animationHandler.animateListDelete(index);
                        outputArea.append("Deleted value at index " + index + " in the List\n");
                    } else {
                        showError("Please enter a valid index for deletion.");
                    }
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

    public void repaintVisualization() {
        visualizationPanel.repaint();
    }

}
