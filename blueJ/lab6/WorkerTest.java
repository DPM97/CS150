

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

/**
 * The test class WorkerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WorkerTest
{
    /**
     * Default constructor for test class WorkerTest
     */
    public WorkerTest()
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
    public void testGetFirstName() {
        Worker worker = new Worker(new Random(5));
        //expected = Cplqo;
        assertEquals(worker.firstName, "Cplqo");
    }
    
    @Test
    public void testGetLastName() {
        Worker worker = new Worker(new Random(5));
        //expected = Pquxof;
        assertEquals(worker.lastName, "Pquxof");
    }
}
