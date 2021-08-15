/**
 *
 */
package emphasis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Emphasis example JavaFX application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class EmphasisApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Emphasis");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EmphasisView.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
