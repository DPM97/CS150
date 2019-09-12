

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RandomIntegerContainerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RandomIntegerContainerTest
{
    /**
     * Default constructor for test class RandomIntegerContainerTest
     */
        
    public RandomIntegerContainerTest()
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
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testAddToFront() {
        RandomIntegerContainer container = new RandomIntegerContainer();
        int[] intArr1 = new int[]{40, 30, 20};
        container.addToFront(intArr1[2]);
        container.addToFront(intArr1[1]);
        int[] result = container.addToFront(intArr1[0]);
        assertArrayEquals(intArr1, result);

    }
}
