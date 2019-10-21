import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.*;

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
    
    /**
     * test newCustomer method
     * makes sure customer is added to 
     * the correct adt
     * @throws IOException catch fileIO error
     */
    
    @Test
    public void testNewCustomer() throws IOException {
        Restaurant restaurant = new Restaurant(".","bagel", 99, 1, 5, 2, 2);
        restaurant.getEmployees();
        restaurant.accountForIncomingCustomers(1);
        assertEquals(1, restaurant.customerQueue.size()); //one customer should be in queue
    }
    
    /**
     * makes sure customer
     * is removed from the cookQueue
     * at the correct time
     * @throws IOException catch fileIO error
     */
    
    @Test
    public void testLeavingCustomer() throws IOException {
        Restaurant restaurant = new Restaurant(".","bagel", 99, 1, 5, 2, 2);
        restaurant.getEmployees();
        restaurant.accountForIncomingCustomers(1);
        restaurant.tick = 1;
        restaurant.checkCooks();
        restaurant.tick = 4;
        restaurant.accountForLeavingCustomers(); //customer should have left by now
        assertEquals(0, restaurant.customerQueue.size());
    }
    
    /**
     * tests multiple customers leaving
     * @throws IOException catch fileIO error
     */
    
    @Test
    public void testLeavingCustomers() throws IOException {
        Restaurant restaurant = new Restaurant(".","bagel", 99, 1, 5, 2, 1);
        restaurant.getEmployees();
        restaurant.accountForIncomingCustomers(3);
        restaurant.tick++;
        restaurant.checkCooks();
        restaurant.tick++;
        restaurant.checkCooks();
        restaurant.tick = 4;
        restaurant.checkCooks();
        restaurant.accountForLeavingCustomers();
        assertEquals(2, restaurant.cookQueue.size()); //there should still be 2 customer waiting for food (only 1 chef);
    }
    
    /**
     * make sure employee
     * break method works correctly
     * @throws IOException catch fileIO error
     */
    
    @Test
    public void testBreakTimeFunction() throws IOException {
        Restaurant restaurant = new Restaurant(".","bagel", 99, 1, 5, 2, 2);
        restaurant.tick = 360;
        restaurant.getEmployees();
        restaurant.accountForIncomingCustomers(3);
        Queue<Customer> queue = restaurant.customerQueue;
        restaurant.checkShiftChange();
        if (queue == restaurant.customerQueue && restaurant.tick == 365) { //nothing happens to customers over the break time
            assertEquals(1, 1);
        } else {
            assertEquals(1, 0);
        }
    }
    
    /**
     * test the cookWait method
     * make sure food is being
     * cooked at the correct pace
     * and orders are being processed 
     * at the correct ticks
     * @throws IOException catch fileIO error
     */
    
    @Test 
    public void testCookWaitTime() throws IOException {
        Restaurant restaurant = new Restaurant(".","bagel", 99, 1, 5, 1, 2);
        restaurant.getEmployees();
        restaurant.accountForIncomingCustomers(2);
        restaurant.tick = 1;
        restaurant.checkCooks();
        restaurant.tick = 4;
        restaurant.accountForLeavingCustomers();
        //1 customer should be done here
        if (restaurant.ordersFilled == 1) { 
            restaurant.checkCooks();
            restaurant.tick = 6;
            restaurant.accountForLeavingCustomers();
            assertEquals(2, restaurant.ordersFilled);  //second customer should be done now
        } else {
            assertEquals(1,0);
        }
    }
    
    /**
     * test the getEmployee methods
     * makes sure it adds the correct 
     * amount of each employee (cook, cashier)
     * @throws IOException catch fileIO error
     */
    
    @Test
    public void testGetEmployees() throws IOException {
        Restaurant restaurant = new Restaurant(".","bagel", 99, 1, 5, 4, 1);
        restaurant.getEmployees();
        if (restaurant.cooks.size() == 4 && restaurant.cashiers.size() == 1) {
            assertEquals(1,1);
        } else {
            assertEquals(1,0);
        }
    }
    
    /**
     * test the line waitTime method
     * make sure the correct wait is calculuated
     * when multiple groups come in
     * @throws IOException catch fileIO error
     */
    
    @Test
    public void testLineWaitTime() throws IOException {
        Restaurant restaurant = new Restaurant(".","bagel", 99, 1, 5, 4, 2);
        restaurant.getEmployees();
        restaurant.accountForIncomingCustomers(4); //add first 4 customers
        restaurant.tick++;
        restaurant.checkCooks();
        if (restaurant.getWait() == 2) { //wait should be 2
            restaurant.tick++;
            restaurant.checkCooks();
            restaurant.accountForIncomingCustomers(4); //add another 4 customers
            assertEquals(6, restaurant.getWait());
        } else {
            assertEquals(1,0);
        }
    }
    
    /**
     * make sure multiple
     * customers are added
     * note - the method that gets the input
     * integer is random in the main program
     * @throws IOException catch fileIO error
     */
    
    @Test
    public void testFetchIncomingCustomers() throws IOException {
        Restaurant restaurant = new Restaurant(".","bagel", 99, 1, 5, 4, 2);
        restaurant.getEmployees();
        restaurant.accountForIncomingCustomers(4);
        assertEquals(4, restaurant.customerQueue.size());
    }
    
    /**
     * make sure restaurant with bagel input
     * returns the correct food object
     * @throws IOException catch fileIO error
     */
    
    @Test
    public void testTypeBagel() throws IOException {
        Restaurant restaurant = new Restaurant(".","bagel", 99, 1, 5, 4, 2);
        assertEquals("bagel", restaurant.type);
    }
    
    /**
     * make sure restaurant with hoagie input
     * returns the correct food object
     * @throws IOException catch fileIO error
     */
    
    @Test
    public void testTypeHoagie() throws IOException {
        Restaurant restaurant = new Restaurant(".","hoagie", 99, 1, 5, 4, 2);
        assertEquals("hoagie", restaurant.type);
    }
    
    /**
     * make sure restaurant with pizza input
     * returns the correct food object
     * @throws IOException catch fileIO error
     */
    
    @Test
    public void testTypePizza() throws IOException {
        Restaurant restaurant = new Restaurant(".","pizza", 99, 1, 5, 4, 2);
        assertEquals("pizza", restaurant.type);
    }
    
    /**
     * tests the satisfaction calculation
     * @throws IOException catch fileIO error
     */
    
    @Test
    public void testSatisfaction() throws IOException {
        Restaurant restaurant = new Restaurant(".","pizza", 99, 1, 5, 2, 2);
        restaurant.getEmployees();
        restaurant.accountForIncomingCustomers(1);
        restaurant.tick++;
        restaurant.checkCooks();
        restaurant.tick = 7;
        restaurant.checkCooks();
        restaurant.accountForLeavingCustomers(); //this customer has gotten his pizza (7 ticks) (.93 satisfaction) (first order so avg. shouldnt matter);
        assertEquals(0.93, restaurant.satisfaction, 0.01); //avg. satisfaction should be .93 (based off of this one customer)
    }
    
    /**
     * tests busyness variable
     * @throws IOException catch fileIO error
     */
    
    @Test
    public void testBusyness() throws IOException {
        Restaurant restaurant = new Restaurant(".","pizza", 43, 1, 5, 2, 2);
        assertEquals(57, restaurant.business); //busyness = (100 - input for algorithm sake)
    }
}
