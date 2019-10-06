import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * restaurant class
 * contains main simulation
 */

public class Restaurant {
    private final int amountOfCashiers;
    private final int amountOfCooks;
    private int tick;

    private Employee employeeController;
    private Logger log;
    private Queue<Customer> customerQueue;
    private Queue<Customer> cookQueue;
    private Stack<Cook> cooks;
    private Stack<Cashier> cashiers;

    public String type;
    private int price;
    private Random random;

    private int business;
    private int shifts;
    private int breakTime;
    private int ordersFilled;
    public float satisfaction;
    private float waitTime;
    private float waitTimeWithCookTime;

    Restaurant(String dir, String type, int business, int shifts, int breakTime, int cooks, int cashiers) {
        this.customerQueue = new LinkedList<Customer>();
        this.cookQueue = new LinkedList<Customer>();
        
        this.employeeController = new Employee();
        this.cooks = new Stack<Cook>();
        this.cashiers = new Stack<Cashier>();

        this.tick = 0;
        this.price = 0;
        this.random = new Random(2030203);
        this.type = type;

        this.business = 100 - business;
        this.shifts = shifts + 1;
        this.breakTime = breakTime;
        
        this.amountOfCashiers = cashiers;
        this.amountOfCooks = cooks;

        this.ordersFilled = 0;
        this.satisfaction = 1;
        this.waitTime = 0;
        this.waitTimeWithCookTime = 0;

        this.log = new Logger(dir, type, business, shifts, breakTime, cooks, cashiers);
    }

    /**
     * starts simulation (controls the clock)
     * @return
     * @throws IOException
     */

    Restaurant start() throws IOException {
        FileWriter file = this.log.createCSV();
        getEmployees();
        while(this.tick < 720) {
            simNextTick();
            addTick();
        }
        this.log.logData(file, this.ordersFilled, this.satisfaction, this.waitTime, this.waitTimeWithCookTime, this.price);
        return null;
    }

    /**
     * simulates the next tick
     * does everything a restaurant needs to do
     * in a single tick when called
     */

    private void simNextTick() {
        accountForLeavingCustomers();
        checkCooks();
        accountForIncomingCustomers(fetchIncomingCustomers());
        checkShiftChange();
        //checkEmployees();
        //System.out.println(this.customerQueue.toString());
    }

    /**
     * simulates the effect
     * that a shift change
     * will have on the restaurant
     */

    private void checkShiftChange() {
        for (int i = 1; i < this.shifts; i++) {
            if (this.tick == (720 / this.shifts) * i) {
                //System.out.println("Old shift leaving");
                this.cooks.clear();
                this.cashiers.clear();
                for (int j = 0; j < this.breakTime; j++) {
                    addTick();
                }
                getEmployees();
                for (int j = 0; j < this.breakTime; j++) {
                    accountForIncomingCustomers(fetchIncomingCustomers());
                }
            }
        }
    }

    /**
     * algorithm to decide
     * how many people come into the
     * restaurant when it is called
     * @return
     */

