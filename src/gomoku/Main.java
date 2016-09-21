package gomoku;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gomokuWindow.fxml"));
        primaryStage.setTitle("Gomoku Deluxe");
        primaryStage.setScene(new Scene(root, 600, 800));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
