package peggame;

import java.util.Collection;

public interface PegGame {

    Collection<move> getPossibleMoves();
    
    gamestate getgGamestate();

    void makeMove(move move) throws PegGameException;


} 