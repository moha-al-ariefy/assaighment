package peggame;

public class location {

    private int col;
    private int row;
    private int rowSize;
    private int colSize;

    public location(int row, int col, int rowSize, int colSize) {
        this.row = row;
        this.col = col;
        this.rowSize = rowSize;
        this.colSize = colSize;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        if (row >= 0 && row < rowSize) {
            this.row = row;
        } else {
            throw new IllegalArgumentException("Row value must be within the row size.");
        }
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        if (col >= 0 && col < colSize) {
            this.col = col;
        } else {
            throw new IllegalArgumentException("Column value must be within the column size.");
        }
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }
}
