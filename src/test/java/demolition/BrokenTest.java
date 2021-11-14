package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BrokenTest {
    
    public Broken broken;

    public BrokenTest() {
        broken = new Broken(32, 64);
    }

    /**
    * Test the broke method
    */
    @Test
    public void testBroke() {
        broken.broke();
        assertTrue(broken.getBroken());
    }

    /**
    * Test the broke method
    */
    @Test
    public void testGetBroken() {
        assertNotNull(broken.getBroken());
    }

    /**
    * Test the reset method
    */
    @Test
    public void testReset() {
        broken.reset();
        assertFalse(broken.getBroken());
    }
}
