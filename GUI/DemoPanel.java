package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

import DataStructure.Stack;
import DataStructure.Queue;
import DataStructure.DataStructure;
import DataStructure.List;


class DemoPanel extends JPanel {
    private DataStructure dataStructure;
    private JTextArea outputArea;
    private JTextField inputField;
    private JPanel visualizationPanel;

    public DemoPanel(DataStructure dataStructure, String name) {
        this.dataStructure = dataStructure;
        setName(name);
        setLayout(new BorderLayout());

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        inputField = new JTextField();
        add(inputField, BorderLayout.NORTH);

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
                dataStructure.insert(value);
                outputArea.append("Inserted: " + value + "\n");
                animateInsertion(value);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                dataStructure.delete(value);
                outputArea.append("Deleted: " + value + "\n");
                repaintVisualization();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());

        buttonPanel.add(createButton);
        buttonPanel.add(insertButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(findButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.NORTH);
    }

    private void drawElements(Graphics g) {
        java.util.List<Integer> elements = dataStructure.getElements();
        int x = 10;
        int y = 20;
        for (int value : elements) {
            g.drawRect(x, y, 40, 40);
            g.drawString(String.valueOf(value), x + 15, y + 25);
            x += 50;
        }
    }

    private void animateInsertion(int value) {
        Timer timer = new Timer(100, new ActionListener() {
            int xPos = 10;

            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g = visualizationPanel.getGraphics();
                g.clearRect(0, 0, visualizationPanel.getWidth(), visualizationPanel.getHeight());
                drawElements(g);

                g.drawRect(xPos, 20, 40, 40);
                g.drawString(String.valueOf(value), xPos + 15, 25);
                xPos += 50;

                if (xPos > visualizationPanel.getWidth()) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    private void repaintVisualization() {
        visualizationPanel.repaint();
    }
}
