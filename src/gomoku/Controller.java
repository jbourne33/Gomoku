package gomoku;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    private GridPane gridPane;

    @FXML
    private void onMouseClickGridPane(MouseEvent e) {

    }

    @FXML
    private void quitAction(){
        Platform.exit();
    }

}
