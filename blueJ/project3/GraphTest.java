

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Stack;

/**
 * The test class GraphTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GraphTest
{
    /**
     * Default constructor for test class GraphTest
     */
    public GraphTest()
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
     * test create method
     */
    @Test
    public void create() {
        Graph graph = new Graph();
        graph.create(10, 1, 2000);
        assertEquals(graph.nodes.size(), 10);
    }
    
    /**
     * test calculate connectivity
     */
    @Test
    public void connectivity() {
        Graph graph = new Graph();
        graph.create(10, 1, 2000);
        float total = 0;
        for (int i = 0; i < graph.nodes.size(); i++) {
            total += graph.nodes.get(i).edges.size();
        }
        assertEquals(total / graph.nodes.size(),graph.calcConnectivity(), 2);
    }
    
   /**
    * test randnode function
    */ 
   @Test
   public void randNode() {
        Graph graph = new Graph();
        graph.create(1000, 1, 2000);
        assertEquals(false, graph.randNode() == graph.randNode());
   }
   
   /**
    * test fetchpath function
    */ 
   @Test
   public void fetchPath() {
        Graph graph = new Graph();
        graph.create(1000, 1, 2000);
        Stack<Graph.GraphNode> path = graph.fetchPath(graph.nodes.get(20), graph.nodes.get(30));
        assertEquals(true, path.size() > 0);
   }
   
   /**
    * test addEdge
    */
   
   @Test
   public void addEdge() {
       
   }
}
