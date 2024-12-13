package DataStructure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


abstract class DataStructure{
    protected java.util.List<Integer> elements;

    public DataStructure() {
        elements = new ArrayList<>();
    }

    public abstract void insert(int value);
    public abstract void delete(int value);
    public abstract boolean find(int value);
    public abstract void sort();

    public java.util.List<Integer> getElements() {
        return elements;
    }
}