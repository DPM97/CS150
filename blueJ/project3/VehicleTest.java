

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * The test class VehicleTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class VehicleTest
{
    /**
     * Default constructor for test class VehicleTest
     */
    public VehicleTest()
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
        Vehicle driver = new Vehicle(sim, graph);
        assertEquals(driver.state,"IDLE");
    }
    
    /**
     * test random node
     */
    @Test
    public void getRandNode() throws IOException {
        Graph graph = new Graph();
        graph.create(1000, 1, 1500);
        Simulation sim = new Simulation(graph,10, 5);
        Vehicle driver = new Vehicle(sim, graph);
        assertEquals(driver.node != null,true);
    }
    
    /**
     * test move method
     */
    @Test
    public void getRandDest() throws IOException {
        Graph graph = new Graph();
        graph.create(1000, 1, 1500);
        Simulation sim = new Simulation(graph,10, 5);
        Vehicle driver = new Vehicle(sim, graph);
        driver.gps.push(driver.node);
        driver.gps.push(driver.node.edges.get(0).end);
        driver.move();
        assertEquals(driver.loc > 0,true);
    }
}
