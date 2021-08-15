package unsw.automata;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 */
public class GameOfLifeController {

    private GameOfLife gameOfLife;
    private Timeline timeline;
    private boolean start;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button tickButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button playButton;


    public GameOfLifeController() {
        gameOfLife = new GameOfLife();
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameOfLife.tick();
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        start = false;
    }

    @FXML
    public void handleTick(ActionEvent event) {
        gameOfLife.tick();
    }

    @FXML
    public void handlePlay(ActionEvent event) {
        if (!start) {
            playButton.setText("Stop");
            timeline.play();
            start = true;
        } else {
            playButton.setText("Play");
            timeline.pause();
            start = false;
        }
    }

    @FXML
    public void initialize() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                CheckBox checkBox = new CheckBox();
                checkBox.setSelected(false);
                checkBox.selectedProperty().bindBidirectional(gameOfLife.cellProperty(i, j));
                gridPane.add(checkBox, i, j);
            }
        }
    }

    @FXML
    public void handleStop(ActionEvent event) {
        timeline.pause();
    }

}

