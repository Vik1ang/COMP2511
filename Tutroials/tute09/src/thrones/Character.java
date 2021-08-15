package thrones;

import java.util.List;

/**
 * A character in the simple grid game example.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class Character {
    private int healthPoints;

    private int x, y;

    public Character(int x, int y) {
        healthPoints = 100;
        this.x = x;
        this.y = y;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Cause this character the given amount of damage.
     *
     * @param points
     */
    public void damage(int points) {
        healthPoints -= points;
    }

    /**
     * Attempts to make a move to a square in the game, given all of the characters
     * If it is an invalid move, returns INVALID.
     * If it is a valid move but the square is occupied, attacks the character and returns ATTACK
     * If it is a valid move and the square is free, returns SUCCESS
     */
    public MoveResult makeMove(int x, int y, List<Character> characters) {
        // This function uses two abstract methods (AKA 'hook methods') which the concrete classes must implement
        if (!canMove(this.x - x, this.y - y)) {
            return MoveResult.INVALID;
        }

        for (Character character : characters) {
            if (character != this && character.getX() == x && character.getY() == y) {
                attack(character);
                return MoveResult.ATTACK;
            }
        }
        
        this.x = x;
        this.y = y;

        return MoveResult.SUCCESS;
    }

    public String toString() {
        return getClass().getSimpleName() + " at (" + getX() + ", " + getY() + "), health = " + healthPoints;
    }

    /**
     * This character attacks the given victim, causing them damage according to
     * their rules.
     *
     * @param victim
     */
    public abstract void attack(Character victim);

    /**
     * Can this character move by the given amount along the x and y axes.
     *
     * @param x
     * @param y
     * @return True if they can move by that amount, false otherwise
     */
    public abstract boolean canMove(int dx, int dy);
}
