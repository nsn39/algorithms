/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class HeapSort {
    public static void sort(int[] a) {
        int N = a.length;

        // Max-heapify the array first
        for (int i = (N / 2) - 1; i >= 0; i--) {
            sink(a, i, N);
        }

        // Now as long as N>1 pop out the max element and max-heapfiy again...
        while (N > 1) {
            swap(a, 0, N - 1);
            sink(a, 0, --N);
        }
    }

    private static void swap(int[] a, int ind1, int ind2) {
        StdOut.println("Swap between " + ind1 + " and " + ind2);
        int temp = a[ind1];
        a[ind1] = a[ind2];
        a[ind2] = temp;
    }

    private static void sink(int[] vals, int k, int N) {
        while (k <= (N / 2) - 1) {
            int j = (2 * k) + 1;
            if (j < N - 1 && less(vals[j], vals[j + 1]) < 0) j++;
            if (vals[k] > vals[j]) break;
            swap(vals, k, j);
            k = j;
        }
    }

    private static int less(int a, int b) {
        return Integer.compare(a, b);
    }


    public static void main(String[] args) {
        int[] sample = { 53, 34, 64, 2, 646, 243, 5654, 234, 757, 4, 54, 63 };
        StdOut.println("Array length of " + sample.length);
        sort(sample);
        for (int item : sample) {
            StdOut.println(item);
        }
    }
}
