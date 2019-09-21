

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StringContainerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StringContainerTest
{
    /**
     * Default constructor for test class StringContainerTest
     */
    public StringContainerTest()
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
    public void insertionTest() {
        String expected = "[1002, 1033, 4500, 5023, 9802]";
        expected = expected.toString();
        StringContainer container = new StringContainer();
        container.addToList(1033);
        container.addToList(5023);
        container.addToList(1002);
        container.addToList(4500);
        container.addToList(9802);

        String result = container.insertion();
        assertEquals(expected, result);
    }
    
    @Test
    public void selectionTest() {
        String expected = "[1002, 1033, 4500, 5023, 9802]";
        expected = expected.toString();
        StringContainer container = new StringContainer();
        container.addToList(1033);
        container.addToList(5023);
        container.addToList(1002);
        container.addToList(4500);
        container.addToList(9802);

        String result = container.selection();
        assertEquals(expected, result);
    }
    
    @Test
    public void linearTest() {
        int expected = 2; //index expected
        StringContainer container = new StringContainer();
        container.addToList(1002);
        container.addToList(1033);
        container.addToList(4500);
        container.addToList(5023);
        container.addToList(9802);

        int result = container.linear(4500);
        assertEquals(expected, result);
    }
    
    @Test
    public void linearTestNotFound() {
        int expected = -1; //index expected
        StringContainer container = new StringContainer();
        container.addToList(1002);
        container.addToList(1033);
        container.addToList(4500);
        container.addToList(5023);
        container.addToList(9802);

        int result = container.linear(3030);
        assertEquals(expected, result);
    }
    
    @Test
    public void binaryTest() {
        int expected = 3; //index expected
        StringContainer container = new StringContainer();
        container.addToList(1002);
        container.addToList(1033);
        container.addToList(4500);
        container.addToList(5023);
        container.addToList(9802);
        int result = container.binary(5023);
        assertEquals(expected, result);
    }
    
    @Test
    public void binaryTestNotFound() {
        int expected = -1; //index expected
        StringContainer container = new StringContainer();
        container.addToList(1002);
        container.addToList(1033);
        container.addToList(4500);
        container.addToList(5023);
        container.addToList(9802);
        int result = container.binary(4023);
        assertEquals(expected, result);
    }
}
