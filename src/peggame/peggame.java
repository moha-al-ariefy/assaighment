package peggame;

import java.util.Collection;

public interface peggame {

    public Collection<Move> getPossibleMoves(Player player);

  
    GameState getGameState();

   
    void makeMove(Move move) throws PegGameException;
}
