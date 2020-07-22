/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class UnorderedMaxPQ {
    private int N;
    private int[] vals;

    public UnorderedMaxPQ(int capacity) {
        vals = new int[capacity];
        N = 0;
    }

    public boolean isEmpty() {
        return (N == 0);
    }

    public void insert(int x) {
        vals[N++] = x;
    }

    private void swap(int[] a, int ind1, int ind2) {
        int temp = a[ind1];
        a[ind1] = a[ind2];
        a[ind2] = temp;
    }

    public int delMax() {
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (vals[max] < vals[i]) {
                max = i;
            }
        }
        swap(vals, max, N - 1);
        return vals[--N];
    }

    public static void main(String[] args) {
        UnorderedMaxPQ m1 = new UnorderedMaxPQ(15);
        m1.insert(7);
        m1.insert(43);
        m1.insert(34);
        m1.insert(45);
        StdOut.println(m1.delMax());
    }
}
