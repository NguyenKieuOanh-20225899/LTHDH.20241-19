package Main;

import java.util.LinkedList;
import java.util.Queue;

public class QueueStructure<T> {
    private Queue<T> queue;

    public QueueStructure() {
        this.queue = new LinkedList<>();
    }

    // Tạo mới hàng đợi
    public void create() {
        this.queue = new LinkedList<>();
    }

    // Thêm phần tử vào hàng đợi
    public void enqueue(T item) {
        queue.add(item);
    }

    // Lấy và xóa phần tử ở đầu hàng đợi
    public T dequeue() {
        if (isEmpty()) {
            return null; // Trả về null nếu hàng đợi rỗng
        }
        return queue.poll();
    }

    // Lấy phần tử đầu tiên mà không xóa
    public T front() {
        if (isEmpty()) {
            return null; // Trả về null nếu hàng đợi rỗng
        }
        return queue.peek(); // peek() trả về phần tử đầu tiên nhưng không xóa
    }

    // Kiểm tra hàng đợi có rỗng không
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public String toString() {
        return queue.toString(); // Hiển thị hàng đợi dưới dạng chuỗi
    }
}
