

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PizzaTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PizzaTest
{
    /**
     * Default constructor for test class PizzaTest
     */
    public PizzaTest()
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
    
    /**
     * make sure it gets 
     * the correct cook time
     */
    
    @Test
    public void testGetWait() {
        Pizza pizza = new Pizza();
        assertEquals(6, pizza.getWait());
    }
}
