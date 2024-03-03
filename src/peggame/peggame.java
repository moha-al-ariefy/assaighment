package peggame;

import java.util.Collection;

public interface PegGame { // the interface 

    Collection<move> getPossibleMoves(); // this is a list of moves that could be used 
    
    gamestate getgGamestate(); // geting the game state 

    void makeMove(move move) throws PegGameException; // this using the move to move the peg in the board.


} 