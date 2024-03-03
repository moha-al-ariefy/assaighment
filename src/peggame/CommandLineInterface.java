package peggame;

import java.util.Scanner;
//the cli which is the thing that makes the game work.
public class CommandLineInterface {

    public static void playGame(PegGame game) { // the main method for the game you could say this is a simple ui for the game.
        Scanner s = new Scanner(System.in);//creating a scanner to get input from the player.
        while (true) {//the loop to make the game contuine running
            System.out.println(game.toString());/*
           after entering the file location in final main it will use the fileparser
           to create the board then implent the board inside the zsquare method to make it playable.
           basicly upcasting! 
            */ 
            System.out.print("Enter command(move or quit) could move in all the sides: ");
            /* here it take the command wither to move or to quit 
             * it will be more clear down but simply move it must be in this format 
             * move r1 c1 r2 c2 (like it was mentioned in the requirments)
             */
            String command = s.nextLine(); // here we store the input in command to use it.
            String[] parts = command.split(" ");/* 
            store it in a list by using the split command so that its usable
            this is the only i found to make it work like this 
            */ 
            

            //now here for the real work we will chack the input first if it is quit this what will happen 
            if (parts[0].equals("quit")) { // first here cheacking it if its quit (it must be spelled corectly)
                System.out.println("see you later :( "); //then you quit the game and break the loop
                break;
            } else if (parts[0].equals("move")) {
                /*now for the real work MOVE well this is the real part after entering 
                the move command and the new location to move the peg to*/
                try { // try catch to avoid breaking the loop

                    int r1 = Integer.parseInt(parts[1]);  // this is method is used to conver the string to integer.
                    int c1 = Integer.parseInt(parts[2]);
                    int r2 = Integer.parseInt(parts[3]);
                    int c2 = Integer.parseInt(parts[4]);

                    move move = new move(new location(r1, c1), new location(r2, c2));
                    // now this uses our move class to create a move object to use it int the make move.
                    game.makeMove(move); // this wil become much clearer in zasqaure class.

                    // now its really bad to make the game run forever so it will cheak the game state after each move
                    if (game.getgGamestate() == gamestate.STALMATE || game.getgGamestate() == gamestate.WON) {
                        System.out.println("Game over! State: " + game.getgGamestate());
                        break;
                    }
                    /*  this is the catches two for for the movemnt commands and one for the
                     move which we created by ourself to handle the invalid moves.
                    */
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
        s.close(); // closing the scanner
    }

    public static void main(String[] args) {
        PegGame game = new Zasquare(10); // here the game is created
        playGame(game);
    }
}
