/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class LinkedQueueOfStrings {
    private Node first, last;
    private int n;

    class Node {
        String item;
        Node next;
    }

    public void enqueue(String item) {
        Node oldLast = last;
        last = new Node();
        last.next = null;
        last.item = item;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public static void main(String[] args) {
        LinkedQueueOfStrings link = new LinkedQueueOfStrings();
        link.enqueue("this");
        link.enqueue("girl");
        System.out.println(link.dequeue());
    }
}
