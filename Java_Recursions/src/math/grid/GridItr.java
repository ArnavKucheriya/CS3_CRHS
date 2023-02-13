package math.grid;

import math.location.Location;

import java.util.Iterator;

public class GridItr<E> implements Iterator<Location> {
    private final Grid<E> grid;
    private Location spot;
    private boolean init;

    public GridItr(Grid<E> grid, Location start) {
        this.grid = grid;
        spot = start.copy();
        init = true;
    }

    @Override
    public boolean hasNext() {
        return !(spot.getRow() == grid.getRows()-1 &&
                spot.getCol() == grid.getCols()-1);
    }

    @Override
    public Location next() {
        if (!hasNext())
            throw new IllegalStateException();
        else if (spot.getCol() == grid.getCols()-1) {
            spot.nextRow();
            spot.setCol(-1);
        }
        if (init) init = false;
        else spot.nextCol();
        return spot;
    }
}
