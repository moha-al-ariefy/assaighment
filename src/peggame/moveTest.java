package peggame;

import org.junit.Test;
import static org.junit.Assert.*;

public class moveTest {
    @Test
    public void testGetFrom() {
        location from = new location(0, 0);
        location to = new location(1, 1);
        move move = new move(from, to);

        assertEquals(from, move.getFrom());
    }

    @Test
    public void testGetTo() {
        location from = new location(0, 0);
        location to = new location(1, 1);
        move move = new move(from, to);

        assertEquals(to, move.getTo());
    }
}
