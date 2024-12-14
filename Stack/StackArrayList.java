package Stack;

import java.util.*;

//Stack sử dụng ArrayList
public class StackArrayList extends DataStructure {

 public StackArrayList() {
     super();
 }

 @Override
 public void insert(int value) {
     elements.add(value);
     System.out.println("Pushed: " + value);
 }

 public int pop() {
     if (elements.isEmpty()) {
         System.out.println("Stack is empty. Cannot pop.");
         return -1; // trả về -1 nếu Stack rỗng
     }
     int poppedValue = elements.remove(elements.size() - 1);
     System.out.println("Popped: " + poppedValue);
     return poppedValue;
 }

 public int peek() {
     if (elements.isEmpty()) {
         System.out.println("Stack is empty.");
         return -1; // trả về -1 nếu Stack rỗng
     }
     return elements.get(elements.size() - 1);
 }

 public boolean isEmpty() {
     return elements.isEmpty();
 }

 @Override
 public void display() {
     if (isEmpty()) {
         System.out.println("Stack is empty.");
         return;
     }
     System.out.print("Stack contents: ");
     for (int i = 0; i < elements.size(); i++) {
         System.out.print(elements.get(i) + " ");
     }
     System.out.println();
 }

 @Override
 public void delete(int value) {
     if (elements.isEmpty()) {
         System.out.println("Stack is empty. Cannot delete.");
         return;
     }

     boolean deleted = elements.remove(Integer.valueOf(value)); // xóa phần tử đầu tiên có giá trị bằng value
     if (deleted) {
         System.out.println("Deleted: " + value);
     } else {
         System.out.println("Value not found in stack.");
     }
 }

 @Override
 public boolean find(int value) {
     return elements.contains(value);
 }

 @Override
 public void sort() {
     if (elements.isEmpty()) {
         System.out.println("Stack is empty. Cannot sort.");
         return;
     }
     // Sắp xếp bằng thuật toán Bubble Sort
     for (int i = 0; i < elements.size() - 1; i++) {
         for (int j = 0; j < elements.size() - i - 1; j++) {
             if (elements.get(j) > elements.get(j + 1)) {
                 // Hoán đổi giá trị nếu phần tử trước lớn hơn phần tử sau
                 int temp = elements.get(j);
                 elements.set(j, elements.get(j + 1));
                 elements.set(j + 1, temp);
             }
         }
     }
     System.out.println("Stack sorted: " + elements);
 }
}











