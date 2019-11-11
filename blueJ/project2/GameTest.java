

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * The test class GameTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GameTest
{
    /**
     * Default constructor for test class GameTest
     */
    public GameTest()
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
     * test fetchGold method
     * @throws IOException exception for logger
     * @throws InterruptedException exception for wait method
     */
    
    @Test
    public void testFetchGold() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        Game game = new Game(map);
        assertEquals(17, game.fetchTotalGold());
    }
    
    /**
     * test createDigger method
     * @throws IOException exception for logger
     * @throws InterruptedException exception for wait method
     */
    
    @Test
    public void testCreateDigger() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        Game game = new Game(map);
        game.createDigger();
        assertEquals(true, game.dwarfs.peek().getClass().getName().equals("Digger"));
    }
    
    /**
     * test createHarvester method
     * @throws IOException exception for logger
     * @throws InterruptedException exception for wait method
     */
    
    @Test public void testCreateHarvester() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        Game game = new Game(map);
        game.createHarvester();
        assertEquals(true, game.dwarfs.peek().getClass().getName().equals("Harvester"));
    }
    
    /**
     * test createBuilder method
     * @throws IOException exception for logger
     * @throws InterruptedException exception for wait method
     */
   
    @Test 
    public void testCreateBuilder() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        Game game = new Game(map);
        game.createBuilder();
        assertEquals(true, game.dwarfs.peek().getClass().getName().equals("Builder"));
    }
}
