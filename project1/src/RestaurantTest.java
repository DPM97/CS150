import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * The test class RestaurantTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RestaurantTest
{
    /**
     * Default constructor for test class RestaurantTest
     */
    public RestaurantTest()
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
    public void testNewCustomer() throws IOException {
        Restaurant restaurant = new Restaurant(".","bagel", 99, 1, 5, 2, 2);
        restaurant.getEmployees();
        restaurant.accountForIncomingCustomers(1);
        System.out.println(restaurant.customerQueue);
    }
}
