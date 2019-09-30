import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MyListStringContainerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MyListStringContainerTest
{
    /**
     * Default constructor for test class MyListStringContainerTest
     */
    public MyListStringContainerTest()
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
    public void testAddToback() {
        String expected = "12 34 56 78";
        MyListStringContainer container = new MyListStringContainer();
        container.addToBack("12");
        container.addToBack("34");
        container.addToBack("56");
        container.addToBack("78");
        String result = container.get(0);
        for (int i = 1; i < 4; i++) {
            result += " ";
            result += container.get(i);
        }
        assertEquals(expected, result);
    }
    
    @Test 
    public void testAddToFront() {
        String expected = "12 34 56 78";
        MyListStringContainer container = new MyListStringContainer();
        container.addToFront("78");
        container.addToFront("56");
        container.addToFront("34");
        container.addToFront("12");
        String result = container.get(0);
        for (int i = 1; i < 4; i++) {
            result += " ";
            result += container.get(i);
        }
        assertEquals(expected, result);
    }
    
    @Test
    public void testSubStringIterator() {
        int expected = 2;
        MyListStringContainer container = new MyListStringContainer();
        container.addToBack("78");
        container.addToBack("56");
        container.addToBack("34");
        container.addToBack("12");
        int result = container.subStringIterator("3");
        assertEquals(expected, result);
    }
    
    @Test
    public void testSubString() {
        int expected = 3;
        MyListStringContainer container = new MyListStringContainer();
        container.addToBack("78");
        container.addToBack("56");
        container.addToBack("34");
        container.addToBack("12");
        int result = container.subString("2");
        assertEquals(expected, result);
    }
        
}
