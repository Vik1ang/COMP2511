package unsw.quaint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Braedon Wooding.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
