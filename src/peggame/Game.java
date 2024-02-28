package peggame;

import java.util.Collection;

public class Game implements peggame {
    private GameState gameState;

    public Game() {
        this.gameState = GameState.NOT_STARTED;
    }

    @Override
    public Collection<Move> getPossibleMoves() {
  
        return null;
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public void makeMove(Move move) throws PegGameException {
       
    }
}
