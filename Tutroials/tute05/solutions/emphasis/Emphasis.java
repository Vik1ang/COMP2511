/**
 *
 */
package emphasis;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The model for the simple emphasis example.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Emphasis {
    private StringProperty text;

    public Emphasis() {
        this.text = new SimpleStringProperty();
    }

    public void emphasise() {
        if (!getText().endsWith("!"))
            setText(getText().toUpperCase() + "!!!!");
    }

    public String getText() {
        return text.get();
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public StringProperty textProperty() {
        return text;
    }
}
