import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] datas;
    private int capacity;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        capacity = 1;
        size = 0;
        datas = (Item[]) new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == capacity) {
            resize(capacity * 2);
        }
        datas[size++] = item;
    }

    // Resizing the Array of items to fit in more elements.
    private void resize(int newCapacity) {
        Item[] newDatas = (Item[]) new Object[newCapacity];
        for (int i = 0; i < capacity; i++) {
            newDatas[i] = datas[i];
        }
        datas = newDatas;
        capacity = newCapacity;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        Item item = datas[randomIndex];
        datas[randomIndex] = datas[size - 1];
        datas[size - 1] = null;
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        return datas[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] copiedDatas;
        private int currentSize = size;

        public RandomizedQueueIterator() {
            Item[] copiedArray = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                copiedArray[i] = datas[i];
            }
            copiedDatas = copiedArray;
        }

        public boolean hasNext() {
            return (currentSize > 0);
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported.");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items present.");
            }
            int randomIndex = StdRandom.uniform(currentSize);
            Item item = copiedDatas[randomIndex];
            copiedDatas[randomIndex] = copiedDatas[currentSize - 1];
            copiedDatas[currentSize - 1] = null;
            currentSize--;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> mine = new RandomizedQueue<Integer>();
        mine.enqueue(8);
        mine.enqueue(9);
        mine.enqueue(7);
        mine.enqueue(3);
        mine.enqueue(4);
        StdOut.println(mine.size());
        for (int i = 0; i < 5; i++) {
            StdOut.println(mine.dequeue());
        }
        StdOut.println(mine.size());
    }
}
