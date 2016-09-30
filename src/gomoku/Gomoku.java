package gomoku;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.util.Stack;

/**
 * Created by ben on 9/21/16.
 */
public class Gomoku {

    public static final int WHITE = 1;
    public static final int BLACK = 2;

    public static final int BOARD_SIZE = 15;

    private boolean gameOver = false;

    private Stack<Move> moves = new Stack<Move>();

    private int currentTurn;

    private static BackgroundImage blackPiece;
    private  static BackgroundImage whitePiece;

    public Gomoku() {
        currentTurn = (Math.random() <= 0.5) ? BLACK : WHITE;

        Image imgBlack = new Image("/pieces/black.png", true);
        BackgroundSize backgroundSize = new BackgroundSize(32, 32, true, true, true, true);
        blackPiece = new BackgroundImage(imgBlack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        Image imgWhite = new Image("/pieces/white.png", true);
        whitePiece = new BackgroundImage(imgWhite, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        }

    public void makeMove(Move piece) {
        if(!moves.contains(piece) && !gameOver) {
            piece.setPlayer(currentTurn);
            moves.push(piece);

            //Update game board
            //System.out.println("move: at " + piece.x() + "/" + piece.y());

            if(checkWin(piece)) {
                gameOver = true;

                Stage dialogStage = new Stage();
                Button btn = new Button("Ok.");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        dialogStage.close();
                    }
                });
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.setScene(new Scene(VBoxBuilder.create().
                        children(new Text("Player " + currentTurn + " won!"), btn).
                        alignment(Pos.CENTER).padding(new Insets(10)).build()));
                dialogStage.show();
            }

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

            gameOver = false;

            currentTurn = (currentTurn == BLACK) ? WHITE : BLACK;

        }
    }

    public boolean checkWin(Move move) {
        return ( checkWinH(move) || checkWinV(move) || checkWinDiagBackward(move) || checkWinDiagForward(move) );
    }

    private boolean checkWinH(Move move) {
        return  (consecutive(move, 1, 0, 1) + consecutive(move, -1, 0, 0)  >= 5);
    }

    private boolean checkWinV(Move move) {
        return  (consecutive(move, 0, 1, 1) + consecutive(move, 0, -1, 0)  >= 5);
    }

    private boolean checkWinDiagBackward(Move move) {
        return  (consecutive(move, -1, -1, 1) + consecutive(move, 1, 1, 0)  >= 5);
    }

    private boolean checkWinDiagForward(Move move) {
        return  (consecutive(move, -1, 1, 1) + consecutive(move, 1, -1, 0)  >= 5);
    }

    private int consecutive(Move piece, int xdir, int ydir, int consecutiveMoves) {

        for(Move nextMove : moves) {
            if((nextMove.y() == piece.y()+ydir) && (nextMove.x() == (piece.x()+xdir))) {
                //System.out.println("Found piece at x: " + nextMove.x());
                if(nextMove.getPlayer() == piece.getPlayer()){
                    //System.out.println("Same player found: " + consecutiveMoves);
                    consecutiveMoves += 1;
                    return consecutive(nextMove, xdir, ydir, consecutiveMoves);
                }

            }
        }

        return consecutiveMoves;
    }

}
