package peggame;

import java.util.List;

public interface PegGame {

    /**
     * remmber this must return vaild moves as a collction .
     * @return all possible moves
     */
    List<Move> getPossibleMoves();

    /**
      what is the game states>.
     @return the current game state (NOT_STARTED, IN_PROGRESS, STALEMATE, or WON)
     */
    GameState getGameState();

    /**
     making the move.
     
      @param move its supposed to get the move that the user want to make.
      @throws PegGameException if its not a possible move
     */
    void makeMove(Move move) throws PegGameException;

    /**
     * I will think about this later
     */
    String toString();

    void switchPlayer();

}
