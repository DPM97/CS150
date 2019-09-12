

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CellTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CellTest
{
    /**
     * Default constructor for test class CellTest
     */
    public CellTest()
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
     * test the Append method
     */
    @Test
    public void testAppend() {
        String testString = " fb zh";
        Cell cell = new Cell();
        cell.append("fb");
        cell.append("zh");
        
         /**
         * call toString method and compare to expected
         */
        
        String finalString = cell.toString();
        assertEquals(testString, finalString);
    }
    
    /**
     * test the toString method
     */
    @Test
    public void testToString() {
        String testString = " bk lf sf";
        Cell cell = new Cell();
        
        /**
         * populate cells
         */
        cell.append("bk");
        cell.append("lf");
        cell.append("sf");

        /**
         * call toString method and compare to expected
         */
        String finalString = cell.toString();
        assertEquals(testString, finalString);
    }
}
