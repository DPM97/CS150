
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * The test class DiggerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DiggerTest
{
    /**
     * Default constructor for test class DiggerTest
     */
    public DiggerTest()
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
        game.createDigger();
        Dwarf digger = game.dwarfs.peek();
        digger.goRight();
        digger.goRight();
        digger.goLeft();
        assertEquals(digger.location, 1);
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
        game.createDigger();
        Dwarf digger = game.dwarfs.peek();
        digger.goRight();
        assertEquals(digger.location, 1);
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
        game.createDigger();
        Dwarf digger = game.dwarfs.peek();
        digger.goDown();
        digger.goDown();
        digger.goUp();
        assertEquals(digger.location, 30);
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
        game.createDigger();
        Dwarf digger = game.dwarfs.peek();
        digger.goDown();
        assertEquals(digger.location, 30);
    }
    
    /**
     * test the move method
     * @throws IOException exception for logger
     * @throws InterruptedException exception for wait method
     */
    
    @Test
    public void testMove() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        Game game = new Game(map);
        game.createDigger();
        Dwarf digger = game.dwarfs.peek();
        digger.move();
        assertEquals(digger.location, 1);
    }
}
