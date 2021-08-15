package thrones;

/**
 * A troll can only move up, down, left or right, and has a 1 in 6 chance of
 * inflicting 20 points of damage.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dragon extends Character {

    public Dragon(int x, int y) {
        super(x, y);
    }

    @Override
    public void attack(Character victim) {
        if (Math.random() * 6 < 1)
            victim.damage(20);
    }

    @Override
    public boolean canMove(int dx, int dy) {
        dx = Math.abs(dx);
        dy = Math.abs(dy);

        return (dx == 1 && dy == 0 || dx == 0 && dy == 1);
    }

}
