
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * restaurant class contains main simulation
 */

public class Restaurant {
    
    public final int amountOfCashiers;
    public final int amountOfCooks;
    public int tick;
    private int wage;
    private Employee employeeController;
    private Logger log;

    public Queue<Customer> customerQueue;
    public Queue<Customer> cookQueue;
    public Stack<Cook> cooks;
    public Stack<Cashier> cashiers;

    public String type;
    private int price;
    private int cost;

    private Random random;
    private int foodWait;
    public int business;
    private int shifts;
    private int breakTime;
    public int ordersFilled;
    public float satisfaction;
    private float waitTime;
    private float waitTimeWithCookTime;

    /**
     * constructor
     * @param dir output directory
     * @param type type of sim
     * @param business busyness var
     * @param shifts amount of shifts
     * @param breakTime time in between shifts
     * @param cooks amount of cooks
     * @param cashiers amount of cashiers
     * @throws IOException
     */


    Restaurant(String dir, String type, int business, int shifts, int breakTime, int cooks, int cashiers) throws IOException {
        this.customerQueue = new LinkedList<Customer>();
        this.cookQueue = new LinkedList<Customer>();
        this.employeeController = new Employee();
        this.cooks = new Stack<Cook>();
        this.cashiers = new Stack<Cashier>();
        this.wage = (employeeController.newCook(0).wage * cooks * 12) + (employeeController.newCashier(0).wage * cashiers * 12);
        this.foodWait = 0;
        this.tick = 0;
        this.price = 0;
        this.random = new Random(2012391203);
        this.type = type;
        this.business = 100 - business;
        this.shifts = shifts + 1;
        this.breakTime = breakTime;
        this.amountOfCashiers = cashiers;
        this.amountOfCooks = cooks;
        this.ordersFilled = 0;
        this.satisfaction = 1;
        this.waitTime = 0;
        this.waitTimeWithCookTime = this.foodWait;
        this.log = new Logger(dir, type, business, shifts, breakTime, cooks, cashiers);
    }

    /**
     * starts simulation (controls the clock)
     * @return retsauant object
     * @throws IOException
     */

    Restaurant start() throws IOException {
        FileWriter file = this.log.createCSV();
        getEmployees();
        while(this.tick < 720) {
            simNextTick();
            this.tick++;
        }
        calcCost(); //calculate cost of materials
        this.log.logData(file, this.ordersFilled, this.satisfaction, this.waitTime, this.waitTimeWithCookTime, this.price, this.price - this.wage - this.cost);
        this.log.closeLog();
        return null;
    }


    /**
     * simulates the next tick
     * does everything a restaurant needs to do
     * in a single tick when called
     */

    public void simNextTick() throws IOException {
        accountForLeavingCustomers();
        accountForIncomingCustomers(fetchIncomingCustomers());
        checkCooks();
        checkShiftChange();
    }

    /**
     * simulates the effect
     * that a shift change
     * will have on the restaurant
     */

    public void checkShiftChange() throws IOException {
        for (int i = 1; i < this.shifts; i++) {
            if (this.tick == (720 / this.shifts) * i) { //clear employees
                this.cooks.clear();
                this.cashiers.clear();
                for (int j = 0; j < this.breakTime; j++) { //wait x ticks
                    this.tick++;
                }
                getEmployees(); //get new employees
                for (int j = 0; j < this.breakTime; j++) {
                    accountForIncomingCustomers(fetchIncomingCustomers()); //get incoming customers x times
                }
            }
        }
    }

    /**
     * algorithm to decide
     * how many people come into the
     * restaurant when it is called
     * @return amount of incoming customers
     */

