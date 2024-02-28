package peggame;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        List<Player> players = Arrays.asList(player1, player2);

        Board board = new Board(5, 5, players);

        while (board.getGameState() == GameState.IN_PROGRESS) {
            Player currentPlayer = board.getCurrentPlayer();

            Collection<Move> possibleMoves = board.getPossibleMoves(currentPlayer);

            if (possibleMoves.isEmpty()) {
                break;
            }

            Move move = possibleMoves.iterator().next();

            try {
                board.makeMove(move);

                System.out.println(board);
            } catch (PegGameException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final game state: " + board.getGameState());
    }
}
