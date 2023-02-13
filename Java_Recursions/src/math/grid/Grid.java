package math.grid;

import math.location.Location;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Grid<E> implements Iterable<Location> {
    private E[][] grid;
    public final static int ROW = 0; // return a iterator of rows, cols or the matrix
    public final static int COL = 1;
    public final static int MATRIX = 2;
    private int current;
    private Location start;
    public Grid(E[][] grid) {
        this.grid = grid;
        setIterator(2);
    }

    public void setGrid(E[][] grid) {
        this.grid = grid;
    }

    public E[][] getGrid() {
        return grid;
    }

    public void setSpot(Location loc, E val) {
        grid[loc.getRow()][loc.getCol()] = val;
    }

    public E getSpot(Location loc) {
        if (!inMatrix(loc))
            throw new IndexOutOfBoundsException();
        return grid[loc.getRow()][loc.getCol()];
    }

    public int getRows() {
        return grid.length;
    }

    public int getCols() {
        return grid[0].length;
    }

    public boolean inMatrix(Location loc ) {
        int r = loc.getRow();
        int c = loc.getCol();
        return r >= 0 && c >= 0 &&
                r < grid.length && c < grid[0].length;
    }

    public void clear() {
        grid = (E[][]) new Object[grid.length][grid[0].length];
    }

    public void setIterator(int iteratorType) {
        setIterator(iteratorType, new Location());
    }

    public void setIterator(int iteratorType, Location start) {
        if (iteratorType < 0 || iteratorType > MATRIX)
            throw new IllegalStateException();
        this.start = start;
        current = iteratorType;
    }

    public void setStart(Location start) {
        if (start == null)
            throw new NoSuchElementException();
        this.start = start;
    }

    public Grid<E> copy() throws CloneNotSupportedException {
        return new Grid<>(grid.clone());
    }

    @Override
    public Iterator<Location> iterator() {
        if (current == ROW)
            return new RowItr<>(this, start);
        else if (current == COL)
            return new ColItr<>(this, start);
        return new GridItr<>(this, start);
    }
}
