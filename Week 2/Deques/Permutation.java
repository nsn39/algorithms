/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomQ = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            randomQ.enqueue(word);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(randomQ.dequeue());
        }
    }
}
