

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * The test class MainTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MainTest
{
    /**
     * Default constructor for test class MainTest
     */
    public MainTest()
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
     * tests to make sure the correct amount of tests 
     * are being read from the file
     * (dependent on the amount of tests in imput.txt)
     * 
     * Currently using 3 but this test wont work unless
     * the integer matches the amount of lines in the file
     */
    
    /**
     * checks to make sure the input file is read correctly
     * @throws IOException catch fileIO error
     */
    
    @Test
    public void testNumTests() throws IOException {
        Main main = new Main();
        assertEquals(3, main.numTests());
    }
}
