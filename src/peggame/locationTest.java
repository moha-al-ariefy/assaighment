package peggame;

import org.junit.Test;
import static org.junit.Assert.*;

public class locationTest {
    @Test
    public void testGetCol() {
        int col = 1;

        int row = 2;
        location loc = new location(row, col);

        assertEquals(col, loc.getCol());
    }

    @Test
    public void testGetRow() {
        int col = 1;
        int row = 2;
        location loc = new location(row, col);

        assertEquals(row, loc.getRow());
    }
}
