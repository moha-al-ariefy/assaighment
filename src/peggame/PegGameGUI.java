package peggame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PegGameGUI extends Application {

    private static Zasquare game; // Your game logic here

    public static void setGame(Zasquare game) {
        PegGameGUI.game = game;
    }
    @Override
    public void start(Stage primaryStage) {
        game = new Zasquare(5); // Initialize your game

        GridPane grid = new GridPane(); // Create a new grid pane

        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board[i].length; j++) {
                final int finalI = i;
                final int finalJ = j;
                Button button = new Button(); // Create a new button for each cell
                button.setOnAction(e -> {
                    try {
                        game.makeMove(new move(new location(finalI, finalJ), new location(finalI, finalJ)));
                        updateBoard(grid);
                    } catch (PegGameException ex) {
                        System.out.println("Invalid move: " + ex.getMessage());
                    }
                });
                grid.add(button, j, i); // Add the button to the grid at the correct position
            }
        }
        

        Scene scene = new Scene(grid, 500, 500); // Create a new scene with the grid pane
        primaryStage.setScene(scene); // Set the scene on the primary stage
        primaryStage.show(); // Show the primary stage
    }

    private void updateBoard(GridPane grid) {
        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board[i].length; j++) {
                Button button = (Button) grid.getChildren().get(i * game.board.length + j);
                button.setText(game.board[i][j] == 1 ? "o" : "-");
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
