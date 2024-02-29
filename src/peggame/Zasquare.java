package peggame;

import java.util.ArrayList;
import java.util.List;

public class Zasquare implements PegGame {

    private char[][] board;

    public Zasquare(char[][] board) {
        this.board = board;
    }

    

    @Override
    public GameState getGameState() {
        int pegCount = 0;
        for (char[] row : board) {
            for (char c : row) {
                if (c == 'o') {
                    pegCount++;
                }
            }
        }

        if (pegCount == 1) {
            return GameState.WON;
        } else if (getPossibleMoves().isEmpty()) {
            return GameState.STALEMATE;
        } else {
            return GameState.IN_PROGRESS;
        }
    }
    private char currentPlayer = 'o';  // 'o' for player 1 and 'O' for player 2
    @Override
    public List<Move> getPossibleMoves() {
        List<Move> moves = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == currentPlayer) {
                    moves.addAll(findValidMoves(new Location(row, col)));
                }
            }
        }
        return moves;
    }
    
    private List<Move> findValidMoves(Location peg) {
        List<Move> moves = new ArrayList<>();
        int row = peg.getRow();
        int col = peg.getCol();
    
        // Check jumps in all four directions
        if (col < board[row].length - 2 && board[row][col + 1] != '.' && board[row][col + 2] == '.') {
            moves.add(new Move(peg, new Location(row, col + 2)));
        }
        if (col > 1 && board[row][col - 1] != '.' && board[row][col - 2] == '-') {
            moves.add(new Move(peg, new Location(row, col - 2)));
        }
        if (row < board.length - 2 && board[row + 1][col] != '.' && board[row + 2][col] == '-') {
            moves.add(new Move(peg, new Location(row + 2, col)));
        }
        if (row > 1 && board[row - 1][col] != '.' && board[row - 2][col] == '-') {
            moves.add(new Move(peg, new Location(row - 2, col)));
        }
    
        return moves;
    }
    
    
    @Override
public void makeMove(Move move) throws PegGameException {
    Location from = move.getFrom();
    Location to = move.getTo();

    if (!isValidMove(from, to)) {
        throw new PegGameException("Invalid move!");
    }

    board[from.getRow()][from.getCol()] = '-';
    board[to.getRow()][to.getCol()] = currentPlayer;

    int jumpedRow = (from.getRow() + to.getRow()) / 2;
    int jumpedCol = (from.getCol() + to.getCol()) / 2;
    board[jumpedRow][jumpedCol] = '-';
}


    private boolean isValidMove(Location from, Location to) {
        int row = from.getRow();
        int col = from.getCol();
    
        if (!from.equals(to) || board[row][col] != currentPlayer || board[to.getRow()][to.getCol()] != '-') {
            return false;
        }
    
        int rowDiff = Math.abs(from.getRow() - to.getRow());
        int colDiff = Math.abs(from.getCol() - to.getCol());
    
        // Check if the jump is diagonal or a single step
        if (rowDiff != colDiff || rowDiff != 2) {
            return false;
        }
    
        // Check if the jumped peg exists
        int jumpedRow = (from.getRow() + to.getRow()) / 2;
        int jumpedCol = (from.getCol() + to.getCol()) / 2;
        return board[jumpedRow][jumpedCol] == currentPlayer;
    }
    
    @Override
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'o') ? 'O' : 'o';
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : board) {
            for (char c : row) {
                sb.append(c);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    
}
    