package peggame;

import java.util.Collection;

public class Board {
    private int rowSize;
    private int colSize;
    private Peg[][] pegs;

    public Board(int rowSize, int colSize) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.pegs = new Peg[rowSize][colSize];

        // Initialize the board with pegs
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                pegs[i][j] = new Peg();
            }
        }
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    public void removePeg(location location) {
        int row = location.getRow();
        int col = location.getCol();

        if (row >= 0 && row < rowSize && col >= 0 && col < colSize) {
            pegs[row][col] = null;
        } else {
            throw new IllegalArgumentException("Location must be within the board size.");
        }
    }

   
}
