package peggame;
// this is the the view class
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import peggame.pegcont;
import peggame.Zasquare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class pggui extends Application {
    private Zasquare game;
    GridPane board;
    private Label statusbar;
    private pegcont controller;

    Circle selectedPeg = null;
    int selectedRow = -1;
    int selectedCol = -1;
    @Override
    public void start(Stage primaryStage) {
        // Initialize the game
        initializeGame(primaryStage);

        // Set up the game board
        setupBoard();

        // Update the game status
        updateStatus();

        // Create the controller and connect it to the view
        controller = new pegcont(game, this);

        // Create the scene and show it
        createScene(primaryStage);
    }

    
/*
 * this is the class responsiable for Initializing the game it works by
 * first it starts with file chooser then create the boaed usuing the file parser function and zasqyuare.
 */
    private void initializeGame(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();// here we create am obj for file chooser to use it.
        //file chooser is similaer to filereader but insted of already assigning one you choose it by yourself.
        //(its a javafx function)

        fileChooser.setInitialDirectory(new File("activ3/saves"));
        // this line makes it start choosing from a curtain directory called saves.

        File file = fileChooser.showOpenDialog(primaryStage);
        // this is another function of filechooser this one in simple words open the file and allow 
        //you to choose from it and then store it inside file.

        if (file != null) { // if the file is empity the value would be null
            try {// try and catch to avoid crash
                game = fileparser.parseFile(file.getPath());// now using the file parser function we created last activ we 
                //create our board
            } catch (IOException e) {// io exception in case the file wasnt found.
                showErrorDialog("Could not load game from file.");
            }
        }
    }
    
    private void updateStatus() { // this function constantly update the game status using the getgGamestate method in zasqaure.
        if (statusbar == null) {
            statusbar = new Label();
        }
        statusbar.setText("Game Status: " + game.getgGamestate());
    }
    private void setupBoard() {
        board = new GridPane();// using gridpane like what is asked in the requirments.

        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board[i].length; j++) {
                Circle circle = newcCircle(i, j);
                board.add(circle, j, i);
            }
        }
        // this code above is similar to the code i used in the file parser and in the command line interface 
        //where here it replace 1 and 0s with circles (closest thing to a peg)
    }

    private Circle newcCircle(int row, int col) {
        Circle circle = new Circle(20); // here creating our circles.
        if (game.board[row][col] == 1) { // an if statment to cheack if its one then its black(a peg)
            circle.setFill(Color.BLACK);
        } else {
            circle.setFill(Color.GRAY);// else gray which is much viewable 
        }
        circle.setOnMouseClicked(event -> handleMouseClick(row, col)); // this line sets an event handler for the circles which will be further explAINED down.
        return circle;// now we return the circle to use
    }

    private void createScene(Stage mainStage) {
        BorderPane shelf = new BorderPane();
        // here we create a bordaer pane obj we want it for its 5 areas (we are only using top bottom and center)

        // Create the Load Game, Save Game, and Close buttons
        Button loadButton = new Button("Load Game");
        loadButton.setOnAction(event -> loadGame());
        Button saveButton = new Button("Save Game");
        saveButton.setOnAction(event -> saveGame());
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> closeGame(mainStage));
        // event -> ####### this is lambda expersion. it take takes the event which is here the mouse click and call the function
        // Create the game state box
        HBox gameStateBox = new HBox(statusbar); // first thing above the statues bar
        gameStateBox.setSpacing(10);

        // Create the button box
        HBox buttonBox = new HBox(loadButton, saveButton, closeButton); // and here are the buttons with 30 as spacing to not be to close to each other
    buttonBox.setSpacing(30);


        // Add the board, game state box, and button box to the BorderPane
        shelf.setCenter(board); // the board in the center 
        shelf.setTop(gameStateBox); // the game statue bar on the top 
        shelf.setBottom(buttonBox); // and the buttons down

        Scene scene = new Scene(shelf); // creating the scene 
        mainStage.setScene(scene);
        mainStage.show(); // and then showing it to be visable

    }

    private void closeGame(Stage primaryStage) { // one of weirdest requriments a close button 
        // Close the game and the stage
        primaryStage.close();
    }
    
    private void loadGame() {// the load game function 
        FileChooser fileChooser = new FileChooser(); // its basicly the same as creating a board again
        fileChooser.setInitialDirectory(new File("activ3/saves"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try (Scanner scanner = new Scanner(file)) {
                int size = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                game.board = new int[size][size];

                for (int i = 0; i < size; i++) {
                    String line = scanner.nextLine();
                    for (int j = 0; j < size; j++) {
                        game.board[i][j] = line.charAt(j) == 'o' ? 1 : 0;
                    }
                }
                updateBoard();
                updateGameState();
                // both of those are really important they basicly change the board statues and update the board.
            } catch (FileNotFoundException e) {
                showErrorDialog("Could not load game from file.");
            }
        }
    }

    private void saveGame() {
        // here we are doing it in the oppiste way.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("activ3/saves"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(null);
        updateGameState();
        // here its basicly the same creating but here we use .add to add a new .txt file
        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file)) { // now we do a reverse board creation.
                writer.println(game.board.length);

                for (int i = 0; i < game.board.length; i++) {
                    for (int j = 0; j < game.board[i].length; j++) {
                        if (game.board[i][j] == 1) {
                            writer.print("o");
                        } else {
                            writer.print(".");
                        }
                    }
                    writer.println();
                }
            } catch (FileNotFoundException e) {
                showErrorDialog("Could not save game to file.");
            }
        }
    }

    public void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message); // for showing the errors if made.
        alert.showAndWait();
    }

    private void handleMouseClick(int row, int col) {
        controller.handleMouseClick(row, col); // here it use the controller to handle mouse clicks and basicly this is the part
         // that make this game play able.
    }

    public void updateBoard() {
        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board[i].length; j++) {
                Circle circle = (Circle) getNodeByRowColumnIndex(i, j, board);
                circle.setFill(game.board[i][j] == 1 ? Color.BLACK : Color.GRAY);
            }
        }
    } // this is probably not the most efficent way but here it makes the board change after each move.

    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) { // 
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {                
                return node;
            }
        }
        return null;
    }
    /**
     * 
    * This method returns the Node at the specified row and column in a given GridPane.
    * It is used to change and select circles in the game.
    *
    */

    public void updateGameState() {
        gamestate currentState = game.getgGamestate();
        switch (currentState) {
            case NOT_STARTED:
                statusbar.setText("Game Status: Not Started");
                break;
            case IN_PROGRESS:
                statusbar.setText("Game Status: In Progress");
                break;
            case STALMATE:
                statusbar.setText("Game Status: Stalemate");
                break;
            case WON:
                statusbar.setText("Game Status: Won");
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}