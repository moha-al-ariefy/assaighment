package peggame;

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
import peggame.PegGameController;
import peggame.Zasquare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PegGameGUI extends Application {
    private Zasquare game;
    GridPane board;
    private Label statusLabel;
    private PegGameController controller;

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
        controller = new PegGameController(game, this);

        // Create the scene and show the stage
        createScene(primaryStage);
    }

    

    private void initializeGame(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:/Users/ma980/Downloads/124/saves"));
        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            try {
                game = (Zasquare) fileparser.parseFile(file.getPath());
            } catch (IOException e) {
                showErrorDialog("Could not load game from file.");
            }
        }
    }
    private void updateStatus() {
        if (statusLabel == null) {
            statusLabel = new Label();
        }
        statusLabel.setText("Game Status: " + game.getgGamestate());
    }
    private void setupBoard() {
        board = new GridPane();

        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board[i].length; j++) {
                Circle circle = createCircle(i, j);
                board.add(circle, j, i);
            }
        }
    }

    private Circle createCircle(int row, int col) {
        Circle circle = new Circle(20);
        circle.setFill(game.board[row][col] == 1 ? Color.BLACK : Color.GRAY);
        circle.setOnMouseClicked(event -> handleMouseClick(row, col));
        return circle;
    }

    private void createScene(Stage primaryStage) {
        // Use a BorderPane as the root node
        BorderPane root = new BorderPane();

        // Create the Load Game, Save Game, and Close buttons
        Button loadButton = new Button("Load Game");
        loadButton.setOnAction(event -> loadGame());
        Button saveButton = new Button("Save Game");
        saveButton.setOnAction(event -> saveGame());
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> closeGame(primaryStage));
        // Create the game state box
        HBox gameStateBox = new HBox(statusLabel);
        gameStateBox.setSpacing(10);

        // Create the button box
        HBox buttonBox = new HBox(loadButton, saveButton, closeButton);
    buttonBox.setSpacing(30);


        // Add the board, game state box, and button box to the BorderPane
        root.setCenter(board);
        root.setTop(gameStateBox);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private void closeGame(Stage primaryStage) {
        // Close the game and the stage
        primaryStage.close();
    }
    
    private void loadGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
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
            } catch (FileNotFoundException e) {
                showErrorDialog("Could not load game from file.");
            }
        }
    }

    private void saveGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(null);
        updateGameState();

        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file)) {
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
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }
    private void handleMouseClick(int row, int col) {
        controller.handleMouseClick(row, col);
    }

    public void updateBoard() {
        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board[i].length; j++) {
                Circle circle = (Circle) getNodeByRowColumnIndex(i, j, board);
                circle.setFill(game.board[i][j] == 1 ? Color.BLACK : Color.GRAY);
            }
        }
    }

    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return node;
            }
        }
        return null;
    }

    public void updateGameState() {
        gamestate currentState = game.getgGamestate();
        switch (currentState) {
            case NOT_STARTED:
                statusLabel.setText("Game Status: Not Started");
                break;
            case IN_PROGRESS:
                statusLabel.setText("Game Status: In Progress");
                break;
            case STALMATE:
                statusLabel.setText("Game Status: Stalemate");
                break;
            case WON:
                statusLabel.setText("Game Status: Won");
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}