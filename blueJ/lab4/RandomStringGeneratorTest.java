

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RandomStringGeneratorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RandomStringGeneratorTest
{
    /**
     * Default constructor for test class RandomStringGeneratorTest
     */
    public RandomStringGeneratorTest()
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
    public void testCreateString() {
        String expected = "5409 7286 8685 2408 9532 5224 6460 2616 9223 7262 ";
        int seed = 102304;
        int numberOfItems = 10;
        RandomStringGenerator generator = new RandomStringGenerator(seed, numberOfItems);
        String result = generator.nextString();
        assertEquals(expected, result);
    }
}
