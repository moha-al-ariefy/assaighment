package peggame;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Zasquare implements PegGame {
    int[][] board; // creating the board as a list (I tried many this was easily the easiest one.)
    private gamestate gameState;// for using the game state enums

    public Zasquare(int size) { // the constructor for creating the board frame and putting the defult game
                                // state
        this.board = new int[size][size];
        this.gameState = gameState.IN_PROGRESS;
    }

    @Override
    public String toString() {
        String result = ""; // this for presenting the board as the form of 0 and 1 is not the best
        for (int i = 0; i < board.length; i++) { // loop for rows
            for (int j = 0; j < board[i].length; j++) { // loop for cols
                if (board[i][j] == 1) { // the 1 represnts a peg
                    result += "o "; // so we add O
                } else if (board[i][j] == 0) { // 0 represnts a hole
                    result += " - "; // so we add -
                }
            }
            result += "\n"; // creating a new line after every full row to have the 2d shape .
        }
        return result;
    }

    @Override
    public Collection<move> getPossibleMoves() {
        List<move> possibleMoves = new ArrayList<>(); // creating a list with the possible moves
        for (int i = 0; i < board.length; i++) { // going through rows
            for (int j = 0; j < board[i].length; j++) { // going through cols
                if (board[i][j] == 1) { // Check if there's a peg at this position
                    int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // a list with the 4 directions the peg could move in
                    for (int[] direction : directions) { // a loop over each direction
                        // claclute the new colum index
                        int newRow = i + 2 * direction[0];  
                        int newCol = j + 2 * direction[1];
                        if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[i].length) {
                            // Check if the destination cell is empty
                            if (board[newRow][newCol] == 0) {
                                possibleMoves.add(new move(new location(i, j), new location(newRow, newCol))); // add this move to the list
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

        int jumpRow = from.getRow() + (to.getRow() - from.getRow()) / 2;
        int jumpCol = from.getCol() + (to.getCol() - from.getCol()) / 2;
        board[jumpRow][jumpCol] = 0;

        board[from.getRow()][from.getCol()] = 0;
        board[to.getRow()][to.getCol()] = 1;
    }

}
