package peggame;

import java.util.Collection;

public class Move  {
    private location start;
    private location end;
    private Board board;

    public Move(location start, location end, Board board) {
        this.start = start;
        this.end = end;
        this.board = board;
    }

    public location getStart() {
        return start;
    }

    public location getEnd() {
        return end;
    }

    public void execute() {
        int midRow = (start.getRow() + end.getRow()) / 2;
        int midCol = (start.getCol() + end.getCol()) / 2;
        location midLocation = new location(midRow, midCol, board.getRowSize(), board.getColSize());

        board.removePeg(midLocation);
    }

    
}
