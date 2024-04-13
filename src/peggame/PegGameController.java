package peggame;

import peggame.Zasquare;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import peggame.PegGameException;
import peggame.location;
import peggame.move;
import peggame.PegGameGUI;

public class PegGameController {
    private Zasquare game;
    private PegGameGUI view;

    public PegGameController(Zasquare game, PegGameGUI view) {
        this.game = game;
        this.view = view;
    }



    public void handleMouseClick(int row, int col) {
        if (view.selectedPeg == null) {
            // Select a peg
            Node node = view.getNodeByRowColumnIndex(row, col, view.board);
            if (node instanceof Circle && game.board[row][col] == 1) {
                view.selectedPeg = (Circle) node;
                view.selectedRow = row;
                view.selectedCol = col;
                view.selectedPeg.setFill(Color.RED);
            }
        } else {
            if (row == view.selectedRow && col == view.selectedCol) {
                // Deselect the peg
                view.selectedPeg.setFill(Color.BLACK); // or whatever the original color was
                view.selectedPeg = null;
                view.selectedRow = -1;
                view.selectedCol = -1;
            } else {
                try {
                    game.makeMove(new move(new location(view.selectedRow, view.selectedCol), new location(row, col)));
                    view.updateBoard();
                    view.selectedPeg = null;
                    view.selectedRow = -1;
                    view.selectedCol = -1;
                    view.updateGameState();
                } catch (PegGameException e) {
                    view.showErrorDialog("Invalid move.");
                }
            }
        }
    }
    
}