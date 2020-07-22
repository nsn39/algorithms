/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description: Dijkstras three parittion quick sort implementation.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class QuickSortThreePartition {

    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int val = a[lo];
        int lt = lo;
        int gt = hi;
        int i = lt;
        while (i <= gt) {
            if (val == a[i]) i++;
            else if (val > a[i]) {
                // Swap a[i] and a[lt] and increment them both.
                int temp = a[i];
                a[i] = a[lt];
                a[lt] = temp;
                i++;
                lt++;
            }
            else {
                // Swap a[i] and a[gt] and decrement gt.
                int temp = a[i];
                a[i] = a[gt];
                a[gt] = temp;
                gt--;
            }
        }

        sort(a, lo, lt - 1);
        sort(a, lt + 1, hi);

    }

    public static void sort(int[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        int[] sample1 = { 3, 3, 4, 2, 2, 6, 6, 2, 3, 5, 4, 4, 4 };
        sort(sample1);
        StdOut.println(Arrays.toString(sample1));
    }
}
