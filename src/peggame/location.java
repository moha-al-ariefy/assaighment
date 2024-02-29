package peggame;

public class Location {
    private final int row;
    private final int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object second) {
        if (this == second) return true;
        if (second == null || getClass() != second.getClass()) return false;

        Location location = (Location) second;

        return col == location.col && row == location.row;
    }

    @Override
    public String toString() {
        return "(" + col + ", " + row + ")";
    }
}
