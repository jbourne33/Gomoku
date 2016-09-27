package gomoku;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        for(int y = 0; y < 15; y++)
            for(int x = 0; x < 15; x++) {
                ImageView image = new ImageView();
                gridPane.add(image, x, y);
                image.setImage(new Image("test.jpg"));
                GridPane.setRowIndex(image, x);
                GridPane.setColumnIndex(image, y);
                GridPane.setHalignment(image, HPos.CENTER); // To align horizontally in the cell
                GridPane.setValignment(image, VPos.CENTER); // To align vertically in the cell
                image.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println( "Node: " + image + " at " + GridPane.getRowIndex( image) + "/" + GridPane.getColumnIndex( image)));

            }

        gridPane.setGridLinesVisible(true);
    }

    @FXML
    private void onMouseClickGridPane(MouseEvent e) {
        for( Node node: gridPane.getChildren()) {

            if( node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY())) {
                System.out.println( "Node: " + node + " at " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex( node));
            }


        }

    }

    @FXML
    private void quitAction(){
        Platform.exit();
    }

}