    private int fetchIncomingCustomers() {
        if (this.type == "bagel") {
            if (getWait() + fetchCookTimeNoProgress(this.foodWait) + this.foodWait < 15) {
                for (int i = 0; i < 4; i++) {
                    if (this.random.nextInt(4 * this.business) == i) {
                        return i;
                    }
                }
            }
        } else if (this.type == "hoagie") {
            if (getWait() + fetchCookTimeNoProgress(this.foodWait) + this.foodWait < 15) {
                for (int i = 0; i < 4; i++) {
                    if (this.random.nextInt(4 * this.business) == i) {
                        return i;
                    }
                }
            }
        } else {
            if (getWait() + fetchCookTimeNoProgress(this.foodWait) + this.foodWait < 15) {
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

    public void accountForLeavingCustomers() throws IOException {
        while(this.cookQueue.peek() != null && this.cookQueue.peek().orderFilled <= this.tick) { //remove customers that are supposed to leave at this tick
            this.satisfaction = (float) (((this.satisfaction * this.ordersFilled) + (1 - ((this.tick - this.cookQueue.peek().enterTime) * .01))) / (this.ordersFilled + 1));
            this.waitTimeWithCookTime = (((this.waitTimeWithCookTime * this.ordersFilled) + (this.tick - this.cookQueue.peek().enterTime)) / (this.ordersFilled + 1));
            this.waitTime = ((this.waitTime * this.ordersFilled) + (this.cookQueue.peek().endLine - this.cookQueue.peek().enterTime)) / (this.ordersFilled + 1);
            this.log.log(this.tick, "Customer " + this.cookQueue.peek() + " order filled");
            this.cookQueue.remove();
            this.ordersFilled++;
        }
    }

    /**
     * call newCustomer x (input) times
     * @param num amount of incoming customers that are going to be added
     */

    public void accountForIncomingCustomers(int num) throws IOException {
        for (int i = 0; i < num; i++) {
            newCustomer();
        }
    }

    /**
     * calculates the total cost in
     * food materials / supplies used
     */

    public void calcCost() {
        if (this.type == "bagel") {
            this.cost = bagel().cost * this.ordersFilled;
        } else if (this.type == "hoagie") {
            this.cost = hoagie().cost * this.ordersFilled;
        } else {
            this.cost = pizza().cost * this.ordersFilled;
        }
    }

    /**
     * checks if customers have had their orders taken
     * generates new orderFilled value based off of the
     * wait. Adds customer to cookQueue & removes from
     * customerQueue (line queue)
     */

    public void checkCooks() throws IOException {
        while(this.customerQueue.peek() != null && this.customerQueue.peek().orderFilled == tick) { //remove customers that are supposed to leave at this tick
            Customer customer = this.customerQueue.peek();
            if (this.type == "bagel") {
                customer.cookTime = bagel().wait;
                customer.orderFilled = this.tick + fetchCookTime(bagel().wait);
            } else if (this.type == "hoagie") {
                customer.cookTime = hoagie().wait;
                customer.orderFilled = this.tick + fetchCookTime(hoagie().wait);
            } else {
                customer.cookTime = pizza().wait;
                customer.orderFilled = this.tick + fetchCookTime(pizza().wait);
            }
            this.customerQueue.peek().endLine = this.tick;
            this.log.log(this.tick, "Customer " + this.customerQueue.peek() + " order taken");
            this.cookQueue.add(customer);
            this.log.log(this.tick, "Customer " + this.customerQueue.peek() + " waiting for food");
            this.customerQueue.remove();
        }
    }


    /**
     * get the expected cook time
     * which is also the time the customer
     * receives their food
     * @param foodWait time to cook food
     * @return cook time with cook wait
     */

    private int fetchCookTime(int foodWait) throws IOException {
        accountForLeavingCustomers();
        int total = 0;
        int i = 0;
        for(Customer customer : this.cookQueue) {
            if (i < this.cooks.size()) {
                customer.cookTime--;
                total += customer.cookTime;
            } else {
                total += foodWait;
            }
            i++;
        }
        return (total / this.cooks.size()) + foodWait;
    }

    /**
     * fetch the cook time without changing any intervals
     * good for when a method needs the time, but doesn't
     * want to add a "tick" in terms of the food cooking
     * @param foodWait time to cook food
     * @return cook time with cook wait without changing any variable (adding a tick)
     */

    private int fetchCookTimeNoProgress(int foodWait) {
        int total = 0;
        int i = 0;
        for(Customer customer : this.cookQueue) {
            if (i < this.cooks.size()) {
                total += customer.cookTime;
            } else {
                total += foodWait;
            }
            i++;
        }
        return (total / this.cooks.size()) + foodWait;
    }

    /**
     * add employees
     * to their queues
     * when called
     */

    public void getEmployees() throws IOException {
        for (int i = 0; i < this.amountOfCashiers; i++) {
            this.log.log(this.tick, "Cashier checking in");
            this.cashiers.push(this.employeeController.newCashier(this.tick));
        }
        for (int i = 0; i < this.amountOfCooks; i++) {
            this.log.log(this.tick, "Cook checking in");
            this.cooks.push(this.employeeController.newCook(this.tick));
        }
    }

    /**
     * return new bagel object
     * @return bagel object
     */

    private Bagel bagel() {
        return new Bagel();
    }

    /**
     * return new hoagie object
     * @return hoagie object
     */

    private Hoagie hoagie() {
        return new Hoagie();
    }

    /**
     * return new pizza object
     * @return pizza object
     */

    private Pizza pizza() {
        return new Pizza();
    }


    /**
     * create new customer object
     * and add to the customer queue
     */

    private void newCustomer() throws IOException {
        if (this.type == "bagel") {
            int totalWait = getWait() + fetchCookTimeNoProgress(bagel().wait);
            if (this.tick + totalWait < 720) {
                this.foodWait = bagel().wait;
                this.price += bagel().price;
                Customer customer = new Customer <Bagel>(this.tick, bagel(), getWait());
                this.log.log(this.tick, "New Customer " + customer);
                this.customerQueue.add(customer);
            }
        } else if (this.type == "hoagie") {
            int totalWait = getWait() + fetchCookTimeNoProgress(hoagie().wait);
            if (this.tick + totalWait < 720) {
                this.foodWait = hoagie().wait;
                this.price += hoagie().price;
                Customer customer = new Customer <Hoagie>(this.tick, hoagie(), getWait());
                this.log.log(this.tick, "New Customer " + customer);
                this.customerQueue.add(customer);
            }
        } else {
            int totalWait = getWait() + fetchCookTimeNoProgress(pizza().wait);
            if (this.tick + totalWait < 720) {
                this.foodWait = pizza().wait;
                this.price += pizza().price;
                Customer customer = new Customer <Pizza>(this.tick, pizza(), getWait());
                this.log.log(this.tick, "New Customer " + customer);
                this.customerQueue.add(customer);
            }
        }
    }

    /**
     * algorithm to
     * fetch expected wait time
     * for the restaurants queue
     * @return wait time in line
     */

    public int getWait() {
        int lineWait = 0;
        for (Customer customer : this.customerQueue) {
            if (customer.orderFilled - this.tick < 0) {
                customer.orderFilled += this.breakTime;
            }
            lineWait += (customer.orderFilled - this.tick);
        }
        lineWait = lineWait / this.cashiers.size();
        return lineWait + 1;
    }

}
