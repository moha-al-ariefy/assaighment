package peggame;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ZasquareTest {
    private Zasquare zasquare;

    @Before
    public void setup() {
        zasquare = new Zasquare(5);
    }

    @Test
    public void testGetPossibleMoves() {
        Collection<move> moves = zasquare.getPossibleMoves();
        // Add assertions based on your game rules
    }

    @Test
    public void testGetgGamestate() {
        gamestate state = zasquare.getgGamestate();
        // Add assertions based on your game rules
    }

    @Test
    public void testMakeMove() {
        move move = new move(new location(0, 0), new location(0, 2));
        try {
            zasquare.makeMove(move);
            // Add assertions to check the state of the board
        } catch (PegGameException e) {
            Assert.fail("Move should be valid");
        }
    }

    @Test
    public void testToString() {
        String boardString = zasquare.toString();
    }
}
