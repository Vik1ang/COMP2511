package emphasis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmphasisTest {

    @Test
    public void testEmphasise() {
        Emphasis emphasis = new Emphasis();
        emphasis.setText("My code isn't working");

        emphasis.emphasise();

        assertEquals("MY CODE ISN'T WORKING!!!!", emphasis.getText());

        emphasis.setText("Oh no!");

        emphasis.emphasise();

        assertEquals("Oh no!", emphasis.getText());
    }

    @ParameterizedTest
    @ValueSource(strings = { "Hello", "123456", "F#$@", "I'm a long string... I'm attached to a yoyo!" })
    public void testIdempotency(String input) {
        Emphasis emphasis = new Emphasis();
        emphasis.setText(input);
        emphasis.emphasise();
        String emphasisedOnce = emphasis.getText();
        emphasis.emphasise();
        assertEquals(emphasisedOnce, emphasis.getText());
    }
}
