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
     * @param graph graph obj'
     * @param drivers driver amount
     * @param customers customers per node
     */

    public Simulation(Graph graph, int drivers, double customers) {
        this.graph = graph;
        this.drivers = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.tick = 0;
        this.driverAmount = drivers;
        this.customerAmount = (int) (this.graph.nodes.size() * customers);
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
                    //customer.waitTime++;
                    if (customer.driver != null) {
                        break;
                    } else {
                        customer.waitTime++;
                        findVehicle(customer);
                    }
                } else if (customer.state.equals("DRIVING")) {
                    customer.driver.move();
                    customer.node = customer.driver.node;
                    if (customer.node.key == customer.destination.key) {
                        //System.out.println("CUSTOMER DROPPED OFF AT DESTINATION");
                        customer.driver.customer = null;
                        customer.driver.state = "IDLE";
                        for (final Iterator iterator = this.customers.iterator(); iterator.hasNext(); ) {
                            Object cur = iterator.next();
                            if (cur.equals(customer)) {
                                iterator.remove();
                                //System.out.println("removed");
                            }
                        }
                        this.rides++;
                        System.out.println(this.rides);
                        break;
                    }
                }
            }

            for (Vehicle driver : this.drivers) {
                if (driver.state.equals("PICKING-UP")) {
                    driver.customer.waitTime++;
                    driver.move();
                    if (driver.state.equals("AT-DEST")) {
                        //System.out.println("CUSTOMER BEING PICKED UP");
                        //System.out.println(driver.customer.waitTime);
                        driver.customer.satisfaction =  (5 * (1 - (0.0001 * driver.customer.waitTime)));
                        //System.out.println(driver.customer.satisfaction);
                        driver.gps = this.graph.fetchPath(driver.node, driver.customer.destination);
                        driver.state = "DRIVING";
                        driver.customer.state = "DRIVING";
                    }
                } else if (driver.state.equals("IDLE")) {
                    driver.idleTime++;
                }
            }
        }
        System.out.println("TOTAL RIDES: " + this.rides);
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
        if (this.customers.size() < this.customerAmount * 0.9) {
            int rand = this.graph.random.nextInt(5) + 1;
            for (int i = 0; i < rand; i++) {
                this.customers.add(new Customer(this, this.graph));
            }
        }
    }

}
