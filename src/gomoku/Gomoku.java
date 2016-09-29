package gomoku;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Stack;

/**
 * Created by ben on 9/21/16.
 */
public class Gomoku {

    private static final int WHITE = 0;
    private static final int BLACK = 1;

    public static final int BOARD_SIZE = 15;

    Stack<Move> moves = new Stack<Move>();

    private int currentTurn = 1;

    private static BackgroundImage blackPiece;
    private  static BackgroundImage whitePiece;

    public Gomoku() {
        currentTurn = (Math.random() <= 0.5) ? 1 : 2;

        Image imgBlack = new Image("/pieces/black.png", true);
        BackgroundSize backgroundSize = new BackgroundSize(32, 32, true, true, true, true);
        blackPiece = new BackgroundImage(imgBlack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        Image imgWhite = new Image("/pieces/white.png", true);
        whitePiece = new BackgroundImage(imgWhite, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        }

    public void makeMove(int x, int y, Move piece) {
        if(!moves.contains(piece)) {
            moves.push(piece);

            //Update game board
            System.out.println("move: at " + x + "/" + y);

            if (currentTurn == BLACK) {
                piece.setBackground(new Background(blackPiece));
                currentTurn = WHITE;
            } else {
                piece.setBackground(new Background(whitePiece));
                currentTurn = BLACK;
            }
        }
    }

    public void removeLastMove() {
        if(!moves.empty()) {
            Move piece = moves.pop();

            piece.setBackground(null);

            currentTurn = (currentTurn == BLACK) ? WHITE : BLACK;

        }
    }

}
