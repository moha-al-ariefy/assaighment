package peggame;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Zasquare implements PegGame {
    int[][] board;
    private gamestate gameState;

    public Zasquare(int size) {
        this.board = new int[size][size];
        this.gameState = gameState.IN_PROGRESS; // Assuming GameState is an enum you've defined elsewhere
    }



    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    sb.append("o "); // represents a peg
                } else if (board[i][j] == 0) {
                    sb.append("- "); // represents an empty hole
                } else {
                    sb.append("  "); // represents a wall or occupied spot
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    



    @Override
    public Collection<move> getPossibleMoves() {
        List<move> possibleMoves = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) { // Check if there's a peg at this position
                    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                    for (int[] direction : directions) {
                        int newRow = i + 2 * direction[0];
                        int newCol = j + 2 * direction[1];
                        if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[i].length) {
                            // Check if the destination cell is empty
                            if (board[newRow][newCol] == 0) {
                                possibleMoves.add(new move(new location(i, j), new location(newRow, newCol)));
                            }
                        }
                    }
                }
            }
        }
        return possibleMoves;
    }
    

@Override
public gamestate getgGamestate() {
    int pegCount = 0;
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
            if (board[i][j] == 1) {
                pegCount++;
            }
        }
    }

    if (pegCount == 0) {
        return gamestate.NOT_STARTED;
    } else if (pegCount > 1 && getPossibleMoves().isEmpty()) {
        return gamestate.STALMATE;
    } else if (pegCount == 1) {
        return gamestate.WON;
    } else {
        return gamestate.IN_PROGRESS;
    }
}






    @Override
public void makeMove(move move) throws PegGameException {
    location from = move.getFrom();
    location to = move.getTo();

    if (board[from.getRow()][from.getCol()] != 1 || board[to.getRow()][to.getCol()] != 0) {
        throw new PegGameException("Invalid move");
    }

    board[from.getRow()][from.getCol()] = 0; 
    board[to.getRow()][to.getCol()] = 1; 

}

}
