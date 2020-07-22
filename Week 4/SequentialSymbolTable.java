import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class SequentialSymbolTable<Key, Value> {
    private class Node {
        Key key;
        Value value;
        Node next;

        Node(Key keyName, Value valueName) {
            this.key = keyName;
            this.value = valueName;
        }
    }

    private LinkedList<Node> aList;
    private int sizeNum;

    public SequentialSymbolTable() {
        aList = new LinkedList<>();
        sizeNum = 0;
    }

    public void put(Key key, Value value) {
        boolean hasMatch = false;
        for (int i = 0; i < aList.size(); i++) {
            if (aList.get(i).key.equals(key)) {
                hasMatch = true;
                aList.get(i).value = value;
                break;
            }
        }
        if (!hasMatch) {
            Node newNode = new Node(key, value);
            aList.addFirst(newNode);
            sizeNum++;
        }
    }

    public Value get(Key key) {
        Value ans = null;
        Node firstNode = aList.getFirst();
        while (firstNode != null) {
            if (firstNode.key.equals(key)) {
                ans = firstNode.value;
                break;
            }
            firstNode = firstNode.next;
        }
        return ans;
    }

    public void delete(Key key) {
        Node firstNode = aList.getFirst();
        while (firstNode != null) {
            if (firstNode.key.equals(key)) {
                boolean ans = aList.remove(firstNode);
                if (ans) sizeNum--;
                break;
            }
            firstNode = firstNode.next;
        }
    }

    public boolean contains(Key key) {
        return (get(key) != null);
    }

    public boolean isEmpty() {
        return (sizeNum == 0);
    }

    public int size() {
        return sizeNum;
    }

    public static void main(String[] args) {
        SequentialSymbolTable<Integer, Integer> mine
                = new SequentialSymbolTable<>();
        mine.put(1, 10);
        mine.put(1, 50);
        mine.put(2, 100);
        mine.put(3, 100);
        mine.delete(3);
        StdOut.println(mine.size());
    }
}

