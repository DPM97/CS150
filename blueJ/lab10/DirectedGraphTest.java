

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DirectedGraphTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DirectedGraphTest
{
    /**
     * Default constructor for test class DirectedGraphTest
     */
    public DirectedGraphTest()
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
     * test adding a node to the graph
     */
    
    @Test
    public void addNode() {
        DirectedGraph graph = new DirectedGraph<String>();
        assertEquals(0, graph.nodes.size());
        graph.addNode("b");
        assertEquals(1, graph.nodes.size());
    }
    
    /**
     * test adding an edge to a node
     */
    
    @Test
    public void addEdge() {
        DirectedGraph graph = new DirectedGraph<String>();
        graph.addNode("a");
        graph.addNode("b");
        graph.addEdge("a","b",5);
        assertEquals(1, graph.getNeighbors("a").size());
    }
    
   /**
    * test getting neighbors of node
    */ 
    
    @Test
    public void getNeighbors() {
        DirectedGraph graph = new DirectedGraph<String>();
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addNode("d");
        graph.addEdge("a","b",5);
        graph.addEdge("a","c",4);
        graph.addEdge("c","d",10);
        assertEquals(2, graph.getNeighbors("a").size());
    }
    
    /**
     * test dijkstra's shortest path
     */
    
    @Test
    public void dijkstra() {
        DirectedGraph graph = new DirectedGraph<String>();
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addNode("d");
        graph.addEdge("a","b",5);
        graph.addEdge("a","c",4);
        graph.addEdge("c","d",10);
        graph.dijkstra("a");
        assertEquals("a 0 c 4 b 5 d 14 ", graph.dijkstraString);
    }
}
