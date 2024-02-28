package peggame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Board implements peggame {
    private int rowSize;
    private int colSize;
    private Peg[][] pegs;
    private List<Player> players;
    private Player currentPlayer;

    public Board(int rowSize, int colSize, List<Player> players) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.pegs = new Peg[rowSize][colSize];
        this.players = players;
        this.currentPlayer = players.get(0);  int pegsPerPlayer = (rowSize * colSize) / 5;

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                Player owner = players.get((i * colSize + j) / pegsPerPlayer);
                pegs[i][j] = new Peg(owner, new location(i, j, rowSize, colSize));
                owner.addPeg(pegs[i][j]);
            }
        }
    }
    public int getRowSize() {
        return rowSize;
    }

    public Player getCurrentPlayer() {  
        return currentPlayer;
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
    @Override
public GameState getGameState() {
    int pegCount = 0;
    for (int i = 0; i < rowSize; i++) {
        for (int j = 0; j < colSize; j++) {
            if (pegs[i][j] != null && pegs[i][j].isPresent()) {
                pegCount++;
            }
        }
    }

    if (pegCount == rowSize * colSize) {
        return GameState.NOT_STARTED;
    }

    if (pegCount == 1) {
        return GameState.WON;
    }

    if (pegCount >= 2 && getPossibleMoves(currentPlayer).isEmpty()) {
        return GameState.STALEMATE;
    }

    return GameState.IN_PROGRESS;
}

private boolean isValidMove(location start, location end) {
    int midRow = (start.getRow() + end.getRow()) / 2;
    int midCol = (start.getCol() + end.getCol()) / 2;

    if (end.getRow() >= 0 && end.getRow() < rowSize && end.getCol() >= 0 && end.getCol() < colSize && pegs[end.getRow()][end.getCol()] == null && pegs[midRow][midCol] != null && pegs[midRow][midCol].isPresent()) {
        return true;
    } else {
        return false;
    }
}

public Collection<Move> getPossibleMoves(Player player) {
    List<Move> possibleMoves = new ArrayList<>();

    for (Peg peg : player.getPegs()) {
        location currentLocation = peg.getLocation();
        location[] directions = {new location(currentLocation.getRow()-2, currentLocation.getCol(), rowSize, colSize), new location(currentLocation.getRow()+2, currentLocation.getCol(), rowSize, colSize), new location(currentLocation.getRow(), currentLocation.getCol()-2, rowSize, colSize), new location(currentLocation.getRow(), currentLocation.getCol()+2, rowSize, colSize)};
        for (location end : directions) {
            if (isValidMove(currentLocation, end)) {
                possibleMoves.add(new Move(currentLocation, end, this));
            }
        }
    }

    return possibleMoves;
}

@Override
public void makeMove(Move move) throws PegGameException {
    location start = move.getStart();
    location end = move.getEnd();

    if (!isValidMove(start, end)) {
        throw new PegGameException("Invalid move.");
    }

    Peg peg = pegs[start.getRow()][start.getCol()];
    peg.remove();
    currentPlayer.removePeg(peg);

    int midRow = (start.getRow() + end.getRow()) / 2;
    int midCol = (start.getCol() + end.getCol()) / 2;
    peg = pegs[midRow][midCol];
    peg.remove();
    currentPlayer.removePeg(peg);

    currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
}







}

   

