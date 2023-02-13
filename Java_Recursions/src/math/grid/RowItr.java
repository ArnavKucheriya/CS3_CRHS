package math.grid;

import math.location.Location;

import java.util.Iterator;

public class RowItr<E> implements Iterator<Location> {
    final private Grid<E> grid;
    private Location loc;
    private boolean init;

    public RowItr(Grid<E> grid, Location start) {
        this.grid = grid;
        loc = start.copy();
        init = true;
    }

    @Override
    public boolean hasNext() {
        return loc.getRow() < grid.getRows()-1;
    }

    @Override
    public Location next() {
        if (init) init = false;
        else loc.nextRow();
        return loc;
    }
}
