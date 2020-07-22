/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.List;

public class BinarySymbolTable {
    private class Node {
        int key;
        int value;
        Node next;

        Node(int keyName, int valueName) {
            this.key = keyName;
            this.value = valueName;
        }
    }

    private List<Node> nodesList;
    private int size;

    public BinarySymbolTable(int capacity) {
        nodesList = Arrays.asList(new Node[capacity]);
        size = 0;
        // StdOut.println(size);
    }

    public void put(int key, int value) {
        if (isEmpty()) {
            StdOut.println("First phase");
            // nodesList.add(new Node(key, value));
            nodesList.set(size, new Node(key, value));
            StdOut.println("Somewhere between.");
            size++;
        }
        else {
            int ind = rank(key);
            if (nodesList.get(ind).key == key) {
                nodesList.get(ind).value = value;
            }
            else {
                for (int i = size - 1; i >= ind; i--) {
                    StdOut.println("Second phase");
                    nodesList.set(i + 1, nodesList.get(i));
                }
                StdOut.println("Third phase");
                nodesList.set(ind, new Node(key, value));
                size++;
            }
        }

    }

    public int get(int key) {
        if (isEmpty()) return Integer.MIN_VALUE;
        int ind = rank(key);
        if (ind < size && nodesList.get(ind).key == key) return nodesList.get(ind).value;
        else return Integer.MIN_VALUE;
    }

    public void delete(int key) {
        // If you get the key then shift all the array elements to the right of it to 1 step left.
        int ind = rank(key);
        if (nodesList.get(ind).key == key) {
            for (int i = ind + 1; i < size; i++) {
                nodesList.set(i - 1, nodesList.get(i));
            }
            size--;
        }
    }

    public boolean contains(int key) {
        return (get(key) != Integer.MIN_VALUE);
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    private int rank(int key) {

        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = Integer.compare(nodesList.get(mid).key, key);
            if (cmp > 0) lo = mid + 1;
            else if (cmp < 0) hi = mid - 1;
            else return mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        BinarySymbolTable mine
                = new BinarySymbolTable(100);
        mine.put(1, 10);
        mine.put(1, 50);
        mine.put(2, 100);
        mine.put(3, 100);
        // mine.delete(3);
        StdOut.println(mine.size());
    }
}
