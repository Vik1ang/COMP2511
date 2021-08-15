package unsw.loopmania;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import unsw.loopmania.mediaplayer.computerPlayers;
import unsw.loopmania.memento.LoopManiaWorldCareTaker;
import unsw.loopmania.memento.LoopManiaWorldMemento;
import unsw.loopmania.utils.PackageHelper;
import unsw.loopmania.utils.PreservedWord;

/**
 * controller for the main menu.
 */
public class MainMenuController {
    /**
     * open the help settings
     */
    public Button helpMenuButton;
    /**
     * facilitates switching to main game
     */
    @FXML private MenuButton difficultyButton;
    @FXML private MenuButton speedButton;

    @FXML private MediaView mediaView;

    /**
     * The Game speed.
     */
// game speed mode 1/2/3
    SimpleStringProperty gameSpeed = new SimpleStringProperty(PreservedWord.gameSpeedSlow);
    /**
     * The Game mode.
     */
    SimpleStringProperty gameMode = new SimpleStringProperty(PreservedWord.gameModeStandard);

    /**
     * the game switcher
     */
    private MenuSwitcher gameSwitcher;

    /**
     * The only settings controller
     */
    private SettingsMenuController settingsMenuController;

    /**
     * the root of settings window
     */
    private Parent settingsMenuRoot;

    /**
     * The current main game controller
     */
    private LoopManiaWorldController mainController;

    /**
     * Instantiates a new Main menu controller.
     */
    public MainMenuController(){
    }

    /**
     * Initialize.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void initialize() throws IOException {
        difficultyButton.setLayoutX(100);
        difficultyButton.setLayoutX(100);
        difficultyButton.setText("Difficulty(Standard)");
        difficultyButton.getItems().get(0).setText(PreservedWord.gameModeStandard);
        difficultyButton.getItems().get(1).setText(PreservedWord.gameModeBerserk);
        difficultyButton.getItems().get(2).setText(PreservedWord.gameModeSurvival);
        difficultyButton.getItems().get(3).setText(PreservedWord.gameModeConfusing);

        speedButton.setText("GameSpeed(1.0)");
        speedButton.getItems().get(0).setText(PreservedWord.gameSpeedSlow);
        speedButton.getItems().get(1).setText(PreservedWord.gameSpeedMedium);
        speedButton.getItems().get(2).setText(PreservedWord.gameSpeedQuick);

        //load the settings menu
        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("SettingsMenu.fxml"));
        settingsMenuRoot = settingsLoader.load();
        settingsMenuController = settingsLoader.getController();

        // set the media view
        File f = new File("src/music/Amazing China.mp3");
        Media media = new Media(f.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
        mediaPlayer.setOnPlaying(() -> System.out.println("Mucic playing"));
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

    /**
     * Switch difficulty standard.
     */
    public void switchDifficultyStandard() {
        gameMode.set(PreservedWord.gameModeStandard);
        difficultyButton.setText(PreservedWord.gameModeStandard);
    }

    /**
     * Switch difficulty berserk.
     */
    public void switchDifficultyBerserk() {
        gameMode.set(PreservedWord.gameModeBerserk);
        difficultyButton.setText(PreservedWord.gameModeBerserk);
    }

    /**
     * Switch difficulty survival.
     */
    public void switchDifficultySurvival() {
        gameMode.set(PreservedWord.gameModeSurvival);
        difficultyButton.setText(PreservedWord.gameModeSurvival);
    }

    /**
     * Switch difficulty confusing.
     */
    public void switchDifficultyConfusing() {
        gameMode.set(PreservedWord.gameModeConfusing);
        difficultyButton.setText(PreservedWord.gameModeConfusing);
    }

    /**
     * Game speed property simple string property.
     *
     * @return the simple string property
     */
    public SimpleStringProperty gameSpeedProperty() {
        return gameSpeed;
    }

    /**
     * Gets game mode property.
     *
     * @return the game mode property
     */
    public SimpleStringProperty getGameModeProperty() { return gameMode; }

    /**
     * Open help menu.
     *
     * @throws IOException the io exception
     */
    public void openHelpMenu() throws IOException {
        Stage helpMenuStage = new Stage();
        helpMenuStage.setResizable(false);
        helpMenuStage.setTitle("Help Menu");

        FXMLLoader helpMenuLoader = new FXMLLoader(getClass().getResource("GameHelpView.fxml"));
        Parent helpMenuRoot = helpMenuLoader.load();
        Scene helpMenuWindow = new Scene(helpMenuRoot);

        helpMenuRoot.requestFocus();
        helpMenuStage.setScene(helpMenuWindow);
        helpMenuStage.show();
    }

