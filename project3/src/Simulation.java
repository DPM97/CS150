import java.util.ArrayList;
import java.util.Iterator;

public class Simulation {

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
     * constructor
     * @param graph graph obj
     */

    public Simulation(Graph graph, int drivers, int customers) {
        this.graph = graph;
        this.drivers = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.tick = 0;
        this.driverAmount = drivers;
        this.customerAmount = customers;
        this.rides = 0;
    }

    public void start() {
        genObjects();
        while(this.tick < 864000) {
            addObjects();
            if (this.tick % 36000 == 0) { //every hour
                this.graph.adjustTraffic();
            }
            for (Customer customer : this.customers) {
                if (customer.state.equals("WAITING")) {
                    if (customer.driver != null) {
                        break;
                    } else {
                        findVehicle(customer);
                    }
                } else if (customer.state.equals("DRIVING")) {
                    customer.driver.move();
                    customer.node = customer.driver.node;
                    if (customer.node.key == customer.destination.key) {
                        System.out.println("CUSTOMER DROPPED OFF AT DESTINATION");
                        customer.driver.customer = null;
                        customer.driver.state = "IDLE";
                        for (final Iterator iterator = this.customers.iterator(); iterator.hasNext(); ) {
                            iterator.next();
                            if (iterator.next().equals(customer)) {
                                iterator.remove();
                            }
                        }
                        this.rides++;
                    }
                }
            }

            for (Vehicle driver : this.drivers) {
                if (driver.state.equals("PICKING-UP")) {
                    driver.move();
                    if (driver.state.equals("AT-DEST")) {
                        System.out.println("CUSTOMER BEING PICKED UP");
                        System.out.println(driver.node.key);
                        System.out.println(driver.customer.destination.key);
                        driver.gps = this.graph.fetchPath(driver.node, driver.customer.destination);
                        driver.state = "DRIVING";
                        driver.customer.state = "DRIVING";
                    }
                }
            }
        }
    }

    public void findVehicle(Customer customer) {
        int dist = 999999999;
        Vehicle driver = null;
        for (Vehicle vehicle : this.drivers) {
            if (this.graph.getDistance(customer.node.key, vehicle.node.key) < dist && vehicle.customer == null) {
                driver = vehicle;
                dist = this.graph.getDistance(customer.node.key, vehicle.node.key);
            }
        }
        if (driver != null) {
            driver.state = "PICKING-UP";
            driver.gps = graph.fetchPath(driver.node, customer.node);
            driver.customer = customer;
            customer.driver = driver;
            System.out.println("vehicle found...");
        }
    }

    public void genObjects() {
        for (int i = 0; i < this.driverAmount; i++) {
            this.drivers.add(new Vehicle(this, this.graph));
        }
        for (int i = 0; i < this.customerAmount; i++) {
            this.customers.add(new Customer(this, this.graph));
        }
    }

    public void addObjects() {

    }

}
