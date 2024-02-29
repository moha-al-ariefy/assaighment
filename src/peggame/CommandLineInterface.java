package peggame;

import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {

    public static void playGame(PegGame game) {
        Scanner scanner = new Scanner(System.in);
        String command;
    
        while (true) {
            System.out.println("Current state:");
            System.out.println(game.toString());
    
            // Display possible moves
            List<Move> possibleMoves = game.getPossibleMoves();
            System.out.println("Possible moves: " + possibleMoves);
    
            System.out.print("Enter your command (move or quit): ");
            command = scanner.nextLine().trim();
    
            if (command.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            } else if (command.startsWith("move")) {
                try {
                    String[] moveParts = command.split(" ");
                    if (moveParts.length != 5) {
                        throw new IllegalArgumentException("Invalid move format: Use 'move row1 col1 row2 col2'");
                    }
    
                    int fromRow = Integer.parseInt(moveParts[1]);
                    int fromCol = Integer.parseInt(moveParts[2]);
                    int toRow = Integer.parseInt(moveParts[3]);
                    int toCol = Integer.parseInt(moveParts[4]);
    
                    game.makeMove(new Move(new Location(fromRow, fromCol), new Location(toRow, toCol)));
    
                    // Switch the current player after each move
                    game.switchPlayer();
    
                    // Display the board after the move
                    System.out.println("Current state:");
                    System.out.println(game.toString());
    
                } catch (NumberFormatException e) {
                    System.err.println("Invalid move format: Numbers expected for row and column.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                } catch (PegGameException e) {
                    System.err.println("Invalid move: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid command! Enter 'move' or 'quit'.");
            }
    
            // Check game state after each move
            if (game.getGameState() == GameState.WON) {
                System.out.println("Congratulations! You won the game.");
                break;
            } else if (game.getGameState() == GameState.STALEMATE) {
                System.out.println("Stalemate! No more valid moves.");
                break;
            }
        }
    }
    
    
}
