import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* *****************************************************************************
 *  Name: Nishan Poudel
 *  Date: 31st May 2020
 *  Description: Coursera algorithms week 1 assignment JAVA
 **************************************************************************** */
public class Percolation {
    private final int num;
    private boolean[] opened;
    private final WeightedQuickUnionUF graph;
    private final int top;
    private final int bottom;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Out of bounds.");
        }

        num = n;
        opened = new boolean[n * n + 2];
        graph = new WeightedQuickUnionUF(n * n + 2);
        top = 0;
        bottom = n * n + 1;
        opened[top] = true;
        opened[bottom] = true;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int currentIndex = xyto1D(row, col);
        opened[currentIndex] = true;
        openSites++;
        // if on the first or the last row connect to virtual ends.
        if (row == 1) {
            graph.union(top, currentIndex);
        }
        if (row == num) {
            graph.union(bottom, currentIndex);
        }

        // if the sites are open in any of four adjacent directions unify them
        tryUnion(currentIndex, row - 1, col);
        tryUnion(currentIndex, row, col - 1);
        tryUnion(currentIndex, row, col + 1);
        tryUnion(currentIndex, row + 1, col);

    }

    // If the adjacent four sites are also open we unify them.
    private void tryUnion(int currentIndex, int rowB, int colB) {
        if (rowB > 0 && colB > 0 && rowB <= num && colB <= num && isOpen(rowB, colB)) {
            graph.union(currentIndex, xyto1D(rowB, colB));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return opened[xyto1D(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return (graph.find(top) == graph.find(xyto1D(row, col)));
    }

    // converts 2D coordinates into 1D
    private int xyto1D(int row, int col) {
        return ((row - 1) * num) + col;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return (graph.find(top) == graph.find(bottom));
    }


    // test client (optional)
    public static void main(String[] args) {
        StdOut.println("Go to PercolationStats instead");
    }
}
