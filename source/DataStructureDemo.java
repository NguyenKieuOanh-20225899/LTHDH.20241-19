package source;


import javax.swing.*;

import source.GUI.MainMenu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DataStructureDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}