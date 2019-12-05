

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LinearHashRosterTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LinearHashRosterTest
{
    /**
     * Default constructor for test class LinearHashRosterTest
     */
    public LinearHashRosterTest()
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
        LinearHashRoster roster = new LinearHashRoster(10, 0.8);
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
        LinearHashRoster roster = new LinearHashRoster(10, 0.8);
        roster.put(new StudentRecord("bill", "bob", "2031230123"));
        assertEquals(roster.containsKey("2031230123"), true);
    }
    
    /**
     * test containsValue
     */
    @Test
    public void testContainsValue() {
        LinearHashRoster roster = new LinearHashRoster(10, 0.8);
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        roster.put(record);
        assertEquals(roster.containsValue(record), true);
    }
    
    /**
     * test get
     */
    @Test
    public void testGet() {
        LinearHashRoster roster = new LinearHashRoster(10, 0.8);
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        roster.put(record);
        assertEquals(roster.get("2031230123"), record);
    }
    
    /**
     * test put
     */
    @Test
    public void testPut() {
        LinearHashRoster roster = new LinearHashRoster(10, 0.8);
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        roster.put(record);
        assertEquals(roster.get("2031230123"), record);
    }
    
    /**
     * test remove
     */
    public void testRemove() {
        LinearHashRoster roster = new LinearHashRoster(10, 0.8);
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        roster.put(record);
        assertEquals(roster.remove("2031230123"), record);
        assertEquals(roster.size(), 0);
    }
    
    /**
     * test keyset
     */
    public void testKeySet() {
        LinearHashRoster roster = new LinearHashRoster(10, 0.8);
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
        LinearHashRoster roster = new LinearHashRoster(10, 0.8);
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
        LinearHashRoster roster = new LinearHashRoster(10, 0.8);
        StudentRecord record = new StudentRecord("bill", "bob", "2031230123");
        StudentRecord record2 = new StudentRecord("bill", "bob", "2031234023");
        roster.put(record);
        roster.put(record2);
        assertEquals(roster.values().size(), 2);
    }
}
