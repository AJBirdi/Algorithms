//Built in accordance to the specification at http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;

public class Percolation {	
	//Converts row and column to a number that can access a single dimensional array
	private int location; 
	private int gridSize;
	
	private WeightedQuickUnionUF WQU;
	
	private boolean grid[];
	
	public Percolation(int n){
		if(n <= 0){
			throw new IllegalArgumentException();
		}
		else{
			gridSize = n;
			//Creates grid with 2 extra nodes that will function as virtual top and bottoms 
			//	that are connected to all top and all bottom nodes
			grid = new boolean[(n * n) + 2];
			WQU = new WeightedQuickUnionUF((gridSize * gridSize) + 2);
			
			//Makes extra nodes connect to top and bottom rows
			for(int x = 0; x < gridSize; x++){
				WQU.union(gridSize * gridSize, x);
				WQU.union(((gridSize * gridSize) + 1), ((gridSize * (gridSize -1)) + x));
			}
		}
	}
	
	//Open a site then connect it to adjacent sites that are also open
	public void open(int row, int column) {
		if((row == 0 || column == 0) || (row > gridSize || column > gridSize)){
			throw new IndexOutOfBoundsException();
		}
		
		if(!isOpen(row, column)){
			location = ((row - 1) * gridSize) + (column - 1);
			StdOut.println("This site was opened [" + row + ", " + column + "].");
			grid[location] = true;
			
			//Checks adjacent sites to see if they're open and connect to them if they are
			if((row - 1) >= 1 && isOpen(row - 1, column) && (location - gridSize) >= 0){
				WQU.union(location, location - gridSize);
			}
			else if((row + 1 <= gridSize) && isOpen(row + 1, column) && (location + gridSize <= 24)){
				WQU.union(location, location + gridSize);
			}
			else if((column + 1 <= gridSize) && isOpen(row, column + 1) && (location + 1 <= 24)){
				WQU.union(location, location + 1);
			}
			else if((column - 1 >= 1) && isOpen(row, column - 1) && (location - 1) >= 0){
				WQU.union(location, location - 1);
			}
		}
		else{
			StdOut.println("Cannot open site.");
		}
		
	}
	
	//Checks to see if a site is open
	public boolean isOpen(int row, int column) {
		if((row == 0 || column == 0) || (row > gridSize || column > gridSize)){
			throw new IndexOutOfBoundsException();
		}
		
		location = ((row - 1) * gridSize) + (column - 1);
		if(grid[location] == false){
			return false;
		}
		else{
			return true;
		}		
	}
	
	//Checks to see if a site is closed
	public boolean isFull(int row, int column) {
		if((row == 0 || column == 0) || (row > gridSize || column > gridSize)){
			throw new IndexOutOfBoundsException();
		}
		
		location = ((row - 1) * gridSize) + (column - 1);
		if(grid[location] == false){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Returns number of open sites
	public int numberOfOpenSites() {
		int count = 0;
	
		for(int x = 0; x < (gridSize * gridSize); x++){
			if(grid[x] == true){
				count++;
			}
		}
		return count;
	}
	
	//Checks if there is a path from the any of the top nodes to any of the bottom nodes
	public boolean percolates() {
		//Check if the virtual sites are connected to each other
		return WQU.connected((gridSize * gridSize), ((gridSize * gridSize) + 1));
	}	
}
