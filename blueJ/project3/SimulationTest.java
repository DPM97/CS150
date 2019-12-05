

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * The test class SimulationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SimulationTest
{
    /**
     * Default constructor for test class SimulationTest
     */
    public SimulationTest()
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
     * test gen objects
     * @throws IOException for logger
     */
    @Test
    public void genObjects() throws IOException {
        Graph graph = new Graph();
        graph.create(1000, 1, 1500);
        Simulation sim = new Simulation(graph,10,10);
        sim.genObjects();
        assertEquals(sim.drivers.size(), 10);
    }
    
    /**
     * test add objects
     * @throws IOException for logger
     */
    @Test
    public void addObjects() throws IOException {
        Graph graph = new Graph();
        graph.create(1000, 1, 1500);
        Simulation sim = new Simulation(graph,10,10);
        sim.addObjects();
        assertEquals(sim.customers.size() > 0, true);
    }
    
    /**
     * test find closest vehicle
     * @throws IOException for logger
     */
    @Test
    public void findClosestVehicle() throws IOException {
        Graph graph = new Graph();
        graph.create(1000, 1, 1500);
        Simulation sim = new Simulation(graph,10,10);
        sim.drivers.add(new Vehicle(sim, graph));
        Customer customer = new Customer(sim, graph);
        sim.customers.add(customer);
        sim.findVehicle(customer);
        assertEquals(sim.drivers.get(0), customer.driver);
    }
    
    /**
     * checks tick
     * @throws IOException for logger
     */
    @Test
    public void checkTick() throws IOException {
        Graph graph = new Graph();
        graph.create(1000, 1, 1500);
        Simulation sim = new Simulation(graph,10,10);
        sim.drivers.add(new Vehicle(sim, graph));
        Customer customer = new Customer(sim, graph);
        sim.start();
        assertEquals(720, sim.tick);
    }
    
    /**
     * checks rides 
     * makes sure they aren't 0
     * @throws IOException for logger
     */
    @Test
    public void checkRides() throws IOException {
        Graph graph = new Graph();
        graph.create(1000, 1, 1500);
        Simulation sim = new Simulation(graph,10,10);
        sim.drivers.add(new Vehicle(sim, graph));
        Customer customer = new Customer(sim, graph);
        sim.start();
        assertEquals(true, sim.rides > 0);
    }
    
    /**
     * checks tick
     * @throws IOException for logger
     */
    @Test
    public void checkVehicles() throws IOException {
        Graph graph = new Graph();
        graph.create(1000, 1, 1500);
        Simulation sim = new Simulation(graph,10, 5);
        sim.genObjects();
        assertEquals(true, sim.checkVehicles());
        sim = new Simulation(graph,0,10);
        sim.genObjects();
        assertEquals(false, sim.checkVehicles());
    }
}
