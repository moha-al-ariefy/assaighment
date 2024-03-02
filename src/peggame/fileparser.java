package peggame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class fileparser {
    public static PegGame parseFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        int size = Integer.parseInt(reader.readLine());
        Zasquare game = new Zasquare(size);

        for (int i = 0; i < size; i++) {
            String line = reader.readLine();
            for (int j = 0; j < size; j++) {
                game.board[i][j] = line.charAt(j) == 'o' ? 1 : 0;
            }
        }

        reader.close();
        return game;
    }

    public static void testBoard(PegGame game){
        System.out.println(game);
    }
}
