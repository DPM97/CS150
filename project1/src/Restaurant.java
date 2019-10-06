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
    private int foodWait;

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

        this.foodWait = 0;

        this.tick = 0;
        this.price = 0;
        this.random = new Random(2402320);
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
        checkCooks();
        accountForLeavingCustomers();
        accountForIncomingCustomers(fetchIncomingCustomers());
        checkShiftChange();
    }

    /**
     * simulates the effect
     * that a shift change
     * will have on the restaurant
     */

    private void checkShiftChange() {
        for (int i = 1; i < this.shifts; i++) {
            if (this.tick == (720 / this.shifts) * i) {
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

            if (getWait() + fetchCookTimeNoProgress(this.foodWait) + this.foodWait < 15) {
                for (int i = 0; i < 4; i++) {
                    if (this.random.nextInt(4 * this.business) == i) {
                        return i;
                    }
                }
            }

        } else if (this.type == "hoagie") {
            //wait = hoagie().wait;

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

    private void accountForLeavingCustomers() {
        //System.out.println(this.cookQueue);
        //System.out.println(this.customerQueue);
        while(this.cookQueue.peek() != null && this.cookQueue.peek().orderFilled <= this.tick) { //remove customers that are supposed to leave at this tick
            this.satisfaction = (float) (((this.satisfaction * this.ordersFilled) + (1 - ((this.tick - this.cookQueue.peek().enterTime) * .01))) / (this.ordersFilled + 1));
            this.waitTimeWithCookTime = (((this.waitTimeWithCookTime * this.ordersFilled) + (this.tick - this.cookQueue.peek().enterTime)) / (this.ordersFilled + 1));
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
            this.cookQueue.add(customer);
            this.customerQueue.remove();
        }
    }

    /**
     * get the expected cook time
     * which is also the time the customer
     * receives their food
     * @param foodWait
     * @return
     */

    private int fetchCookTime(int foodWait) {
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
     * @param foodWait
     * @return
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
        if (this.type == "bagel") {
            int totalWait = getWait() + fetchCookTimeNoProgress(bagel().wait);
            if (this.tick + totalWait < 720) {
                this.foodWait = bagel().wait;
                this.price += bagel().price;
                this.customerQueue.add(new Customer <Bagel>(this.tick, bagel(), getWait()));
            }
        } else if (this.type == "hoagie") {
            int totalWait = getWait() + fetchCookTimeNoProgress(hoagie().wait);
            if (this.tick + totalWait < 720) {
                this.foodWait = hoagie().wait;
                this.price += hoagie().price;
                this.customerQueue.add(new Customer <Hoagie>(this.tick, hoagie(), getWait()));
            }
        } else {
            int totalWait = getWait() + fetchCookTimeNoProgress(pizza().wait);
            if (this.tick + totalWait < 720) {
                this.foodWait = pizza().wait;
                this.price += pizza().price;
                this.customerQueue.add(new Customer <Pizza>(this.tick, pizza(), getWait()));
            }
        }
    }

    /**
     * algorithm to
     * fetch expected wait time
     * for the restaurants queue
     * @return
     */

    private int getWait() {

        int lineWait = 0;
        for (Customer customer : this.customerQueue) {
            if (customer.orderFilled - this.tick < 0) {
                customer.orderFilled += this.breakTime;
            }
            lineWait += (customer.orderFilled - this.tick);
        }
        lineWait = lineWait / this.cashiers.size();
        this.waitTime = ((this.waitTime * this.ordersFilled) + (lineWait + 1)) / (this.ordersFilled + 1);
        return lineWait + 1;
    }

}
