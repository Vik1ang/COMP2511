package thrones;

/**
 * A knight can move like a knight in chess (in an L shape), and has a 1 in 2
 * chance of inflicting 10 points of damage when attacking.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Knight extends Character {

    public Knight(int x, int y) {
        super(x, y);
    }

    @Override
    public void attack(Character victim) {
        if (Math.random() > 0.5)
            victim.damage(10);
    }

    @Override
    public boolean canMove(int dx, int dy) {
        dx = Math.abs(dx);
        dy = Math.abs(dy);
        return (dx == 1 && dy == 2 || dx == 2 && dy == 1);
    }

}
