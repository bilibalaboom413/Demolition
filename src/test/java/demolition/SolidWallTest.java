package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SolidWallTest {
    
    public SolidWall solidWall;

    public SolidWallTest() {
        solidWall = new SolidWall(32, 32);
    }

    /**
    * Test the constructor method
    */
    @Test
    public void testConstructor() {
        assertEquals(32, solidWall.getX());
        assertEquals(32, solidWall.getY());
    }
}
