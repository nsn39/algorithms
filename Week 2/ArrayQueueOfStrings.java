// Resizing Array Implementation of Queue
public class ArrayQueueOfStrings {
    private String[] q;
    private int tail;
    private int head;
    private int capacity;

    // Constructor to initialize the queue
    public ArrayQueueOfStrings(int capacity) {
        this.capacity = capacity;
        q = new String[capacity];
        tail = 0;
        head = 0;
    }

    public boolean isEmpty() {
        return (tail == head);
    }

    // Add items to end of the queue
    public void enqueue(String item) {
        // Resize the array if more items keep getting added to the queue.
        if (tail == capacity) {
            String[] newBone = new String[capacity * 2];
            capacity = capacity * 2;
            for (int i = head; i < tail; i++) {
                newBone[i - head] = q[i];
            }
            q = newBone;
            tail = tail - head;
            head = 0;
        }
        q[tail++] = item;
    }

    // Delete items from start of the queue
    public String dequeue() {
        if (isEmpty()) return null;
        return q[head++];
    }

    public static void main(String[] args) {
        ArrayQueueOfStrings link = new ArrayQueueOfStrings(1);
        link.enqueue("this");
        link.enqueue("girl");
        link.enqueue("pekka");
        System.out.println(link.dequeue());
    }
}
