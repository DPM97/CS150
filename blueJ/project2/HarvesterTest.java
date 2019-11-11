

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * The test class HarvesterTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class HarvesterTest
{
    /**
     * Default constructor for test class HarvesterTest
     */
    public HarvesterTest()
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
     * test move left method
     * @throws IOException exception for logger
     * @throws InterruptedException exception for wait method
     */
    
    @Test
    public void testLeft() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        Game game = new Game(map);
        game.createHarvester();
        Dwarf harvester = game.dwarfs.peek();
        harvester.goRight();
        harvester.goRight();
        harvester.goLeft();
        assertEquals(harvester.location, 1);
    }
    
    /**
     * test move right method
     * @throws IOException exception for logger
     * @throws InterruptedException exception for wait method
     */
    
    @Test 
    public void testRight() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        Game game = new Game(map);
        game.createHarvester();
        Dwarf harvester = game.dwarfs.peek();
        harvester.goRight();
        assertEquals(harvester.location, 1);
    }
    
    /**
     * test move up method
     * @throws IOException exception for logger
     * @throws InterruptedException exception for wait method
     */
    
    @Test
    public void testUp() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        Game game = new Game(map);
        game.createHarvester();
        Dwarf harvester = game.dwarfs.peek();
        harvester.goDown();
        harvester.goDown();
        harvester.goUp();
        assertEquals(harvester.location, 30);
    }
    
    /**
     * test move down method
     * @throws IOException exception for logger
     * @throws InterruptedException exception for wait method
     */
    
    @Test
    public void testDown() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        Game game = new Game(map);
        game.createHarvester();
        Dwarf harvester = game.dwarfs.peek();
        harvester.goDown();
        assertEquals(harvester.location, 30);
    }
}
