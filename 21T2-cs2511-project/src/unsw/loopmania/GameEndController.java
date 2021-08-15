package unsw.loopmania;

import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import unsw.loopmania.utils.PreservedWord;

/**
 * The type Game end controller.
 */
public class GameEndController {
    /**
     * The Game end label.
     */
    public Label gameEndLabel;
    /**
     * facilitates switching to main game
     */
    @FXML private Button finishGameButton;
    /**
     * The Main window.
     */
    Stage mainWindow;


    /**
     * Instantiates a new Game end controller.
     */
    public GameEndController() {
    }

    @FXML
    public void initialize() {
    }

    /**
     * Sets main window.
     *
     * @param mainWindow the main window
     */
    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML private void endGame() throws IOException {
        mainWindow.close();
        ((Stage)finishGameButton.getScene().getWindow()).close();
    }

    /**
     * Sets game end label text.
     *
     * @param text the text
     */
    public void setGameEndLabelText(String text) {
        gameEndLabel.setText(text);
    }

    /**
     * Sets finish button text.
     *
     * @param text the text
     */
    public void setFinishButtonText(String text) {
        finishGameButton.setText(text);
    }
}
