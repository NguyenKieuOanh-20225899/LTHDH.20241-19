package Main;

import java.util.Collections;
import java.util.List;

public class ListStructure<T extends Comparable<T>> extends DataStructure<T> {

    // Add an element to the list
    public void add(T item) {
        elements.add(item);
    }

    // Remove an element from the list
    public void remove(T item) {
        elements.remove(item);
    }

    // Get an element by its index
    public T get(int index) {
        if (index < 0 || index >= elements.size()) {
            return null;
        }
        return elements.get(index);
    }

    // Sort the list in ascending order
    @Override
    public void sort() {
        Collections.sort((List<T>) elements);
    }

    // Override find() to check if the element exists
    @Override
    public boolean find(T item) {
        return elements.contains(item); // Return true if the element exists
    }

    // New method: Find the index of an item
    public int findIndex(T item) {
        return elements.indexOf(item); // Return the index, or -1 if not found
    }

    @Override
    public String toString() {
        return "List: " + display();
    }
}
