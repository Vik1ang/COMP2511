package unsw.loopmania;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * the main application
 * run main method from this class
 */
public class LoopManiaApplication extends Application {
    /**
     * the controller for the game. Stored as a field so can terminate it when click exit button
     */
    private MainMenuController mainMenuController;
//    MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws IOException {

//        music();

        // if main window closed, everything will be closed
        primaryStage.setOnCloseRequest((event) -> {
            Platform.exit();
        });

        // set title on top of window bar
        primaryStage.setTitle("Loop Mania");
        // prevent human player resizing game window (since otherwise would see white space)
        // alternatively, you could allow rescaling of the game (you'd have to program resizing of the JavaFX nodes)
        primaryStage.setResizable(false);

        // load the main menu
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));
        Parent mainMenuRoot = menuLoader.load();
        mainMenuController = menuLoader.getController();

        // create new scene with the main menu (so we start with the main menu)
        Scene scene = new Scene(mainMenuRoot);

        // deploy the main onto the stage
        mainMenuRoot.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    public void music() {
//        String musicString = this.getClass().getClassLoader().getResource("music/handclip.mp3").toExternalForm();
//        Media media = new Media(musicString);
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
//        mediaPlayer.setOnPlaying(() -> System.out.println("Mucic playing"));
//    }


    @Override
    public void stop() {
        // wrap up activities when exit program
        mainMenuController.terminate();
    }

    /**
     * switch to a different Root
     */
    private void switchToRoot(Scene scene, Parent root, Stage stage) {
        scene.setRoot(root);
        root.requestFocus();
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
