package Main;

import java.util.ArrayList;

public class StackStructure<T> {
    protected ArrayList<T> elements = new ArrayList<>();

    public void push(T item) {
        elements.add(item);
    }

    public T pop() {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.remove(elements.size() - 1);
    }

    public T peek() {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(elements.size() - 1);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void create() {
        elements.clear();
    }

    public String display() {
        return elements.toString();
    }

    @Override
    public String toString() {
        return "Stack: " + display();
    }
}
