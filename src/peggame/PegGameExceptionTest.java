package peggame;

import org.junit.Assert;
import org.junit.Test;

public class PegGameExceptionTest {
    @Test
    public void testPegGameException() {
        String message = "This is a test exception.";
        try {
            throw new PegGameException(message);
        } catch (PegGameException e) {
            Assert.assertEquals(message, e.getMessage());
        }
    }
}
