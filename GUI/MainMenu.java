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


public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Data Structure Operations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Data Structure Demonstration", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton stackButton = new JButton("Stack");
        JButton queueButton = new JButton("Queue");
        JButton listButton = new JButton("List");
        JButton helpButton = new JButton("Help");
        JButton quitButton = new JButton("Quit");

        stackButton.addActionListener(e -> openDemo(new DemoPanel(new Stack(), "Stack")));
        queueButton.addActionListener(e -> openDemo(new DemoPanel(new Queue(), "Queue")));
        listButton.addActionListener(e -> openDemo(new DemoPanel(new List(), "List")));
        helpButton.addActionListener(e -> showHelp());
        quitButton.addActionListener(e -> confirmQuit());

        buttonPanel.add(stackButton);
        buttonPanel.add(queueButton);
        buttonPanel.add(listButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void openDemo(JPanel demoPanel) {
        JFrame demoFrame = new JFrame(demoPanel.getName());
        demoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        demoFrame.setSize(500, 400);
        demoFrame.add(demoPanel);
        demoFrame.setVisible(true);
    }

    private void showHelp() {
        JOptionPane.showMessageDialog(this, "This application demonstrates basic operations on Stack, Queue, and List structures.\nChoose a structure to begin.", "Help", JOptionPane.INFORMATION_MESSAGE);
    }

    private void confirmQuit() {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
