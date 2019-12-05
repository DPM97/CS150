

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ChainHashRosterTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ChainHashRosterTest
{
    /**
     * Default constructor for test class ChainHashRosterTest
     */
    public ChainHashRosterTest()
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
     * test clear
     */
    public void testClear() {
        ChainHashRoster roster = new ChainHashRoster(10, 5);
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        roster.put(record);
        roster.clear();
        assertEquals(roster.size(), 0);
    }
    
    /**
     * test containsKey
     */
    @Test
    public void testContainsKey() {
        ChainHashRoster roster = new ChainHashRoster(10, 5);
        roster.put(new StudentRecord("bill", "bob", "2031230123"));
        assertEquals(roster.containsKey("2031230123"), true);
    }
    
    /**
     * test containsValue
     */
    @Test
    public void testContainsValue() {
        ChainHashRoster roster = new ChainHashRoster(10, 5);
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        roster.put(record);
        assertEquals(roster.containsValue(record), true);
    }
    
    /**
     * test get
     */
    @Test
    public void testGet() {
        ChainHashRoster roster = new ChainHashRoster(10, 5);
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        roster.put(record);
        assertEquals(roster.get("2031230123"), record);
    }
    
    /**
     * test put
     */
    @Test
    public void testPut() {
        ChainHashRoster roster = new ChainHashRoster(10, 5);
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        roster.put(record);
        assertEquals(roster.get("2031230123"), record);
    }
    
    /**
     * test remove
     */
    public void testRemove() {
        ChainHashRoster roster = new ChainHashRoster(10, 5);
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        roster.put(record);
        assertEquals(roster.remove("2031230123"), record);
        assertEquals(roster.size(), 0);
    }
    
    /**
     * test keyset
     */
    public void testKeySet() {
        ChainHashRoster roster = new ChainHashRoster(10, 5);
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        StudentRecord record2 = new StudentRecord("bill", "bob", "2031234023");
        roster.put(record);
        roster.put(record2);
        assertEquals(roster.keySet().size(), 2);
    }
    
    /**
     * test size
     */
    public void testSize() {
        ChainHashRoster roster = new ChainHashRoster(10, 5);
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        StudentRecord record2 = new StudentRecord("bill", "bob", "2031234023");
        roster.put(record);
        roster.put(record2);
        assertEquals(roster.size(), 2);
    }
    
    /**
     * test keyset
     */
    public void testValues() {
        ChainHashRoster roster = new ChainHashRoster(10, 5);
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        StudentRecord record2 = new StudentRecord("bill", "bob", "2031234023");
        roster.put(record);
        roster.put(record2);
        assertEquals(roster.values().size(), 2);
    }
}
