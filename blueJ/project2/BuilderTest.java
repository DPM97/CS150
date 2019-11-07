import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * The test class BuilderTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BuilderTest
{
    /**
     * Default constructor for test class BuilderTest
     */
    public BuilderTest()
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
    public void testLeft() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        Game game = new Game(map);
        game.createBuilder();
        Dwarf builder = game.dwarfs.peek();
        builder.goRight();
        builder.goRight();
        builder.goLeft();
        assertEquals(builder.location, 1);
    }
    
    @Test 
    public void testRight() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        Game game = new Game(map);
        game.createBuilder();
        Dwarf builder = game.dwarfs.peek();
        builder.goRight();
        assertEquals(builder.location, 1);
    }
    
    @Test
    public void testUp() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        Game game = new Game(map);
        game.createBuilder();
        Dwarf builder = game.dwarfs.peek();
        builder.goDown();
        builder.goDown();
        builder.goUp();
        assertEquals(builder.location, 30);
    }
    
    @Test
    public void testDown() throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        Game game = new Game(map);
        game.createBuilder();
        Dwarf builder = game.dwarfs.peek();
        builder.goDown();
        assertEquals(builder.location, 30);
    }
    
    
}
