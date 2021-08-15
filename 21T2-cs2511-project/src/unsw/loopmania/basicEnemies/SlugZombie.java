package unsw.loopmania.basicEnemies;

import unsw.loopmania.PathPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Slug zombie.
 */
public class SlugZombie extends Zombie{

    private final int BITE = 20;
    private final int TRANSFER_RATE = 20;

    /**
     * Instantiates a new Zombie.
     *
     * @param position the position
     */
    public SlugZombie(PathPosition position) {
        super(position);
        setBite(BITE);
        setTransferRate(TRANSFER_RATE);
    }

    /**
     * Handle death.
     *
     * @param enemies the enemies
     */
    public void HandleDeath(List<BasicEnemy> enemies) {
        for (int i = 0; i < 5; i++)
            enemies.add(new Slug(this.getPosition()));
    }


}
