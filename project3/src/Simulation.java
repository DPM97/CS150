import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * sim class
 */

public class Simulation {

    /**
     * file logger
     */
    private final Logger logger;
    /**
     * graph obj
     */
    public Graph graph;
    /**
     * drivers / vehicles
     */
    public ArrayList<Vehicle> drivers;
    /**
     * customers
     */
    public ArrayList<Customer> customers;
    /**
     * tick (time unit) (1 tick = 1/10 sec)
     */
    int tick;
    /**
     * amount of drivers
     */
    int driverAmount;
    /**
     * amount of customers
     */
    int customerAmount;
    /**
     * rides
     */
    int rides;
    /**
     * avg satisfaction
     */
    double satisfaction;
    /**
     * avg driver wait time
     */
    double dWaitTime;
    /**
     * avg ride dist
     */
    double rideDist;
    /**
     * customers that have finished and need to be wiped
     */
    ArrayList<Customer> finished;

    /**
     * constructor
     * @param graph graph obj'
     * @param drivers driver amount
     * @param customers customers per node
     * @throws IOException for logger
     */

    public Simulation(Graph graph, int drivers, int customers) throws IOException {
        this.graph = graph;
        this.drivers = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.tick = 0;
        this.driverAmount = drivers;
        this.customerAmount = customers;
        this.rides = 0;
        this.finished = new ArrayList<>();
        this.rideDist = 0;
        this.satisfaction = 5;
        this.dWaitTime = 0;
        this.logger = new Logger();
    }

    /**
     * main function looped - state machine
     * @throws IOException for logger
     */

    public double[] start() throws IOException {
        genObjects();
        while(this.tick < 720) {
            addObjects();
            if (this.tick % 60 == 0 && this.tick != 0) { //every hour
                //System.out.println("HOUR HAS PASSED");
                this.graph.adjustTraffic();
            }
            for (Customer customer : this.customers) {
                if (customer.state.equals("WAITING")) {
                    if (customer.driver != null) {
                    } else {
                        customer.waitTime++;
                        if (checkVehicles()) {
                            findVehicle(customer);
                        }
                    }
                } else if (customer.state.equals("DRIVING")) {
                    customer.driver.move();
                    customer.node = customer.driver.node;
                    if (customer.node.key == customer.destination.key) {
                        this.rideDist = (((this.rideDist * this.rides) + customer.driver.rideDist) / (this.rides+1));
                        this.satisfaction = (((this.satisfaction * this.rides) + customer.satisfaction) / (this.rides+1));
                        this.dWaitTime = (((this.dWaitTime * this.rides) + customer.driver.idleTime) / (this.rides+1));
                        this.logger.log(this.tick + ": customer " + customer + " dropped off at destination by " + customer.driver);
                        customer.driver.customer = null;
                        customer.driver.rideDist = 0;
                        customer.driver.idleTime = 0;
                        customer.driver.state = "IDLE";
                        this.finished.add(customer);
                        this.rides++;
                    }
                }
            }

            for (Vehicle driver : this.drivers) {
                if (driver.state.equals("PICKING-UP")) {
                    driver.customer.waitTime++;
                    driver.move();
                    if (driver.state.equals("AT-DEST")) {
                        driver.rideDist = 0;
                        this.logger.log(this.tick + ": customer " + driver.customer + " picked up by " + driver);
                        driver.customer.satisfaction =  (5 - (0.1 * driver.customer.waitTime));
                        driver.gps = this.graph.fetchPath(driver.node, driver.customer.destination);
                        driver.state = "DRIVING";
                        driver.customer.state = "DRIVING";
                    }
                } else if (driver.state.equals("IDLE")) {
                    driver.idleTime++;
                }
            }

            for (Customer customer : this.finished) {
                if (this.customers.contains(customer)) {
                    this.customers.remove(customer);
                }
            }
            this.finished = new ArrayList<>();
            this.tick++;
        }
        this.logger.log("Simulation finished. Total rides: " + this.rides);
        this.logger.close();
        return new double[]{this.rides, this.satisfaction, this.dWaitTime, this.rideDist, this.graph.connectivity, this.graph.edges, this.graph.weight};
    }

    /**
     * file closest vehicle
     * @param customer customer input
     * @throws IOException
     */

    public void findVehicle(Customer customer) throws IOException {
        int dist = 999999999;
        Vehicle driver = null;
        this.graph.dijkstra(customer.node.key);
        for (Vehicle current : this.drivers) {
            if (current.node.dist < dist && current.state.equals("IDLE")) {
                driver = current;
                dist = current.node.dist;
            }
        }
        if (driver != null) {
            driver.state = "PICKING-UP";
            driver.gps = this.graph.fetchPath(driver.node, customer.node);
            driver.customer = customer;
            customer.driver = driver;
            this.logger.log(this.tick + ": driver " + driver + " going to pick up " + driver.customer);
        }
    }

    /**
     * create initial objects (drivers)
     */

    public void genObjects() {
        for (int i = 0; i < this.driverAmount; i++) {
            this.drivers.add(new Vehicle(this, this.graph));
        }
    }

    /**
     * add customers "randomly" while staying in bounds
     */

    public void addObjects() {
        if (this.customers.size() < this.customerAmount * 0.9) {
            int rand = this.graph.random.nextInt(10) + 1;
            for (int i = 0; i < rand; i++) {
                this.customers.add(new Customer(this, this.graph));
            }
        }
    }

    /**
     * find if any available vehicles
     * @return true if there are
     */

    public boolean checkVehicles() {
        for (Vehicle vehicle : this.drivers) {
            if (vehicle.customer == null) {
                return true;
            }
        }
        return false;
    }

}
