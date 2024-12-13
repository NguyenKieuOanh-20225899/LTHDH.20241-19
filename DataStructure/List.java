package DataStructure;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;


class List extends DataStructure {
    @Override
    public void insert(int value) {
        elements.add(value);
    }

    @Override
    public void delete(int value) {
        elements.remove((Integer) value);
    }

    @Override
    public boolean find(int value) {
        return elements.contains(value);
    }

    @Override
    public void sort() {
        Collections.sort(elements);
    }
}