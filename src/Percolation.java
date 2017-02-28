// Built in accordance to the specification at 
// http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {	
    private int gridSize;
    private int openSiteCount;

    private WeightedQuickUnionUF weightedQuickUnion;

    private boolean[] grid;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        else {
            gridSize = n;
            // Creates grid with 2 extra nodes that will function as virtual top and bottoms
            //  that are connected to all top and all bottom nodes
            grid = new boolean[(n * n) + 2];
            weightedQuickUnion = new WeightedQuickUnionUF((gridSize * gridSize) + 2);

            // Makes virtual nodes connect to top and bottom rows
            for (int x = 0; x < gridSize; x++) {
                weightedQuickUnion.union(gridSize * gridSize, x);
                weightedQuickUnion.union(((gridSize * gridSize) + 1), ((gridSize * (gridSize -1)) + x));
            }
        }
    }

    // Open a site then connect it to adjacent sites that are also open
    public void open(int row, int column) {
        if ((row == 0 || column == 0) || (row > gridSize || column > gridSize)) {
            throw new IndexOutOfBoundsException();
        }

        if (!isOpen(row, column)) {
            // Creates number that can be used to access a single dimensional array that represents
            //  two dimensional data
            int gridLocation = ((row - 1) * gridSize) + (column - 1);
            grid[gridLocation] = true;
            openSiteCount++;

            // Checks if top element is open and connects if it is
            if ((row - 1) >= 0 && (gridLocation - gridSize) >= 0) {
                if ((row - 1) > 0 && isOpen(row - 1, column)) {
                    weightedQuickUnion.union(gridLocation, (gridLocation - gridSize));
                }
            }
            // Checks if bottom element is open and connects if it is
            if (row + 1 <= gridSize && gridLocation + gridSize <= gridSize * gridSize) {
                if (row + 1 <= gridSize && isOpen(row + 1, column)) {
                    weightedQuickUnion.union(gridLocation, gridLocation + gridSize);
                }
            }
            // Checks if right element is open and connects if it is
            if ((column + 1 <= gridSize) && (gridLocation + 1 <= (gridSize * gridSize - 1))) {
                if (column + 1 <= gridSize && isOpen(row, column + 1)) {
                    weightedQuickUnion.union(gridLocation, gridLocation + 1);
                }
            }
            // Checks if left element is open and connects if it is
            if ((column - 1 >= 0) && (gridLocation - 1) >= 0) {
                if (column - 1 > 0 && isOpen(row, column - 1)) {
                    weightedQuickUnion.union(gridLocation, gridLocation - 1);
                }
            }
        }
    }

    // Checks to see if a site is open
    public boolean isOpen(int row, int column) {
        if ((row <= 0 || column <= 0) || (row > gridSize || column > gridSize)) {
            throw new IndexOutOfBoundsException();
        }

        int gridLocation = ((row - 1) * gridSize) + (column - 1);
        return grid[gridLocation];
    }

    // Checks to see if a site is connected to the top virtual node
    public boolean isFull(int row, int column) {
        if ((row <= 0 || column <= 0) || (row > gridSize || column > gridSize)) {
            throw new IndexOutOfBoundsException();
        }

        int gridLocation = ((row - 1) * gridSize) + (column - 1);
        if (row > 1) {
            return weightedQuickUnion.connected(gridLocation, gridSize * gridSize);
        }
        else {
               return isOpen(row, column);
        }
    }

    // Returns number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // Checks if there is a path from the any of the top nodes to any of the bottom nodes
    public boolean percolates() {
        if (gridSize == 1) {
            return false;
        }
        return weightedQuickUnion.connected((gridSize * gridSize), ((gridSize * gridSize) + 1));
    }
}