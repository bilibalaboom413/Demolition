package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ClockIconTest {
    
    public ClockIcon clockIcon;

    public ClockIconTest() {
        clockIcon = new ClockIcon(32, 32);
    }

    /**
    * Test the setTime() method
    */
    @Test
    public void testSetTime() {
        clockIcon.setTime(10);
        assertEquals(10, clockIcon.getTime());
    }

    /**
    * Test the getTime() method
    */
    @Test
    public void testGetTime() {
        assertNotNull(clockIcon.getTime());
    }

    /**
    * Test the tick() method
    */
    @Test
    public void testTick() {
        clockIcon.tick();
        assertNotNull(clockIcon.getTime());
    }
}