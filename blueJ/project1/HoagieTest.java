

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HoagieTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class HoagieTest
{
    /**
     * Default constructor for test class HoagieTest
     */
    public HoagieTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    
    @After
    public void tearDown()
    {
    }
    
    /**
     * make sure it gets 
     * the correct cook time
     */
    
    @Test
    public void testGetWait() {
        Hoagie hoagie = new Hoagie();
        assertEquals(5, hoagie.getWait());
    }
}
