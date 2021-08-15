package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.beans.property.SimpleIntegerProperty;
import org.junit.Test;
import unsw.loopmania.alliedSoldiers.AlliedSoldier;
import unsw.loopmania.alliedSoldiers.TracedAlliedSoldier;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.basicEnemies.Slug;

public class AlliedSoldierTest {
    @Test
    public void alliedSoldierAttackTest() {
        AlliedSoldier alliedSoldier = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(alliedSoldier.attack(), 5);
    }

    @Test
    public void alliedSoldierDeathTest1() {
        AlliedSoldier alliedSoldier = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(alliedSoldier.checkDeath(), false);
        alliedSoldier.gotAttack(5);
        assertEquals(alliedSoldier.checkDeath(), false);
        alliedSoldier.gotAttack(15);
        assertEquals(alliedSoldier.checkDeath(), true);
    }

    @Test
    public void alliedSoldierDeathTest2() {
        AlliedSoldier alliedSoldier = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(alliedSoldier.checkDeath(), false);
        alliedSoldier.gotAttack(25);
        assertEquals(alliedSoldier.checkDeath(), true);
    }

    @Test
    public void tracedAlliedSoldierTest1() {
        TracedAlliedSoldier tracedAlliedSoldier = new TracedAlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(3, tracedAlliedSoldier.getTranceRemain());
        tracedAlliedSoldier.decreaseTranceRemain();
        assertEquals(2, tracedAlliedSoldier.getTranceRemain());
    }

    @Test
    public void tracedAlliedSoldierTest2() {
        TracedAlliedSoldier tracedAlliedSoldier = new TracedAlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BasicEnemy enemy = new Slug(null);
        tracedAlliedSoldier.setEnemy(enemy);
        assertTrue(tracedAlliedSoldier.getEnemy() instanceof Slug);
    }


}
