package emphasis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * A JavaFX controller for the example emphasis application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class EmphasisController {

    @FXML
    private TextField text;

    private Emphasis emphasis;

    public EmphasisController() {
        this.emphasis = new Emphasis();
    }

    @FXML
    public void handleEmphasise(ActionEvent event) {
        emphasis.emphasise();
    }

    @FXML
    public void initialize() {
        emphasis.textProperty().bindBidirectional(text.textProperty());
    }

}

