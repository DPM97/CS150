

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * The test class MapTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MapTest
{
    /**
     * Default constructor for test class MapTest
     */
    public MapTest()
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
    public void testCheckDirt() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        assertEquals(false, map.checkDirt());
    }
    
    @Test
    public void testGetLeft() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        assertEquals(-1, map.getLeft(30));
        assertEquals(30, map.getLeft(31));
    }
    
    @Test
    public void testGetRight() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        assertEquals(-1, map.getRight(89));
        assertEquals(127, map.getRight(126));
    }
    
    @Test
    public void testGetAbove() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        assertEquals(-1, map.getAbove(23));
        assertEquals(16, map.getAbove(46));
    }
    
    @Test
    public void testGetUnder() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        assertEquals(-1, map.getBelow(893));
        assertEquals(670, map.getBelow(640));
    }
}
