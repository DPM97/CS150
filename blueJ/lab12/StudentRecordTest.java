

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StudentRecordTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StudentRecordTest
{
    /**
     * Default constructor for test class StudentRecordTest
     */
    public StudentRecordTest()
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
     * get first name
     */
    @Test
    public void testFirstName() {
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        assertEquals(record.getFirstName(), "bill");
    }
    
    /**
     * get last name
     */
    @Test
    public void testLastName() {
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        assertEquals(record.getLastName(), "bob");
    }
    
    /**
     * get ID
     */
    @Test
    public void testGetID() {
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        assertEquals(record.getID(), "2031230123");
    }
}
