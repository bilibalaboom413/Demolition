package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BombTest {
    
    public Bomb bomb;

    /**
    * Test the reset() method
    */
    @Test
    public void testReset() {
        bomb = new Bomb(32, 96);
        bomb.reset();
        assertEquals(-1000, bomb.getX());
        assertEquals(-1000, bomb.getY());
    }

}
