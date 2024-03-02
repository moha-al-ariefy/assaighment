package peggame;

import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException; // Add this line
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail; // Add this line

public class fileparserTest {

    @Test
    public void testParseFile_validFile() throws IOException {
        String filePath = "src\\peggame\\game1.txt";
        String expectedBoard = "o o - o o\no o - o o\no o - o o\no o - o o\no o - o o";

        PegGame game = fileparser.parseFile(filePath);

        String actualBoard = game.toString();

        assertEquals(expectedBoard, actualBoard);
    }

    @Test(expected = FileNotFoundException.class) 
    public void testParseFile_invalidFile() throws IOException {
        String filePath = "invalid_file.txt";

        fileparser.parseFile(filePath);
    }
}
