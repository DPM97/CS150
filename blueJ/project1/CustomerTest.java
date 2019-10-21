

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CustomerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CustomerTest
{
    /**
     * Default constructor for test class CustomerTest
     */
    public CustomerTest()
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
     * makes sure customer has correct object(bagel)
     */
    
    @Test
    public void testBagelObject() {
        Customer customer = new Customer(0, new Bagel(), 0);
        assertEquals("Bagel", customer.order.getClass().getName());
    }
    
    /**
     * makes sure customer has correct object(hoagie)
     */
    
    @Test
    public void testHoagieObject() {
        Customer customer = new Customer(0, new Hoagie(), 0);
        assertEquals("Hoagie", customer.order.getClass().getName());
    }
    
    /**
     * makes sure customer has correct object(pizza)
     */
    
    @Test
    public void testPizzaObject() {
        Customer customer = new Customer(0, new Pizza(), 0);
        assertEquals("Pizza", customer.order.getClass().getName());
    }
}
