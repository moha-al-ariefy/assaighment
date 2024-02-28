package peggame;

import java.util.Collection;

public class Move implements peggame {
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

    @Override
    public Collection<Move> getPossibleMoves() {
        // TODO make this method show the posiible directions for the peg to move in (every player has one move pear turn)
        throw new UnsupportedOperationException("Unimplemented method 'getPossibleMoves'");
    }

    @Override
    public GameState getGameState() {
        // TODO make this use the game state enum 
        throw new UnsupportedOperationException("Unimplemented method 'getGameState'");
    }

    @Override
    public void makeMove(Move move) throws PegGameException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeMove'");
    }
}
