/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderedSymbolTable {
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

    public OrderedSymbolTable(int capacity) {
        nodesList = Arrays.asList(new Node[capacity]);
        size = 0;
    }

    public void put(int key, int value) {
        if (isEmpty()) {
            // StdOut.println("First phase");
            // nodesList.add(new Node(key, value));
            nodesList.set(size, new Node(key, value));
            // StdOut.println("Somewhere between.");
            size++;
        }
        else {
            // StdOut.println("This is the big time.");
            int ind = rank(key);
            // StdOut.println("Rank successfully taken.");
            // StdOut.println(key);
            if (contains(key)) {
                nodesList.get(ind).value = value;
            }
            else {
                for (int i = size - 1; i >= ind; i--) {
                    // StdOut.println("Second phase");
                    nodesList.set(i + 1, nodesList.get(i));
                }
                // StdOut.println("Third phase");
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

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean contains(int key) {
        return (get(key) != Integer.MIN_VALUE);
    }

    public int size() {
        return size;
    }

    public int min() {
        return nodesList.get(0).key;
    }

    public int max() {
        return nodesList.get(size - 1).key;
    }

    public int floor(int key) {
        int rank = rank(key);
        if (nodesList.get(rank).key == key) {
            return nodesList.get(rank).key;
        }
        else {
            return nodesList.get(rank - 1).key;
        }
    }

    public int ceiling(int key) {
        int rank = rank(key);
        return nodesList.get(rank).key;
    }

    public int rank(int key) {
        int lo = 0, hi = size - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = Integer.compare(key, nodesList.get(mid).key);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }


    public void deleteMax() {
        nodesList.set(size - 1, null);
        size--;
    }

    public void deleteMin() {
        for (int i = 1; i < size; i++) {
            nodesList.set(i - 1, nodesList.get(i));
        }
        size--;
    }

    public int size(int lo, int hi) {
        int rank1 = rank(lo);
        int rank2 = rank(hi);
        return rank2 - rank1 + 1;
    }

    Iterable<Integer> keys(int lo, int hi) {
        int ind1 = ceiling(lo);
        int ind2 = floor(hi);
        List<Integer> ans = new ArrayList<>();
        for (int i = ind1; i <= ind2; i++) {
            ans.add(nodesList.get(i).key);
        }
        return ans;
    }

    Iterable<Integer> keys() {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ans.add(nodesList.get(i).key);
        }
        return ans;
    }

    public static void main(String[] args) {
        OrderedSymbolTable mine
                = new OrderedSymbolTable(100);
        mine.put(1, 10);
        mine.put(1, 50);
        mine.put(2, 100);
        mine.put(3, 100);
        mine.put(10, 100);
        StdOut.println(mine.size());
        StdOut.print(mine.floor(7));
    }
}
