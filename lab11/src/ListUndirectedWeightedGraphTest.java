

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

/**
 * The test class ListUndirectedWeightedGraphTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ListUndirectedWeightedGraphTest
{
    /**
     * Default constructor for test class ListUndirectedWeightedGraphTest
     */
    public ListUndirectedWeightedGraphTest()
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
     * test adding a node 
     */
    
    @Test
    public void testAddNode() {
        ListUndirectedWeightedGraph graph = new ListUndirectedWeightedGraph();
        graph.addNode("one");
        graph.addNode("two");
        assertEquals("[one, two]", Arrays.toString(graph.getNodeNames()));
    }
    
    /**
     * test adding an edge
     */
    
    @Test
    public void testAddEdge() {
        ListUndirectedWeightedGraph graph = new ListUndirectedWeightedGraph();
        graph.addNode("one");
        graph.addNode("two");
        graph.addEdge("one","two", 40);
        assertEquals("[START: one END: two WEIGHT: 40.0]", Arrays.toString(graph.getEdgeNames()));
    }
    
    /**
     * test adding an edge via number
     */
    
    @Test
    public void testAddEdge1() {
        ListUndirectedWeightedGraph graph = new ListUndirectedWeightedGraph();
        graph.addNode("one");
        graph.addNode("two");
        graph.addEdge(0,1,40);
        System.out.println(Arrays.toString(graph.getEdgeNames()));
        assertEquals("[START: one END: two WEIGHT: 40.0]", Arrays.toString(graph.getEdgeNames()));
    }
    
    /**
     * test update weight
     */
    
    @Test
    public void testUpdateWeight() {
        ListUndirectedWeightedGraph graph = new ListUndirectedWeightedGraph();
        graph.addNode("one");
        graph.addNode("two");
        graph.addEdge(0,1,40);
        assertEquals(true, graph.updateWeight("one", "two", 80));
    }
    
    /**
     * test update weight with num
     */
    
    @Test
    public void testUpdateWeight1() {
        ListUndirectedWeightedGraph graph = new ListUndirectedWeightedGraph();
        graph.addNode("one");
        graph.addNode("two");
        graph.addEdge(0,1,40);
        assertEquals(true, graph.updateWeight(0, 1, 80));
    }
    
    /**
     * test getting node names
     */
    
    @Test
    public void testGetNodeNames() {
        ListUndirectedWeightedGraph graph = new ListUndirectedWeightedGraph();
        graph.addNode("one");
        graph.addNode("two");
        graph.addNode("three");
        assertEquals("[one, two, three]", Arrays.toString(graph.getNodeNames()));
    }
    
    /**
     * get neighbor names
     */
    
    @Test
    public void testGetNeighborNames() {
        ListUndirectedWeightedGraph graph = new ListUndirectedWeightedGraph();
        graph.addNode("one");
        graph.addNode("two");
        graph.addNode("three");
        graph.addEdge(0,2,23);
        assertEquals("[three]", Arrays.toString(graph.getNeighborNames("one")));
    }
    
    /**
     * get neighbor nums
     */
    
    @Test
    public void testGetNeighborNums() {
        ListUndirectedWeightedGraph graph = new ListUndirectedWeightedGraph();
        graph.addNode("one");
        graph.addNode("two");
        graph.addNode("three");
        graph.addEdge(0,2,23);
        assertEquals("[2]", Arrays.toString(graph.getNeighborNums(0)));
    }
    
    /**
     * get edge names
     */
    
    @Test
    public void testGetEdgeNames() {
        ListUndirectedWeightedGraph graph = new ListUndirectedWeightedGraph();
        graph.addNode("one");
        graph.addNode("two");
        graph.addNode("three");
        graph.addEdge(0,2,23);
        graph.addEdge(1,2,40);
        assertEquals("[START: one END: three WEIGHT: 23.0, START: two END: three WEIGHT: 40.0]", Arrays.toString(graph.getEdgeNames()));
    }
    
    /**
     * get edge numbers
     */
    
    @Test
    public void testGetEdgeNumbers() {
        ListUndirectedWeightedGraph graph = new ListUndirectedWeightedGraph();
        graph.addNode("one");
        graph.addNode("two");
        graph.addNode("three");
        graph.addEdge(0,2,23);
        graph.addEdge(1,2,40);
        assertEquals("[START: 0 END: 2 WEIGHT: 23.0, START: 1 END: 2 WEIGHT: 40.0]", Arrays.toString(graph.getEdgeNumbers()));
    }
    
    /**
     * test emptying graph
     */
    
    @Test 
    public void testEmpty() {
        ListUndirectedWeightedGraph graph = new ListUndirectedWeightedGraph();
        graph.addNode("one");
        graph.addNode("two");
        graph.addNode("three");
        graph.addEdge(0,2,23);
        graph.addEdge(1,2,40);
        graph.empty();
        assertEquals("[]", Arrays.toString(graph.getNodeNames()));
    }
}
