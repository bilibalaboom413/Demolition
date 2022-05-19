package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BombGuyTest {
    
    BombGuy bombGuy;

    public BombGuyTest() {
        bombGuy = new BombGuy(96, 149);
    }

    /**
    * Test the reset method
    */
    @Test
    public void testReset() {
        bombGuy.reset();
        assertTrue(bombGuy.getMoveDown());
        assertEquals(96, bombGuy.getX());
        assertEquals(149, bombGuy.getY());
    }

    /**
    * Test the getMoveLeft() method
    */
    @Test
    public void testGetMoveLeft() {
        assertNotNull(bombGuy.getMoveLeft());
    }

    /**
    * Test the getMoveRight() method
    */
    @Test
    public void testGetMoveRight() {
        assertNotNull(bombGuy.getMoveRight());
    }

    /**
    * Test the getMoveUp() method
    */
    @Test
    public void testGetMoveUp() {
        assertNotNull(bombGuy.getMoveUp());
    }

    /**
    * Test the getMoveDown() method
    */
    @Test
    public void testGetMoveDown() {
        assertNotNull(bombGuy.getMoveDown());
    }

    /**
    * Test the getRelease() method
    */
    @Test
    public void testGetRelease() {
        assertNotNull(bombGuy.getRelease());
    }

    /**
    * Test the getSpace() method
    */
    @Test
    public void testGetSpace() {
        assertNotNull(bombGuy.getSpace());
    }

    /**
    * Test the setSpace() method
    */
    @Test
    public void testSetSpace() {
        bombGuy.setSpace(true);
        assertTrue(bombGuy.getSpace());
    }

    /**
    * Test the setRelease() method
    */
    @Test
    public void testSetRelease() {
        bombGuy.setRelease(true);
        assertTrue(bombGuy.getRelease());
    }


    /**
    * Test the getCanBomb() method
    */
    @Test
    public void testGetCanBomb() {
        assertNotNull(bombGuy.getCanBomb());
    }

    /**
    * Test the setCanBomb() method
    */
    @Test
    public void testSetCanBomb() {
        bombGuy.setCanBomb(false);
        assertFalse(bombGuy.getCanBomb());
    }

    /**
    * Test the pressLeft() method
    */
    @Test
    public void testPressLeft() {
        bombGuy.pressLeft();
        assertTrue(bombGuy.getLeft());
        assertFalse(bombGuy.getRight());
    }

    /**
    * Test the pressRight() method
    */
    @Test
    public void testPressRight() {
        bombGuy.pressRight();
        assertTrue(bombGuy.getRight());
        assertFalse(bombGuy.getLeft());
    }

    /**
    * Test the pressUp() method
    */
    @Test
    public void testPressUp() {
        bombGuy.pressUp();
        assertTrue(bombGuy.getUp());
        assertFalse(bombGuy.getDown());
    }

    /**
    * Test the pressDown() method
    */
    @Test
    public void testPressDown() {
        bombGuy.pressDown();
        assertTrue(bombGuy.getDown());
        assertFalse(bombGuy.getUp());
    }

    /**
    * Test the pressSpace() method
    */
    @Test
    public void testPressSpace() {
        bombGuy.pressSpace();
        assertTrue(bombGuy.getSpace());
    }

    /**
    * Test the tick() method to tell
    * whether bombGuy can move in any direction
    */
    @Test
    public void testTick() {
        bombGuy.pressLeft();
        bombGuy.tick();
        bombGuy.pressRight();
        bombGuy.tick();
        bombGuy.pressUp();
        bombGuy.tick();
        bombGuy.pressDown();
        bombGuy.tick();
    }

}
