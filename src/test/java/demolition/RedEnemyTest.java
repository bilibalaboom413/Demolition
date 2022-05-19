package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RedEnemyTest {

    public RedEnemy redEnemy;

    public RedEnemyTest() {
        redEnemy = new RedEnemy(96, 149);
    }

    /**
    * Test the reset() method
    */
    @Test
    public void testReset() {
        redEnemy.reset();
        assertEquals(96, redEnemy.getX());
        assertEquals(149, redEnemy.getY());
    }
    
    /**
    * Test the move() method
    */
    @Test
    public void testMove() {
        for (int i = 0; i < 2400; i++) {
            redEnemy.move();
        }
    }
}
