

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

/**
 * The test class ContactListTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ContactListTest
{
    /**
     * Default constructor for test class ContactListTest
     */
    public ContactListTest()
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
     * test quickSort
     */ 
    
    @Test
    public void testQuick() {
        Random random = new Random(45);
        //expected output = 18816713 56842568 68763175
        WorkerList list = new WorkerList(random);
        list.add();
        list.add();
        list.add();
        list.quickSort(0, list.list.size() - 1);
        assertEquals(list.printData(), "18816713 56842568 68763175 ");
    }
    
    /**
     * test mergeSort
     */
    
    @Test 
    public void testMerge() {
        Random random = new Random(45);
        //expected output = 18816713 56842568 68763175
        WorkerList list = new WorkerList(random);
        list.add();
        list.add();
        list.add();
        list.mergeSort(0, list.list.size() - 1);
        assertEquals(list.printData(), "18816713 56842568 68763175 ");
    }
    
    /**
     * make sure method
     * is printing the correct data
     */
    
    @Test 
    public void testPrint() {
        Random random = new Random(45);
        //expected output = 68763175 56842568 18816713
        WorkerList list = new WorkerList(random);
        list.add();
        list.add();
        list.add();
        assertEquals(list.printData(), "68763175 56842568 18816713 ");
    }
}
