/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedStackOfStrings {
    private String[] items;
    private int n;

    public FixedStackOfStrings(int capacity) {
        items = new String[capacity];
        this.n = 0;
    }

    public boolean isEmpty() {
        return (n == 0);
    }

    public void push(String item) {
        items[n++] = item;
    }

    public String pop() {
        return items[--n];
    }

    public static void main(String[] args) {
        FixedStackOfStrings stack = new FixedStackOfStrings(15);
        while (!StdIn.isEmpty()) {
            String newS = StdIn.readString();
            if (newS.equals("-")) {
                StdOut.println(stack.pop());
            }
            else {
                stack.push(newS);
            }
        }
    }
}
