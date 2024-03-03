package peggame;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Project1Main_java {

    // the main method that starts the game 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //the scanner to get the user input for the gamer
        System.out.print("enter the filepath: ");
        String filepath = scanner.nextLine(); // saving the input inside the filename

        try { //try catch to avoid breaking the code

            PegGame game = fileparser.parseFile(filepath); // putting the file name from the scanner to create the board 
            CommandLineInterface.playGame(game);//now using the cli to run the game now with board ready 
        } catch (FileNotFoundException e) { // if the file not find 
            System.out.println("give a valid file path :( : " + filepath);
        } catch (IOException e) { // this when input output is failed or interrupted here main foucs if reading file didnt work
            System.out.println("Error while reading the file: " + filepath);
        } finally {
            scanner.close(); //closing the scanner
        }
    }
}
