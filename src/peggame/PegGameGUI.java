package peggame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PegGameGUI extends Application {
    private Zasquare game;
    private GridPane board;
    private Circle selectedPeg = null;
    private int selectedRow = -1;
    private int selectedCol = -1;

    @Override
    public void start(Stage primaryStage) {
        // Create a file chooser
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            try {
                // Parse the file to create a new game
                game = (Zasquare) fileparser.parseFile(file.getPath());

                // Create a new GridPane to represent the game board
                board = new GridPane();

                // Add pegs and holes to the board
                for (int i = 0; i < game.board.length; i++) {
                    for (int j = 0; j < game.board[i].length; j++) {
                        Circle circle = new Circle(10);
                        if (game.board[i][j] == 1) {
                            circle.setFill(Color.BLACK);
                        } else {
                            circle.setFill(Color.WHITE);
                        }
                        final int finalI = i;
                        final int finalJ = j;
                        circle.setOnMouseClicked(event -> handleMouseClick(finalI, finalJ));
                        board.add(circle, j, i);
                    }
                }
                

                // Create a new scene with the game board and set it on the stage
                Scene scene = new Scene(board);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                // Show an error dialog
                Alert alert = new Alert(AlertType.ERROR, "Could not load game from file.");
                alert.showAndWait();
            }
        }
    }

    private void handleMouseClick(int row, int col) {
        if (selectedPeg == null) {
            // Select a peg
            if (game.board[row][col] == 1) {
                selectedPeg = (Circle) getNodeByRowColumnIndex(row, col, board);
                selectedRow = row;
                selectedCol = col;
                selectedPeg.setFill(Color.RED);
            }
        } else {
            // Move the selected peg
            try {
                game.makeMove(new move(new location(selectedRow, selectedCol), new location(row, col)));
                updateBoard();
                selectedPeg = null;
            } catch (PegGameException e) {
                // Show an error dialog
                Alert alert = new Alert(AlertType.ERROR, "Invalid move.");
                alert.showAndWait();
            }
        }
    }

    private void updateBoard() {
        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board[i].length; j++) {
                Circle circle = (Circle) getNodeByRowColumnIndex(i, j, board);
                if (game.board[i][j] == 1) {
                    circle.setFill(Color.BLACK);
                } else {
                    circle.setFill(Color.WHITE);
                }
            }
        }
    }

    private javafx.scene.Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        javafx.scene.Node result = null;
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
