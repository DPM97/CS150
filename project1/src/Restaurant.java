import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/*
will contain all of the main aspects of a restaurant
basically the hub for employees, customers, orders, queue (maybe might put this in its own queue class)
    - employee counter
    - customer counter
    - order counter
    - price counter
 */
public class Restaurant extends Controller {
    private final int amountOfCashiers;
    private final int amountOfCooks;
    private int tick;

    private Employee employeeController;
    private Logger log;
    private Queue<Customer> customerQueue;
    private Stack<Cook> cooks;
    private Stack<Cashier> cashiers;

    public String type;
    private int price;
    private int employees;
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

    Restaurant start() throws IOException {
        FileWriter file = this.log.createCSV();
        getEmployees();
        while(this.tick < 720) {
            simNextTick();
            addTick();
        }
        //System.out.println("Orders Filled: " + this.ordersFilled);
        //System.out.println("Avg. Satisfaction: " + this.satisfaction);
        //System.out.println("Customers Deferred: " + this.customersDeferred);
        //System.out.println("Avg. WaitTime: " + this.waitTime);
        this.log.logData(file, this.ordersFilled, this.satisfaction, this.waitTime, this.waitTimeWithCookTime, this.price);
        return null;
    }

    private void simNextTick() {
        accountForLeavingCustomers();
        accountForIncomingCustomers(fetchIncomingCustomers());
        checkShiftChange();
        //checkEmployees();
        //System.out.println(this.customerQueue.toString());
    }

    private void checkShiftChange() {
        for (int i = 1; i < this.shifts; i++) {
            if (this.tick == (720 / this.shifts) * i) {
                //System.out.println("Old shift leaving");
                this.cooks.clear();
                this.cashiers.clear();
                for (int j = 0; j < this.breakTime; j++) {
                    accountForLeavingCustomers();
                    addTick();
                }
                getEmployees();
                for (int j = 0; j < this.breakTime; j++) {
                    accountForIncomingCustomers(fetchIncomingCustomers());
                }
            }
        }
    }


    private int fetchIncomingCustomers() {
        //int wait = 0;
        //if ((this.tick / business) % business == 0) {
        if (this.type == "bagel") {
            //wait = bagel().wait;
            if (getWait(bagel().wait) < 15) {
                for (int i = 0; i < 4; i++) {
                    if (this.random.nextInt(4 * this.business) == i) {
                        return i;
                    }
                }
            }
        } else if (this.type == "hoagie") {
            //wait = hoagie().wait;
            if (getWait(hoagie().wait) < 15) {
                for (int i = 0; i < 4; i++) {
                    if (this.random.nextInt(4 * this.business) == i) {
                        return i;
                    }
                }
            }
        } else {
            //wait = pizza().wait;
            if (getWait(pizza().wait) < 15) {
                for (int i = 0; i < 4; i++) {
                    if (this.random.nextInt(4 * this.business) == i) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }

    private void accountForLeavingCustomers() {
        while(this.customerQueue.peek() != null && this.customerQueue.peek().orderFilled == tick) { //remove customers that are supposed to leave at this tick
            this.ordersFilled++;
            this.customerQueue.remove();
        }
    }

    private void accountForIncomingCustomers(int num) {
        for (int i = 0; i < num; i++) {
            newCustomer();
        }
    }

    private void getEmployees() {
        for (int i = 0; i < this.amountOfCashiers; i++) {
            this.cashiers.push(this.employeeController.newCashier(this.tick));
        }
        for (int i = 0; i < this.amountOfCooks; i++) {
            this.cooks.push(this.employeeController.newCook(this.tick));
        }
    }


    private void addTick() {
        if (this.tick < 720) { //12 hours (in minutes) (1 tick = 1 minute)
            this.tick++;
        } else {
            //this.log //saves file
        }
    }

    private Bagel bagel() {
        return new Bagel();
    }

    private Hoagie hoagie() {
        return new Hoagie();
    }

    private Pizza pizza() {
        return new Pizza();
    }


    private void newCustomer() {
        if (this.type == "bagel") { //create new customer with bagel order
            //System.out.println("new customer" + this.tick);
            int wait = getWait(bagel().getWait());
            if (this.tick + wait < 720) {
                this.price += bagel().price;
                this.customerQueue.add(new Customer <Bagel>(this.tick, bagel(), wait));
            }
        } else if (this.type == "hoagie") { //create new customer with hoagie order
            int wait = getWait(hoagie().getWait());
            if (this.tick + wait < 720) {
                this.price += hoagie().price;
                this.customerQueue.add(new Customer<Hoagie>(this.tick, hoagie(), wait));
            }
        } else { //create new customer with pizza order
            int wait = getWait(pizza().getWait());
            if (this.tick + wait < 720) {
                this.price += pizza().price;
                this.customerQueue.add(new Customer<Pizza>(this.tick, pizza(), wait));
            }
        }
    }

    private int getWait(int wait) {
        int lineWait = 0;
        for (Customer customer : this.customerQueue) {
            if (customer.orderFilled - this.tick < 0) {
                customer.orderFilled += this.breakTime;
            }
            lineWait += (customer.orderFilled - this.tick);

        }
        lineWait = lineWait / this.cooks.size();
        //System.out.println((this.waitTime + lineWait) * this.ordersFilled);
        this.waitTime = ((this.waitTime * this.ordersFilled) + lineWait) / (this.ordersFilled + 1);
        this.waitTimeWithCookTime = ((this.waitTimeWithCookTime * this.ordersFilled) + (lineWait + wait)) / (this.ordersFilled + 1);
        if (lineWait > 0) {
            this.satisfaction = (float) ((this.satisfaction * this.ordersFilled) + (1 - (.02 * lineWait))) / (this.ordersFilled + 1);
        } else {
            this.satisfaction = ((this.satisfaction * this.ordersFilled) + 1) / (this.ordersFilled + 1);
        }
        return wait + lineWait;
    }

}
