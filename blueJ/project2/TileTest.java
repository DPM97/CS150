

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * The test class TileTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TileTest
{
    /**
     * Default constructor for test class TileTest
     */
    public TileTest()
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
    public void addDwarf() throws IOException {
        Map map = new Map();
        Game game = new Game(map);
        Tile tile = new Tile("0");
        tile.dwarfs.put(new Digger(map, game), 1);
        assertEquals(true, tile.dwarfs.containsValue(1));
    }
}
