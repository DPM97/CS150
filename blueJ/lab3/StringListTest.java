import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StringListTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StringListTest
{
    /**
     * Default constructor for test class StringListTest
     */
    public StringListTest()
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
     * test to see if the addToBack method works correctly
     */
    @Test
    public void testAddToback() {
        String testString = "ab fb ls pq gh";
        StringListADT list = new StringList();
        
        /**
         * populate list
         */
        list.append("ab");
        list.append("fb");
        list.append("ls");
        list.append("pq");
        list.append("gh");
        
        /**
         * get string value and check its validity
         */
        String finalString = list.toString(); 
        assertEquals(testString, finalString);
    }
    
    /**
     * test the toString method
     */
    
    @Test
    public void testToString() { //tests to see if the toString method returns a valid string
        String testString = "gf lg jf lr vf";
        StringListADT list = new StringList();
        
        /**
         * populate list
         */
        list.append("gf");
        list.append("lg");
        list.append("jf");
        list.append("lr");
        list.append("vf");
        
        /**
         * call toString
         */
        String finalString = list.toString();
        assertEquals(testString, finalString);
    }
    
    /**
     * test isEmpty (false)
     */
    
    @Test
    public void testIsEmpty1() {
        StringListADT list = new StringList();
        list.append("fb");
        boolean finalVal = list.isEmpty();
        assertEquals(false, finalVal);
    }
    
    /**
     * test isEmpty (true)
     */
    
    @Test
    public void testIsEmpty2() {
        StringListADT list = new StringList();
        boolean finalVal = list.isEmpty();
        assertEquals(true, finalVal);
    }
}