    private int fetchIncomingCustomers() {
        //int wait = 0;
        //if ((this.tick / business) % business == 0) {
        if (this.type == "bagel") {
            //wait = bagel().wait;
            
            if (getWait(bagel().wait) < 20) {
                for (int i = 0; i < 4; i++) {
                    if (this.random.nextInt(4 * this.business) == i) {
                        return i;
                    }
                }
            }
            
        } else if (this.type == "hoagie") {
            //wait = hoagie().wait;

            if (getWait(hoagie().wait) < 20) {
                for (int i = 0; i < 4; i++) {
                    if (this.random.nextInt(4 * this.business) == i) {
                        return i;
                    }
                }
            }
        } else {
            
            if (getWait(pizza().wait) < 20) {
                for (int i = 0; i < 4; i++) {
                    if (this.random.nextInt(4 * this.business) == i) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * method that checks if
     * customers have gotten their food
     * and can leave the restaurant
     */

    private void accountForLeavingCustomers() {
        if (this.cookQueue.peek() != null) {
            System.out.println(this.cookQueue.peek().orderFilled);
        }
        while(this.cookQueue.peek() != null && this.cookQueue.peek().orderFilled == tick) { //remove customers that are supposed to leave at this tick
            this.ordersFilled++;
            this.cookQueue.remove();
        }
    }

    /**
     * call newCustomer x (input) times
     * @param num
     */

    private void accountForIncomingCustomers(int num) {
        for (int i = 0; i < num; i++) {
            newCustomer();
        }
    }
    
    private void checkCooks() {
        while(this.customerQueue.peek() != null && this.customerQueue.peek().orderFilled == tick) { //remove customers that are supposed to leave at this tick
            Customer customer = this.customerQueue.peek();
            customer.orderFilled = fetchCookTime();
            this.cookQueue.add(customer);
            this.customerQueue.remove();
        }
    }
    
    private int fetchCookTime() {
        int wait = 0;
        for(Customer customer : this.cookQueue) {
            if (customer.orderFilled - this.tick < 0) {
                customer.orderFilled += this.breakTime;
            }
            wait += (this.tick - customer.orderFilled);
        }
        System.out.println("wait " + wait);
        return wait / this.cooks.size();
    }

    /**
     * add employees
     * to their queues
     * when called
     */

    private void getEmployees() {
        for (int i = 0; i < this.amountOfCashiers; i++) {
            this.cashiers.push(this.employeeController.newCashier(this.tick));
        }
        for (int i = 0; i < this.amountOfCooks; i++) {
            this.cooks.push(this.employeeController.newCook(this.tick));
        }
    }

    /**
     * add a tick if tick is in the range (720)
     */


    private void addTick() {
        if (this.tick < 720) { //12 hours (in minutes) (1 tick = 1 minute)
            this.tick++;
        } else {
            //this.log //saves file
        }
    }

    /**
     * return new bagel object
     * @return
     */

    private Bagel bagel() {
        return new Bagel();
    }

    /**
     * return new hoagie object
     * @return
     */

    private Hoagie hoagie() {
        return new Hoagie();
    }

    /**
     * return new pizza object
     * @return
     */

    private Pizza pizza() {
        return new Pizza();
    }


    /**
     * create new customer object
     * and add to the customer queue
     */

    private void newCustomer() {
        if (this.type == "bagel") { //create new customer with bagel order
            getWait(bagel().wait);
        } else if (this.type == "hoagie") { //create new customer with hoagie order
            getWait(hoagie().wait);
        } else { //create new customer with pizza order
            getWait(pizza().wait);
        }
    }

    /**
     * algorithm to
     * fetch expected wait time
     * for customer given waitTime of food object
     * @param wait
     * @return
     */

    private int getWait(int wait) {
        
        int lineWait = 0;
        for (Customer customer : this.customerQueue) {
            if (customer.orderFilled - this.tick < 0) {
                customer.orderFilled += this.breakTime;
            }
            lineWait += (customer.orderFilled - this.tick);
        }
        lineWait = (lineWait / this.cashiers.size()) + 1;
        
        //System.out.println((this.waitTime + lineWait) * this.ordersFilled);
        this.waitTime = ((this.waitTime * this.ordersFilled) + lineWait) / (this.ordersFilled + 1);
        this.waitTimeWithCookTime = ((this.waitTimeWithCookTime * this.ordersFilled) + (lineWait + wait)) / (this.ordersFilled + 1);
        if (lineWait > 0) {
            this.satisfaction = (float) ((this.satisfaction * this.ordersFilled) + (1 - (.02 * lineWait))) / (this.ordersFilled + 1);
        } else {
            this.satisfaction = ((this.satisfaction * this.ordersFilled) + 1) / (this.ordersFilled + 1);
        }        
        
        System.out.println(lineWait);
        
        if (this.type == "bagel") {
            if (this.tick + lineWait + wait < 720) {
                this.price += bagel().price;
                this.customerQueue.add(new Customer <Bagel>(this.tick, bagel(), lineWait));
            }
        } else if (this.type == "hoagie") {
            if (this.tick + lineWait + wait < 720) {
                this.price += hoagie().price;
                this.customerQueue.add(new Customer <Hoagie>(this.tick, hoagie(), lineWait));
            } 
        } else {
            if (this.tick + lineWait + wait < 720) {
                this.price += pizza().price;
                this.customerQueue.add(new Customer <Pizza>(this.tick, pizza(), lineWait));
            } 
        }
        return wait + lineWait;
    }

}
