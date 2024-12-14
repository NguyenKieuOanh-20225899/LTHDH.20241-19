package Stack;

//Lớp Main để thử nghiệm
public class Main {
 public static void main(String[] args) {
     StackArrayList stackArrayList = new StackArrayList();
     StackLinkedList stackLinkedList = new StackLinkedList();
     StackDoublyLinkedList stackDoublyLinkedList = new StackDoublyLinkedList();

     // Thử nghiệm với Stack ArrayList
     stackArrayList.insert(10);
     stackArrayList.insert(20);
     stackArrayList.insert(30);
     stackArrayList.display();
     stackArrayList.pop();
     stackArrayList.display();
     stackArrayList.sort();
     stackArrayList.display();

     // Thử nghiệm với Stack Linked List Đơn
     stackLinkedList.insert(5);
     stackLinkedList.insert(15);
     stackLinkedList.insert(25);
     stackLinkedList.display();
     stackLinkedList.pop();
     stackLinkedList.display();
     stackLinkedList.sort();
     stackLinkedList.display();

     // Thử nghiệm với Stack Linked List Kép
     stackDoublyLinkedList.insert(7);
     stackDoublyLinkedList.insert(17);
     stackDoublyLinkedList.insert(27);
     stackDoublyLinkedList.display();
     stackDoublyLinkedList.pop();
     stackDoublyLinkedList.display();
     stackDoublyLinkedList.sort();
     stackDoublyLinkedList.display();
 }
}
