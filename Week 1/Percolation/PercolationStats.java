import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/* *****************************************************************************
 *  Name: Nishan Poudel
 *  Date: 31st May 2020
 *  Description: Coursera algorithms week 1 assignment JAVA
 **************************************************************************** */

public class PercolationStats {

    private static final double CONFIDENCE_NUM = 1.96;
    private final int trialsVal;
    private final double[] thresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("Out of bounds");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("Trials must be 1 or greater than that.");
        }

        trialsVal = trials;
        thresholds = new double[trials];
        // Run independent trials.
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }

            }
            thresholds[i] = percolation.numberOfOpenSites() * (1.0 / (n * n));
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double mean = mean(), stddev = stddev();
        return mean - ((CONFIDENCE_NUM * stddev) / Math.sqrt(trialsVal));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double mean = mean(), stddev = stddev();
        return mean + ((CONFIDENCE_NUM * stddev) / Math.sqrt(trialsVal));
    }

    // test client (see below)
    public static void main(String[] args) {

        int num = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats n = new PercolationStats(num, trials);

        StdOut.println("mean = " + n.mean());
        StdOut.println("stddev = " + n.stddev());
        StdOut.println(
                "95% confidence interval = " + "[" + n.confidenceLo() + ", " + n.confidenceHi()
                        + "]");
    }
}
