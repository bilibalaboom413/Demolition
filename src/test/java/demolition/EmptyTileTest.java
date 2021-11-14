package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EmptyTileTest {

    public EmptyTile emptyTile;

    public EmptyTileTest() {
        emptyTile = new EmptyTile(32, 32);
    }
    
    /**
    * Test the constructor method
    */
    @Test
    public void testConstructor() {
        assertEquals(32, emptyTile.getX());
        assertEquals(32, emptyTile.getY());
    }
}
