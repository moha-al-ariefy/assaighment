package peggame;

public class location {

    private int col;
    private int row;
    private int boardSize;

    public location(int row, int col, int boardSize) {
        this.row = row;
        this.col = col;
        this.boardSize = boardSize;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        if (row >= 0 && row < boardSize) {
            this.row = row;
        } else {
            throw new IllegalArgumentException("Row value must be within the board size.");
        }
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        if (col >= 0 && col < boardSize) {
            this.col = col;
        } else {
            throw new IllegalArgumentException("Column value must be within the board size.");
        }
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
}
