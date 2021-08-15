package unsw.automata;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.junit.jupiter.api.Test;

class GameOfLifeTest {

    @Test
    void testTick() {
        GameOfLife gol = new GameOfLife();

        // Create a glider toward the bottom-right corner
        gol.ensureAlive(9, 8);
        gol.ensureAlive(8, 8);
        gol.ensureAlive(7, 8);
        gol.ensureAlive(9, 7);
        gol.ensureAlive(8, 6);

        gol.tick();
        assertTrue(gol.isAlive(9, 8));
        assertTrue(gol.isAlive(8, 8));
        assertTrue(gol.isAlive(8, 9));
        assertTrue(gol.isAlive(7, 7));
        assertTrue(gol.isAlive(9, 7));

        assertFalse(gol.isAlive(7, 8));
        assertFalse(gol.isAlive(8, 6));

        gol.tick();
        assertTrue(gol.isAlive(9, 8));
        assertTrue(gol.isAlive(8, 9));
        assertTrue(gol.isAlive(9, 9));
        assertTrue(gol.isAlive(7, 8));
        assertTrue(gol.isAlive(9, 7));

        //Check to see it wraps around horizontally
        gol.tick();
        assertTrue(gol.isAlive(0, 8));

        //Check to see it wraps around vertically
        gol.tick();
        gol.tick();
        assertTrue(gol.isAlive(9, 0));
    }

   @Test
   void testObservation() {
       GameOfLife gol = new GameOfLife();

       gol.cellProperty(0, 0).addListener(new ChangeListener<Boolean>() {

           @Override
           public void changed(ObservableValue<? extends Boolean> observable,
                               Boolean oldValue, Boolean newValue) {
               alive = newValue;
           }

       });


       // In the next generation after this seed the cell at (0,0) should be
       // alive
       gol.ensureAlive(1, 0);
       gol.ensureAlive(1, 1);
       gol.ensureAlive(0, 1);

       gol.tick();

       assertTrue(alive);
   }

    // Used in the above test.
    private boolean alive = false;

}
