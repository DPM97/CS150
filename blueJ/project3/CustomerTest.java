

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * The test class CustomerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CustomerTest
{
    /**
     * Default constructor for test class CustomerTest
     */
    public CustomerTest()
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
     * test state getter
     */
    @Test
    public void getState() throws IOException {
        Graph graph = new Graph();
        graph.create(1000, 1, 1500);
        Simulation sim = new Simulation(graph,10, 5);
        Customer customer = new Customer(sim, graph);
        assertEquals(customer.state,"WAITING");
    }
    
    /**
     * test random node
     */
    @Test
    public void getRandNode() throws IOException {
        Graph graph = new Graph();
        graph.create(1000, 1, 1500);
        Simulation sim = new Simulation(graph,10, 5);
        Customer customer = new Customer(sim, graph);
        assertEquals(customer.node != null,true);
    }
    
    /**
     * test random destination
     */
    @Test
    public void getRandDest() throws IOException {
        Graph graph = new Graph();
        graph.create(1000, 1, 1500);
        Simulation sim = new Simulation(graph,10, 5);
        Customer customer = new Customer(sim, graph);
        assertEquals(customer.destination != null,true);
    }
}
