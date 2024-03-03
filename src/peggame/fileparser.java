package peggame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class fileparser { // here to meet the requirment of reading from a file 
    public static PegGame parseFile(String filename) // the foucs method in this class. 
    throws IOException { /*ioexception is checked when theres an input output is failed
        this happens here when the reader is unable to read the file
        either its unreacheable or even nonextince 
        */
        FileReader firstread =new FileReader(filename); 
        BufferedReader reader = new BufferedReader(firstread);
        // accessing the file through filereader and bufferdreader/
        int size = Integer.parseInt(reader.readLine()); //changing it into integer 
        Zasquare game = new Zasquare(size); // creating the board


        //This starts a loop that will iterate over each row of the game board
        for (int i = 0; i < size; i++) {
            String line = reader.readLine();//this read the row 
            for (int j = 0; j < size; j++) { // this for going over the colum
                /*
                 * this is the secert method that replaces o with 1 
                 * while everything else with 0 for the pegs and holes location
                 * they are all actually 1 and 0 but the to string method in zasuqre change the apperance
                 */
                game.board[i][j] = line.charAt(j) == 'o' ? 1 : 0;
            }
        }

        reader.close();// closing the reader
        return game; //returnning the board
    }


    //this is something i should probably delete but I used it to test the board creation in @test
    public static void testBoard(PegGame game){ 
        System.out.println(game);
    }
}
