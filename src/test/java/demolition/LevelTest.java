package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LevelTest {
    
    Level level;

    public LevelTest() {
        level = new Level();
    }

    /**
    * Test the getPlayerX() method
    */
    @Test
    public void testGetPlayerX() {
        assertNotNull(level.getPlayerX());
    }

    /**
    * Test the getPlayerY() method
    */
    @Test
    public void testGetPlayerY() {
        assertNotNull(level.getPlayerY());
    }

    /**
    * Test the getRedX() method
    */
    @Test
    public void testGetRedX() {
        assertNotNull(level.getRedX());
    }

    /**
    * Test the getRedY() method
    */
    @Test
    public void testGetRedY() {
        assertNotNull(level.getRedY());
    }

    /**
    * Test the getYellowX() method
    */
    @Test
    public void testGetYellowX() {
        assertNotNull(level.getYellowX());
    }

    /**
    * Test the getYellowY() method
    */
    @Test
    public void testGetYellowY() {
        assertNotNull(level.getYellowY());
    }

    /**
    * Test the getSolidWall() method
    */
    @Test
    public void testGetSolidWall() {
        assertNull(level.getSolidWall());
    }

    /**
    * Test the getBroken() method
    */
    @Test
    public void testGetBroken() {
        assertNull(level.getBroken());
    }

    /**
    * Test the getEmptyTile() method
    */
    @Test
    public void testGetEmptyTile() {
        assertNull(level.getEmptyTile());
    }

    /**
    * Test the getGoal() method
    */
    @Test
    public void testGetGoal() {
        assertNull(level.getGoal());
    }

    /**
    * Test the getMap() method
    */
    @Test
    public void testGetMap() {
        assertNull(level.getMap());
    }

    /**
    * Test the readMap() method
    */
    @Test
    public void testReadMap() {
        level.readMap("level1.txt");
        assertNotNull(level.getMap());
    }

    /**
    * Test the generateMap() method
    */
    @Test
    public void testGenerateMap() {
        level.readMap("level1.txt");
        level.generateMap(level.getMap());
        assertNotNull(level.getMap());
    }
}
