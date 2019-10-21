

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class EmployeeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EmployeeTest
{
    /**
     * Default constructor for test class EmployeeTest
     */
    public EmployeeTest()
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
     * make sure newCashier() returns a 
     * consistent cashier object
     */
    
    @Test
    public void testNewCashier() {
        Employee employeeController = new Employee();
        assertEquals(new Cashier(93).enter + new Cashier(93).wage, employeeController.newCashier(93).enter + employeeController.newCashier(93).wage);
    }
    
    /**
     * make sure newCook() returns a 
     * consisten cook object
     */
    
    @Test
    public void testNewCook() {
        Employee employeeController = new Employee();
        assertEquals(new Cook(43).enter + new Cook(43).wage, employeeController.newCook(43).enter + employeeController.newCook(43).wage);
    }
}
