package math.location;

import java.util.Iterator;
import java.util.Objects;

public class Location {
    private int row;
    private int col;

    public Location() {
        row = 0;
        col = 0;
    }

    public Location(int r, int c) {
        this.row = r;
        this.col = c;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getX() {
        return col;
    }

    public int getY() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Location getRight() {
        return new Location(row, col +1);
    }

    public Location getDown() {
        return new Location(row +1, col);
    }

    public Location getLeft() {
        return new Location(row, col -1);
    }

    public Location getUp() {
        return new Location(row -1, col);
    }

    public void nextRow() {
        row += 1;
    }

    public void nextCol() {
        col += 1;
    }

    public void prevRow() {
        row -= 1;
    }

    public void prevCol() {
        col -= 1;
    }

    public Location nextRow(Location loc) {
        loc.setRow(row +1);
        loc.setCol(col);
        return loc;
    }

    public Location nextCol(Location loc) {
        loc.setRow(row);
        loc.setCol(col +1);
        return loc;
    }

    public Location prevRow(Location loc) {
        loc.setRow(row -1);
        loc.setCol(col);
        return loc;
    }

    public Location prevCol(Location loc) {
        loc.setRow(row);
        loc.setCol(col -1);
        return loc;
    }

    public boolean sameRow(Location loc) {
        return row == loc.getRow();
    }

    public boolean sameCol(Location loc) {
        return col == loc.getCol();
    }

    public boolean sameDiagonal(Location loc) {
           return Math.abs(row - loc.getRow()) == Math.abs(col - loc.getCol());
    }

    public boolean collide(Location loc) {
        return sameCol(loc) || sameRow(loc) || sameDiagonal(loc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return row == location.row && col == location.col;
    }

    public Location copy() {
        return new Location(row, col);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    private class Itr implements Iterator<Location> {
        Location loc;

        Itr(Location loc) {
            this.loc = loc;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Location next() {
            return null;
        }
    }
}
