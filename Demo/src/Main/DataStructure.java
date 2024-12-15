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
    }

    public boolean find(T item) {
        return elements.contains(item); 
    }

    public void delete(T item) {
        elements.remove(item);
    }

    public String display() {
        return elements.toString();
    }
}
