package peggame;

import java.io.IOException;
import java.util.Scanner;

public class peggamemain {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter filename: ");
        String fileName = scanner.nextLine();

        PegGame game = fileparser.parseFile(fileName);

        System.out.println("Welcome to Peg Game!");
        System.out.println("Current board:\n" + game);

        CommandLineInterface.playGame(game);

        System.out.println("Thank you for playing!");
    }
}
