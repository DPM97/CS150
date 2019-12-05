

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TreeRosterTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TreeRosterTest
{
    /**
     * Default constructor for test class TreeRosterTest
     */
    public TreeRosterTest()
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
     * test containsKey
     */
    @Test
    public void testContainsKey() {
        TreeRoster roster = new TreeRoster();
        roster.put(new StudentRecord("bill", "bob", "2031230123"));
        assertEquals(roster.containsKey("2031230123"), true);
    }
    
    /**
     * test containsValue
     */
    @Test
    public void testContainsValue() {
        TreeRoster roster = new TreeRoster();
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        roster.put(record);
        assertEquals(roster.containsValue(record), true);
    }
    
    /**
     * test get
     */
    @Test
    public void testGet() {
        TreeRoster roster = new TreeRoster();
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        roster.put(record);
        assertEquals(roster.get("2031230123"), record);
    }
    
    /**
     * test put
     */
    @Test
    public void testPut() {
        TreeRoster roster = new TreeRoster();
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        roster.put(record);
        assertEquals(roster.get("2031230123"), record);
    }
    
    /**
     * test remove
     */
    public void testRemove() {
        TreeRoster roster = new TreeRoster();
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        roster.put(record);
        assertEquals(roster.remove("2031230123"), record);
        assertEquals(roster.size(), 0);
    }
}
