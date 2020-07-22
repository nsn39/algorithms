/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BottomMergeSort {

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[i] > aux[j]) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    private static void sort(int[] a) {
        int N = a.length;
        int[] aux = new int[a.length];
        for (int sz = 1; sz < N; sz++) {
            for (int lo = 0; lo <= N - sz - 1; lo++) {
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    public static void main(String[] args) {
        int[] sample1 = { 78, 32, 54, 65, 26, 46, 92, 35, 53, 18, 93, 43, 53, 90, 25, 57 };
        sort(sample1);
        StdOut.println(Arrays.toString(sample1));
    }
}
