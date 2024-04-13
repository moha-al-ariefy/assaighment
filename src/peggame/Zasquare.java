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
    public gamestate getgGamestate() {// the method that return the game state.
        int pegCount = 0; // making a varible to store the peg amount
        for (int i = 0; i < board.length; i++) { // cheacking rows
            for (int j = 0; j < board[i].length; j++) { // cheacking cols
                if (board[i][j] == 1) { // 1 means theres a peg
                    pegCount++; // rise the amount of pegs by now
                }
            }
        }

        if (pegCount == 0) { // if 0
            return gamestate.NOT_STARTED; // game not started yet
        } else if (pegCount > 1 && getPossibleMoves().isEmpty()) { // this if theres pegs and theres no posible move 
            return gamestate.STALMATE;// the means the game ended(kinda means lost)
        } else if (pegCount == 1) { // if theres exactly one peg
            return gamestate.WON; // means won
        } else {
            return gamestate.IN_PROGRESS; // otherwise still in progress
        }
    }

    @Override
    public void makeMove(move move) throws PegGameException {
        location from = move.getFrom(); // a variable to store from
        location to = move.getTo(); // a variable to store to
    
        int distance = Math.abs(from.getRow() - to.getRow()) + Math.abs(from.getCol() - to.getCol());
    
        // Check if the move is more than 2 spaces
        if (distance > 2) {
            throw new PegGameException("Invalid move: Move is more than 2 spaces.");
        }
    
        if (board[from.getRow()][from.getCol()] != 1 || board[to.getRow()][to.getCol()] != 0) {
            throw new PegGameException("Invalid move: Must move from a peg to a hole.");
        }
    
        int middleRow = from.getRow() + (to.getRow() - from.getRow()) / 2;
        int middleCol = from.getCol() + (to.getCol() - from.getCol()) / 2;
        board[middleRow][middleCol] = 0;
    
        board[from.getRow()][from.getCol()] = 0; // original point to 0 (hole)
        board[to.getRow()][to.getCol()] = 1; // new point to 1 (peg)
    }
    
}
