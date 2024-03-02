package peggame;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class finalmain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();

        try {
            PegGame game = fileparser.parseFile(filename);
            CommandLineInterface.playGame(game);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        } finally {
            scanner.close();
        }
    }
}
