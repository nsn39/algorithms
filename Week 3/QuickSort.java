/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class QuickSort {
    public static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while (true) {
            while (a[++i] < a[lo]) {
                if (i == hi) break;
            }

            while (a[--j] > a[lo]) {
                if (j == lo) break;
            }
            if (i >= j) break;
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;

        }
        int temp = a[j];
        a[j] = a[lo];
        a[lo] = temp;
        return j;
    }

    public static void sort(int[] a) {
        // To ensure the randomness.
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int lo, int hi) {

        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);

    }

    public static void main(String[] args) {
        int[] sample1 = { 78, 32, 54, 65, 26, 46, 92, 35, 53, 18, 93, 43, 53, 90, 25, 57 };
        sort(sample1);
        StdOut.println(Arrays.toString(sample1));
    }
}
