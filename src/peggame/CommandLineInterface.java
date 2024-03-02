package peggame;

import java.util.Scanner;

public class CommandLineInterface {

    public static void playGame(PegGame game) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(game.toString());
            System.out.print("Enter command(move or quit) could move in all the sides: ");
            String command = scanner.nextLine();
            String[] parts = command.split(" ");

            if (parts[0].equals("quit")) {
                System.out.println("Goodbye!");
                break;
            } else if (parts[0].equals("move")) {
                try {
                    int r1 = Integer.parseInt(parts[1]);
                    int c1 = Integer.parseInt(parts[2]);
                    int r2 = Integer.parseInt(parts[3]);
                    int c2 = Integer.parseInt(parts[4]);
                    move move = new move(new location(r1, c1), new location(r2, c2));
                    game.makeMove(move);
                    if (game.getgGamestate() == gamestate.STALMATE || game.getgGamestate() == gamestate.WON) {
                        System.out.println("Game over! State: " + game.getgGamestate());
                        break;
                    }
                } catch (PegGameException e) {
                    System.out.println("Invalid move: " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid command: " + command);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid command: " + command);
                }
            } else {
                System.out.println("Invalid command: " + command);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        PegGame game = new Zasquare(5);
        playGame(game);
    }
}
