

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

/**
 * The test class WorkerListTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WorkerListTest
{
    /**
     * Default constructor for test class WorkerListTest
     */
    public WorkerListTest()
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
        Random random = new Random(30);
        ContactList list = new ContactList(random);
        list.add();
        list.add();
        list.add();
        list.quickSort(0, list.list.size() - 1);
        assertEquals(list.printData(), "IrhgbcDlzkr JwbacFvazdzd YffjvRvkxes ");
    }
    
    /**
     * test mergeSort
     */
    
    @Test 
    public void testMerge() {
        Random random = new Random(30);
        ContactList list = new ContactList(random);
        list.add();
        list.add();
        list.add();
        list.mergeSort(0, list.list.size() - 1);
        assertEquals(list.printData(), "IrhgbcDlzkr JwbacFvazdzd YffjvRvkxes ");
    }
    
    /**
     * make sure method
     * is printing the correct data
     */
    
    @Test 
    public void testPrint() {
        Random random = new Random(30);
        ContactList list = new ContactList(random);
        list.add();
        list.add();
        list.add();
        assertEquals(list.printData(), "IrhgbcDlzkr YffjvRvkxes JwbacFvazdzd ");
    }
}
