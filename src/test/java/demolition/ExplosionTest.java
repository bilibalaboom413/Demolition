package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ExplosionTest {

    public Explosion explosion;
    public Explosion[] explosions;

    public ExplosionTest() {
        explosions = new Explosion[9];
        explosion = new Explosion(96, 149);
    }

    /**
    * Test the reset method
    */
    @Test
    public void testReset() {
        explosion.reset();
        assertEquals(-1000, explosion.getX());
        assertEquals(-1000, explosion.getY());
    }

    /**
    * Test the getExplosion method
    */
    @Test
    public void testGetExplosion() {
        assertFalse(Explosion.getExplosion());
    }

    /**
    * Test the explore method
    */
    @Test
    public void testExplore() {
        // for (int i = 0; i < explosions.length; i++) {
        //     explosions[i] = new Explosion(0, 0);
        // }
        // Bomb bomb = new Bomb(32, 96);
        // BombGuy bombGuy = new BombGuy(32, 85);
        // Level level = new Level();
        // level.readMap("level1.txt");
        // level.generateMap(level.getMap());
        // RedEnemy redEnemy = new RedEnemy(32, 85);
        // YellowEnemy yellowEnemy = new YellowEnemy(96, 149);
        // Explosion.explore(bomb, explosions, level, bombGuy, yellowEnemy, redEnemy);
        // assertTrue(Explosion.getExplosion());
    }
    
    /**
    * Test the checkExplosion method
    */
    @Test
    public void testCheckExplosion() {}
}
