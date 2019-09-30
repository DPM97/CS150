

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MyLinkedListTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MyLinkedListTest
{
    /**
     * Default constructor for test class MyLinkedListTest
     */
    public MyLinkedListTest()
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
    public void testAddFirst() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        String expected = "78 56 34 12";
        list.addFirst("12");
        list.addFirst("34");
        list.addFirst("56");
        list.addFirst("78");
        String result = list.get(0);
        for (int i = 1; i < 4; i++) {
            result += " ";
            result += list.get(i);
        }
        assertEquals(expected, result);
    }
    
    @Test
    public void testAddEnd() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        String expected = "12 34 56 78";
        list.addEnd("12");
        list.addEnd("34");
        list.addEnd("56");
        list.addEnd("78");
        String result = list.get(0);
        for (int i = 1; i < 4; i++) {
            result += " ";
            result += list.get(i);
        }
        assertEquals(expected, result);
    }
    
    @Test
    public void testGet() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        String expected = "34";
        list.addEnd("12");
        list.addEnd("34");
        list.addEnd("56");
        list.addEnd("78");
        String result = list.get(1);
        assertEquals(expected, result);
    }
}