    /**
     * Load game.
     *
     * @throws IOException the io exception
     */
    @FXML public void loadGame() throws IOException {
        List<String> choices = PackageHelper.getFiles("src/../worlds/savings/");

        List<String> fileNames = new ArrayList<>();

        int temporaryNumber = -1;
        if (mainController != null) {
            LoopManiaWorldCareTaker loopManiaWorldCareTaker = mainController.getLoopManiaWorldCareTaker();
            String currentFileName = mainController.getCurrentFileName();
            for (LoopManiaWorldMemento loopManiaWorldMemento: loopManiaWorldCareTaker.getMementoList()) {
                fileNames.add(currentFileName + "(" + loopManiaWorldMemento.getSavingTime() + ")");
            }
            temporaryNumber = loopManiaWorldCareTaker.getMementoList().size();
        }

        // process file name for man reading
        PackageHelper.processFilesToName(fileNames ,choices);

        // provide the choice dialog to choose the appropriate file name
        ChoiceDialog<String> dialog = new ChoiceDialog("Select the game file", fileNames);
        dialog.setTitle("Load a game");
        dialog.setHeaderText("Select a game you want to load");
        dialog.setContentText("Choose your game:");

        // set the button name
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.NO);
        dialog.getDialogPane().getButtonTypes().clear();
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

        // start the dialog
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String gameFile = result.get();
            if (temporaryNumber == -1 || fileNames.indexOf(gameFile) > temporaryNumber) {
                fileGameLoader("savings/" + gameFile);
            }
            else {
                LoopManiaWorldCareTaker loopManiaWorldCareTaker = mainController.getLoopManiaWorldCareTaker();
                mementoGameLoader(loopManiaWorldCareTaker.get(fileNames.indexOf(gameFile)));
            }
        }
    }

    /**
     * Load the game file: new game or saved game
     * @param gameFile
     * @throws IOException
     */
    private void fileGameLoader(String gameFile) throws IOException {
        LoopManiaWorldControllerLoader loopManiaLoader = new LoopManiaWorldControllerLoader("../worlds/"+ gameFile + ".json");

        // load the main game
        mainController = loopManiaLoader.loadController();
        mainController.setCurrentFileName(gameFile);
        initializeGameWindow();
    }

    private void mementoGameLoader(LoopManiaWorldMemento loopManiaWorldMemento) throws IOException {
        LoopManiaWorldControllerLoader loopManiaLoader = new LoopManiaWorldControllerLoader(loopManiaWorldMemento);

        // load the main game
        String gameFile = mainController.getCurrentFileName();
        mainController = loopManiaLoader.loadController();
        mainController.setCurrentFileName(gameFile);
        initializeGameWindow();
    }

    private void initializeGameWindow() throws IOException {
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("LoopManiaView.fxml"));
        gameLoader.setController(mainController);

        // bind the game mode settings
        mainController.getGameMode().bindBidirectional(this.getGameModeProperty());

        Parent gameRoot = gameLoader.load();
        settingsMenuController.setMediaView(mediaView);

        // bind the game speed settings
        mainController.getGameSpeedProperty().bindBidirectional(this.gameSpeedProperty());
        settingsMenuController.getGameSpeedProperty().bindBidirectional(this.gameSpeedProperty());



        // create new scene with the main menu (so we start with the main menu)
        Parent mainMenuRoot = this.difficultyButton.getScene().getRoot();
        Scene scene = this.difficultyButton.getScene();
        Stage primaryStage = (Stage) this.difficultyButton.getScene().getWindow();

        // set functions which are activated when button click to switch menu is pressed
        // e.g. from main menu to start the game, or from the game to return to main menu
        mainController.setMainMenuSwitcher(() -> {
            settingsMenuController.setSpeedButtonText();
            switchToRoot(scene, settingsMenuRoot, primaryStage);
        });

        // set switcher for main menu
        this.setGameSwitcher(() -> {
            settingsMenuController.setSpeedButtonText();
            switchToRoot(scene, gameRoot, primaryStage);
            mainController.prepareToStartTimer();
        });

        // set switcher for settings
        settingsMenuController.setGameSwitcher(() -> {
            switchToRoot(scene, gameRoot, primaryStage);
            mainController.prepareToStartTimer();
        });

        // set switcher for settings
        settingsMenuController.setRestartSwitcher(() -> {
            switchToRoot(scene, mainMenuRoot, primaryStage);
        });

        // open the new game
        scene.setRoot(gameRoot);
        gameRoot.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * Start new game.
     *
     * @throws IOException the io exception
     */
    @FXML public void startNewGame() throws IOException {
        fileGameLoader("world_with_twists_and_turns");
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

    /**
     * Terminate.
     */
    public void terminate() {
        if (mainController != null)
            mainController.terminate();
    }
}
