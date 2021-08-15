package random;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {


    @Test
    public void testGameSeed4() {
        Game game = new Game(4);
        // 62 52 3 58 67 5 11 46
        assertTrue(game.battle()); // 62
        assertTrue(game.battle()); // 52
        assertFalse(game.battle()); // 3
        assertTrue(game.battle()); // 58
        assertTrue(game.battle()); // 67
        assertFalse(game.battle()); // 5
        assertFalse(game.battle()); // 11
        assertFalse(game.battle()); // 46
    }

    @Test
    public void testGameSeedMinus4() {
        Game game = new Game(-4);
        // 39 13 98 5 43 89 20 23
        assertFalse(game.battle()); // 39
        assertFalse(game.battle()); // 13
        assertTrue(game.battle()); // 98
        assertFalse(game.battle()); // 5
        assertFalse(game.battle()); // 43
        assertTrue(game.battle()); // 89
        assertFalse(game.battle()); // 20
        assertFalse(game.battle()); // 23
    }

    @Test
    public void testGameSeed0() {
        Game game = new Game(0);
        // 60 48 29 47 15 53 91 61
        assertTrue(game.battle());  // 60
        assertFalse(game.battle()); // 48
        assertFalse(game.battle()); // 29
        assertFalse(game.battle()); // 47
        assertFalse(game.battle()); // 15
        assertTrue(game.battle()); // 53
        assertTrue(game.battle()); // 91
        assertTrue(game.battle()); // 61
    }


}