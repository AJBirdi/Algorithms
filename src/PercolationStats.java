//Built in accordance to the specification at 
//http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Math;

public class PercolationStats {
	
	private int gridSize;
	private int trialCount;
	private double[] percThreshold;
	
	public PercolationStats(int n, int trials){
		if(n <= 0 || trials <= 0){
			throw new IllegalArgumentException();
		}
		else{
			gridSize = n;
			trialCount = trials;
			percThreshold = new double[trialCount];
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
	
	public static void main (String[] args){
		PercolationStats percStats;
		int randomRow, randomColumn;
		
		if(args.length != 0){
			percStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		}
		else{
			percStats = new PercolationStats(2, 100000);
		}
		
		for(int x = 0; x < percStats.trialCount; x++){
			Percolation perc = new Percolation(percStats.gridSize);
			
			while(!perc.percolates()){
				randomRow = StdRandom.uniform(percStats.gridSize) + 1;
				randomColumn = StdRandom.uniform(percStats.gridSize) + 1;
				
				if(!perc.isOpen(randomRow, randomColumn)){
					perc.open(randomRow, randomColumn);
				}
			}
			double threshold = perc.numberOfOpenSites() / (double)(percStats.gridSize * percStats.gridSize);
			percStats.percThreshold[x] = threshold;
		}
		
		StdOut.println("Mean :: " + percStats.mean());
		StdOut.println("Standard Deviation :: " + percStats.stddev());
		StdOut.println("Confidence, low :: " + percStats.confidenceLo());
		StdOut.println("Confidence, high :: " + percStats.confidenceHi());
	}
}
