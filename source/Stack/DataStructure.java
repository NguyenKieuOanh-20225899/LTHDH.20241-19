package source.Stack;
import java.util.*;

public abstract class DataStructure {
    protected List<Integer> elements;

    public DataStructure() {
        elements = new ArrayList<>();
    }

    public abstract void insert(int value);
    public abstract void delete(int value);
    public abstract boolean find(int value);
    public abstract void sort();
    public abstract void display();
    public List<Integer> getElements() {
        return elements;
    }
}
