package Main;

import java.util.Collections;
import java.util.List;

public class ListStructure<T extends Comparable<T>> extends DataStructure<T> {

    public void add(T item) {
        elements.add(item);
    }

    public void remove(T item) {
        elements.remove(item);
    }

    public T get(int index) {
        if (index < 0 || index >= elements.size()) {
            return null;
        }
        return elements.get(index);
    }

    @Override
    public void sort() {
        Collections.sort((List<T>) elements);
    }

    @Override
    public boolean find(T item) {
        return elements.contains(item); 
    }

    public int findIndex(T item) {
        return elements.indexOf(item); 
    }

    @Override
    public String toString() {
        return "List: " + display();
    }
}
