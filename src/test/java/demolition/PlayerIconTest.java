package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerIconTest {

    PlayerIcon playerIcon;

    public PlayerIconTest() {
        playerIcon = new PlayerIcon(32, 32);
    }

    /**
    * Test the setLives() method
    */
    @Test
    public void testSetLives() {
        playerIcon.setLives(3);
        assertEquals(3, playerIcon.getLives());
    }

    /**
    * Test the reset() method
    */
    @Test
    public void testReset() {
        playerIcon.setLives(3);
        playerIcon.reset();
        assertEquals(2, playerIcon.getLives());
    }
    
}
