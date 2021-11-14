package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class YellowEnemyTest {
    
    public YellowEnemy yellowEnemy;

    public YellowEnemyTest() {
        yellowEnemy = new YellowEnemy(96, 149);
    }

    /**
    * Test the reset() method
    */
    @Test
    public void testReset() {
        yellowEnemy.reset();
        assertEquals(96, yellowEnemy.getX());
        assertEquals(149, yellowEnemy.getY());
    }
    
    /**
    * Test the move() method
    */
    @Test
    public void testMove() {
        for (int i = 0; i < 2400; i++) {
            yellowEnemy.move();
        }
    }
}

