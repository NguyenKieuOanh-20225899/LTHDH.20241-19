package Main;

import java.util.ArrayList;

public abstract class DataStructure<T> {
    protected ArrayList<T> elements;

    public DataStructure() {
        elements = new ArrayList<>();
    }

    public void create() {
        elements.clear();
    }

    public void insert(T item) {
        elements.add(item);
    }

    public void sort() {
        // Sorting logic is left for child classes, if needed
    }

    public boolean find(T item) {
        return elements.contains(item); // Return true if the item exists in the list
    }

    public void delete(T item) {
        elements.remove(item);
    }

    public String display() {
        return elements.toString();
    }
}
