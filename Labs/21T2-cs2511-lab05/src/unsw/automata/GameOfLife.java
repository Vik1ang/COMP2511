/**
 *
 */
package unsw.automata;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Conway's Game of Life on a 10x10 grid.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLife {

    BooleanProperty[][] state;

    public GameOfLife() {
        // TODO At the start all cells are dead
        state = new SimpleBooleanProperty[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                state[i][j] = new SimpleBooleanProperty(false);
            }
        }
    }

    public void ensureAlive(int x, int y) {
        // TODO Set the cell at (x,y) as alive
        state[x][y].set(true);
    }

    public void ensureDead(int x, int y) {
        // TODO Set the cell at (x,y) as dead
        state[x][y].set(false);
    }

    public boolean isAlive(int x, int y) {
        // TODO Return true if the cell is alive
        return state[x][y].get();
    }

    public void tick() {
        // TODO Transition the game to the next generation.
        boolean[][] temp = new boolean[10][10];
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                int countAlive = 0;

                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (k == 0 && l == 0) {
                            continue;
                        }
                        int row = i + k;
                        int col = j + l;
                        if (i + k < 0) {
                            row = state.length - 1;
                        }
                        if (i + k >= state.length) {
                            row = 0;
                        }
                        if (j + l < 0) {
                            col = state.length - 1;
                        }
                        if (j + l >= state.length) {
                            col = 0;
                        }

                        if (state[row][col].get()) {
                            countAlive++;
                        }
                    }
                }
                if (state[i][j].get()) {
                    if (countAlive < 2) {
                        temp[i][j] = false;
                    } else if (countAlive == 2 || countAlive == 3) {
                        temp[i][j] = true;
                    } else if (countAlive > 3) {
                        temp[i][j] = false;
                    }
                } else {
                    if (countAlive == 3) {
                        temp[i][j] = true;
                    }
                }

            }
        }

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                state[i][j].set(temp[i][j]);
            }
        }
    }

    public BooleanProperty cellProperty(int x, int y) {
        return state[x][y];
    }

}
