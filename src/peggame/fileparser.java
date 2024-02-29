package peggame;

import java.io.*;

public class fileparser {

    public static PegGame parseFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int rows = Integer.parseInt(reader.readLine());

            char[][] board = new char[rows][];

            for (int i = 0; i < rows; i++) {
                board[i] = reader.readLine().toCharArray();
            }

            return new Zasquare(board);
        }

    }
    
}
