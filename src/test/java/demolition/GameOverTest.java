package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameOverTest {
    
    GameOver gameOver;

    public GameOverTest() {
        gameOver = new GameOver(32, 32);
    }

    /**
    * Test the constructor method
    */
    @Test
    public void testConstructor() {
        assertEquals(32, gameOver.getX());
        assertEquals(32, gameOver.getY());
    }
}
