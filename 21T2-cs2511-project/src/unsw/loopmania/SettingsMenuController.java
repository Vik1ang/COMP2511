package unsw.loopmania;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Shape;
import unsw.loopmania.mediaplayer.MacPlayer;
import unsw.loopmania.mediaplayer.WindowsPlayer;
import unsw.loopmania.utils.ImageLoader;
import unsw.loopmania.utils.PackageHelper;
import unsw.loopmania.utils.PreservedWord;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Settings menu controller.
 */
public class SettingsMenuController {

    /**
     * The Pause button.
     */
    @FXML public Button pauseButton;
    /**
     * The Music h box.
     */
    @FXML public HBox musicHBox;
    /**
     * The Sound size.
     */
    @FXML public Slider soundSize;
    @FXML private MenuButton speedButton;
    @FXML private MenuButton musicButton;

    /**
     * The Game speed.
     */
// game speed mode 1/2/3
    SimpleStringProperty gameSpeed = new SimpleStringProperty(PreservedWord.gameSpeedSlow);

    private MenuSwitcher gameSwitcher;
    private MenuSwitcher restartSwitcher;
    private MediaView mediaView;

    /**
     * Main menu controller.
     */
    public void MainMenuController() {
    }


    /**
     * Initialize.
     */
    @FXML
    public void initialize() {

        // set sound volume
        soundSize.valueProperty().addListener((o, oldValue, newValue)->{
            mediaView.getMediaPlayer().setVolume((Double) newValue / 100);
        });

        List<String> musics = PackageHelper.getFiles("src/music/");

        // convert the file to music name for man reading
        List<String> musicNames = new ArrayList<>();
        PackageHelper.processFilesToName(musicNames ,musics);

        for (String music: musicNames) {
            MenuItem musicMenuButton = new MenuItem(music);
            musicMenuButton.setId(music);
            musicMenuButton.setOnAction((o)->{
                musicBeenSelected(musicMenuButton.getId());
            });
            musicButton.getItems().add(musicMenuButton);
        }

        speedButton.setText("GameSpeed(1.0 by default)");
        speedButton.getItems().get(0).setText(PreservedWord.gameSpeedSlow);
        speedButton.getItems().get(1).setText(PreservedWord.gameSpeedMedium);
        speedButton.getItems().get(2).setText(PreservedWord.gameSpeedQuick);

        ImageView view = new ImageView(ImageLoader.pauseButtonImage);
        view.setFitHeight(30);
        view.setFitWidth(30);
        pauseButton.setGraphic(view);
        pauseButton.setBackground(Background.EMPTY);

        pauseButton.setOnAction((o)->{
            if (mediaView.getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
                ImageView newView = new ImageView(ImageLoader.continueButtonImage);
                newView.setFitHeight(30);
                newView.setFitWidth(30);
                pauseButton.setGraphic(newView);
                mediaView.getMediaPlayer().pause();
            }
            else {
                ImageView newView = new ImageView(ImageLoader.pauseButtonImage);
                newView.setFitHeight(30);
                newView.setFitWidth(30);
                pauseButton.setGraphic(newView);
                mediaView.getMediaPlayer().play();
            }
        });
    }

    /**
     * Set game switcher.
     *
     * @param gameSwitcher the game switcher
     */
    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    /**
     * Sets restart switcher.
     *
     * @param restartSwitcher the restart switcher
     */
    public void setRestartSwitcher(MenuSwitcher restartSwitcher) { this.restartSwitcher = restartSwitcher; }

    /**
     * facilitates switching to main game upon button click
     *
     */
    @FXML private void switchToGame() { gameSwitcher.switchMenu(); }

    /**
     * Start anew game.
     */
    @FXML public void startAnewGame() { restartSwitcher.switchMenu(); }

    /**
     * Gets game speed property.
     *
     * @return the game speed property
     */
    public SimpleStringProperty getGameSpeedProperty() {
        return gameSpeed;
    }

    /**
     * Sets speed button text.
     */
    public void setSpeedButtonText() {
        speedButton.setText(gameSpeed.get());
    }

    /**
     * Switch speed slow.
     */
    public void switchSpeedSlow() {
        gameSpeed.set(PreservedWord.gameSpeedSlow);
        speedButton.setText(PreservedWord.gameSpeedSlow);
    }

    /**
     * Switch speed medium.
     */
    public void switchSpeedMedium() {
        gameSpeed.set(PreservedWord.gameSpeedMedium);
        speedButton.setText(PreservedWord.gameSpeedMedium);
    }

    /**
     * Switch speed high.
     */
    public void switchSpeedHigh() {
        gameSpeed.set(PreservedWord.gameSpeedQuick);
        speedButton.setText(PreservedWord.gameSpeedQuick);
    }

    private void musicBeenSelected(String musicName) {
        musicButton.setText(musicName);
        MediaPlayer currentPlayer = mediaView.getMediaPlayer();
        MediaPlayer mediaPlayer;
        File f = new File("src/music/" + musicName + ".mp3");
        Media media = new Media(f.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        currentPlayer.stop();
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setOnPlaying(() -> System.out.println("Mucic playing " + musicName));
    }

    /**
     * Sets media view.
     *
     * @param mediaView the media view
     */
    public void setMediaView(MediaView mediaView) {
        this.mediaView = mediaView;
        soundSize.setValue(mediaView.getMediaPlayer().getVolume() * 100);
    }


}
