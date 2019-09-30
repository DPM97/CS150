

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MyLinkedListIteratorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MyLinkedListIteratorTest
{
    /**
     * Default constructor for test class MyLinkedListIteratorTest
     */
    public MyLinkedListIteratorTest()
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
    public void testHasNext() {
        boolean expected = true;
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.addFirst("12");
        list.addFirst("13");
        MyLinkedListIterator<String> iterator = list.iterator();
        boolean result = iterator.hasNext();
        assertEquals(expected, result);
    }
    
    @Test
    public void testNext() {
        String expected = "13";
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.addFirst("12");
        list.addFirst("13");
        MyLinkedListIterator<String> iterator = list.iterator();
        Node<String> result = iterator.next();
        assertEquals(expected, result.value);        
    }
}
