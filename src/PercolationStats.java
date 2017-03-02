// Built in accordance to the specification at
// http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.ArrayList;
import java.util.List;

public class PercolationStats {

    private int gridSize;
    private int trialCount;
    private double[] percThreshold;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        else {
            gridSize = n;
            trialCount = trials;
            percThreshold = new double[trialCount];

            int randomRow, randomColumn;
            double threshold = 0.0;

            List<Percolation> percObjects = new ArrayList<Percolation>(trialCount);
            for (int x = 0; x < trialCount; x++) {
                Percolation perc = new Percolation(gridSize);

                percObjects.add(perc);

                while (!percObjects.get(x).percolates()) {
                    randomRow = StdRandom.uniform(gridSize) + 1;
                    randomColumn = StdRandom.uniform(gridSize) + 1;

                    if (!percObjects.get(x).isOpen(randomRow, randomColumn)) {
                       percObjects.get(x).open(randomRow, randomColumn);
                    }
                }
                threshold = percObjects.get(x).numberOfOpenSites() / (double) (gridSize * gridSize);
                percThreshold[x] = threshold;
            }
        }
    }

    public double mean() {
        return StdStats.mean(percThreshold);
    }

    public double stddev() {
        return StdStats.stddev(percThreshold);
    }

    public double confidenceLo() {
        return (mean() - (1.96 * stddev())/(Math.sqrt(trialCount)));
    }

    public double confidenceHi() {
        return (mean() + (1.96 * stddev())/(Math.sqrt(trialCount)));
    }

    public static void main(String[] args) {
        PercolationStats percStats;
        if (args.length != 0) {
            percStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }
        else {
            percStats = new PercolationStats(200, 100);
        }

        StdOut.println("Mean :: " + percStats.mean());
        StdOut.println("Standard Deviation :: " + percStats.stddev());
        StdOut.println("Confidence, low :: " + percStats.confidenceLo());
        StdOut.println("Confidence, high :: " + percStats.confidenceHi());
    }
}
