package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class WinScreenTest {
    WinScreen winScreen;

    public WinScreenTest() {
        winScreen = new WinScreen(32, 32);
    }

    /**
    * Test the constructor method
    */
    @Test
    public void testConstructor() {
        assertEquals(32, winScreen.getX());
        assertEquals(32, winScreen.getY());
    }
}

