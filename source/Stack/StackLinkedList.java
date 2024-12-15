package source.Stack;

//Lớp Stack sử dụng Linked List Đơn
class Node {
int value;
Node next;

Node(int value) {
   this.value = value;
   this.next = null;
}
}

public class StackLinkedList extends DataStructure {

private Node top;

public StackLinkedList() {
   super();
   this.top = null;
}

@Override
public void insert(int value) {
   Node newNode = new Node(value);
   newNode.next = top;
   top = newNode;
   System.out.println("Pushed: " + value);
}

public int pop() {
   if (top == null) {
       System.out.println("Stack is empty. Cannot pop.");
       return -1; // trả về -1 nếu Stack rỗng
   }
   int poppedValue = top.value;
   top = top.next;
   System.out.println("Popped: " + poppedValue);
   return poppedValue;
}

public int peek() {
   if (top == null) {
       System.out.println("Stack is empty.");
       return -1; // trả về -1 nếu Stack rỗng
   }
   return top.value;
}

public boolean isEmpty() {
   return top == null;
}

@Override
public void display() {
   if (isEmpty()) {
       System.out.println("Stack is empty.");
       return;
   }
   Node current = top;
   System.out.print("Stack contents: ");
   while (current != null) {
       System.out.print(current.value + " ");
       current = current.next;
   }
   System.out.println();
}

@Override
public void delete(int value) {
   if (isEmpty()) {
       System.out.println("Stack is empty. Cannot delete.");
       return;
   }

   if (top.value == value) {
       top = top.next;
       System.out.println("Deleted: " + value);
       return;
   }

   Node current = top;
   while (current.next != null && current.next.value != value) {
       current = current.next;
   }

   if (current.next != null) {
       current.next = current.next.next;
       System.out.println("Deleted: " + value);
   } else {
       System.out.println("Value not found in stack.");
   }
}

@Override
public boolean find(int value) {
   Node current = top;
   while (current != null) {
       if (current.value == value) {
           return true;
       }
       current = current.next;
   }
   return false;
}

@Override
public void sort() {
   if (top == null) {
       System.out.println("Stack is empty. Cannot sort.");
       return;
   }
   // Sắp xếp bằng thuật toán Bubble Sort
   Node current = top;
   while (current != null) {
       Node nextNode = current.next;
       while (nextNode != null) {
           if (current.value > nextNode.value) {
               // Hoán đổi giá trị nếu phần tử trước lớn hơn phần tử sau
               int temp = current.value;
               current.value = nextNode.value;
               nextNode.value = temp;
           }
           nextNode = nextNode.next;
       }
       current = current.next;
   }
   display();
}
}