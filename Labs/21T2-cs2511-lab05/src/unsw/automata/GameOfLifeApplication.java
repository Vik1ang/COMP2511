/**
 *
 */
package unsw.automata;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Conway's Game of Life Application
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLifeApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Conway's Game of Life");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOfLifeView.fxml"));
        loader.setController(new GameOfLifeController());

        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
