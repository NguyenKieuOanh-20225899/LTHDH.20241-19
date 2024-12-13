
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Queue extends DataStructure {
    private ArrayList<Integer> queue;

    public Queue() {
        queue = new ArrayList<>();
    }

    @Override
    public void insert(int value) {
        queue.add(value);
        elements.add(value);
    }

    @Override
    public void delete(int value) {
        if (!queue.isEmpty()) {
            int removed = queue.remove(0);
            elements.remove((Integer) removed);
        }
    }

    @Override
    public boolean find(int value) {
        return queue.contains(value);
    }

    @Override
    public void sort() {
        Collections.sort(elements);
        queue.clear();
        queue.addAll(elements);
    }
}
