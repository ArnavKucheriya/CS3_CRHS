package math.grid;

import math.location.Location;

import java.util.Iterator;

public class ColItr<E> implements Iterator<Location> {
    private final Grid<E> grid;
    private Location loc;
    private boolean init;

    public ColItr(Grid<E> grid, Location start) {
        this.grid = grid;
        loc = start.copy();
        init = true;
    }

    @Override
    public boolean hasNext() {
        return loc.getCol() < grid.getCols()-1;
    }

    @Override
    public Location next() {
        if (init) init = false;
        else loc.nextCol();
        return loc;
    }
}
