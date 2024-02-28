package peggame;

import java.util.Collection;

public interface peggame {

    Collection<Move> getPossibleMoves();

  
    GameState getGameState();

   
    void makeMove(Move move) throws PegGameException;
}
