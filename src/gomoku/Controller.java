package gomoku;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private GridPane gridPane;

    @FXML
    private StackPane backgroundPane;

    private static int numberOfBackgrounds = 4;
    private List<Background> backgrounds = new ArrayList<Background>();
    private int backgroundCount = 0;

    private Gomoku game = new Gomoku();

    @FXML
    public void initialize() {
        gridPane.setAlignment(Pos.CENTER);

        for(int i = 1; i <= numberOfBackgrounds; i++) {
            Image img = new Image("/backgrounds/" + i + ".jpg", true);
            BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
            BackgroundImage bgImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            Background bg = new Background(bgImage);
            backgrounds.add(bg);

        }

        setGameBoard();

        //gridPane.setGridLinesVisible(true);
    }

    private void setGameBoard() {
        for(int y = 0; y < Gomoku.BOARD_SIZE; y++)
            for(int x = 0; x < Gomoku.BOARD_SIZE; x++) {
                Move gamePiece = new Move(x, y);
                gamePiece.setMinSize(32.0, 32.0);
                gridPane.add(gamePiece, x, y);
                GridPane.setRowIndex(gamePiece, y);
                GridPane.setColumnIndex(gamePiece, x);
                GridPane.setHalignment(gamePiece, HPos.CENTER); // To align horizontally in the cell
                GridPane.setValignment(gamePiece, VPos.CENTER); // To align vertically in the cell
                gamePiece.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> game.makeMove(gamePiece));

            }

    }

    @FXML
    private void quitAction(){
        Platform.exit();
    }

    @FXML
    private void undoMove() {
        game.removeLastMove();
    }

    @FXML
    private  void resetGame() {
        game = new Gomoku();

        gridPane.getChildren().clear();

        setGameBoard();
    }

    @FXML
    private void onMouseClickChangeBackground(){
        if (++backgroundCount == backgrounds.size())
            backgroundCount = 0;

        backgroundPane.setBackground(backgrounds.get(backgroundCount));
    }
}
