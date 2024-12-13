
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


class Stack extends DataStructure {
    private ArrayList<Integer> stack;

    public Stack() {
        stack = new ArrayList<>();
    }

    @Override
    public void insert(int value) {
        stack.add(value);
        elements.add(value);
    }

    @Override
    public void delete(int value) {
        if (!stack.isEmpty()) {
            int removed = stack.remove(stack.size() - 1);
            removed = (Integer)removed;
            elements.remove(removed);
        } 
    }

    @Override
    public boolean find(int value) {
        return stack.contains(value);
    }

    @Override
    public void sort() {
        Collections.sort(elements);
        stack.clear();
        stack.addAll(elements);
    }
}
