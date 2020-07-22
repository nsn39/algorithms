/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class BinaryMaxPQ {
    private int[] vals;
    private int N;

    public BinaryMaxPQ(int capacity) {
        N = 0;
        vals = new int[capacity + 1];
    }

    public boolean isEmpty() {
        return (N == 0);
    }

    public void insert(int x) {
        vals[++N] = x;
        swim(N);
    }

    public int delMax() {
        int maxVal = vals[1];
        swap(vals, N--, 1);
        sink(1);
        vals[N + 1] = Integer.parseInt(null);
        return maxVal;
    }

    public void swim(int k) {
        while (k > 1 && vals[k] > vals[k / 2]) {
            swap(vals, k, k / 2);
            k /= 2;
        }
    }

    public void sink(int k) {
        while (k * 2 <= N) {
            int j = 2 * k;
            if (j < N && vals[j] < vals[j + 1]) j++;
            if (vals[j] < vals[k]) break;
            swap(vals, k, j);
            k = j;
        }
    }

    private void swap(int[] a, int ind1, int ind2) {
        int temp = a[ind1];
        a[ind1] = a[ind2];
        a[ind2] = temp;
    }

    public static void main(String[] args) {
        UnorderedMaxPQ m1 = new UnorderedMaxPQ(15);
        m1.insert(7);
        m1.insert(49);
        m1.insert(34);
        m1.insert(45);
        m1.insert(32);
        StdOut.println(m1.delMax());
    }
}
