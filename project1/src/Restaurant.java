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
    int tick;

    Employee employeeController;
    Logger log;
    Queue<Customer> customerQueue;
    Stack<Cook> cooks;
    Stack<Cashier> cashiers;

    String type;
    int price;
    int employees;
    Random random;

    int business;
    int shifts;
    int breakTime;
    int ordersFilled;
    float satisfaction;

    public Restaurant(String type, int business, int shifts, int breakTime, int employees) {
        this.customerQueue = new LinkedList<Customer>();
        this.employeeController = new Employee();
        this.cooks = new Stack<Cook>();
        this.cashiers = new Stack<Cashier>();
        this.tick = 0;
        this.log = new Logger(this.type);
        this.price = 0;
        this.employees = employees;
        this.random = new Random(2030203);
        this.type = type;

        this.business = 100 - business;
        this.shifts = shifts + 1;
        this.breakTime = breakTime;

        this.ordersFilled = 0;
        this.satisfaction = 1;
    }

    public Restaurant start() {
        getEmployees();
        while(this.tick < 720) {
            simNextTick();
            addTick();
        }
        Logger log = new Logger(this.type);

        return null;
    }

    public void simNextTick() {
        accountForLeavingCustomers();
        accountForIncomingCustomers(fetchIncomingCustomers());
        checkShiftChange();
        //checkEmployees();
        System.out.println(this.customerQueue.toString());
    }

    public void checkShiftChange() {
        for (int i = 1; i < this.shifts; i++) {
            if (this.tick == (720 / this.shifts) * i) {
                System.out.println("Old shift leaving");
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


    public int fetchIncomingCustomers() {
        //int wait = 0;
        //if ((this.tick / business) % business == 0) {
        if (this.type == "bagel") {
            //wait = bagel().wait;
            if (getWait(bagel().wait) < 10) {
                for (int i = 0; i < 4; i++) {
                    if (this.random.nextInt(4 * this.business) == i) {
                        return i;
                    }
                }
            } else {
                this.satisfaction = (this.satisfaction * this.ordersFilled) / (this.ordersFilled + 1);
            }
        } else if (this.type == "hoagie") {
            //wait = hoagie().wait;
            if (getWait(hoagie().wait) < 10) {
                for (int i = 0; i < 4; i++) {
                    if (this.random.nextInt(4 * this.business) == i) {
                        return i;
                    }
                }
            } else {
                this.satisfaction = (this.satisfaction * this.ordersFilled) / (this.ordersFilled + 1);
            }
        } else {
            //wait = pizza().wait;
            if (getWait(pizza().wait) < 10) {
                for (int i = 0; i < 4; i++) {
                    if (this.random.nextInt(4 * this.business) == i) {
                        return i;
                    }
                }
            } else {
                this.satisfaction = (this.satisfaction * this.ordersFilled) / (this.ordersFilled + 1);
            }
        }
        return 0;
    }


    public void accountForLeavingCustomers() {
        while(this.customerQueue.peek() != null && this.customerQueue.peek().orderFilled == tick) { //remove customers that are supposed to leave at this tick
            this.ordersFilled++;
            this.customerQueue.remove();
        }
    }

    public void accountForIncomingCustomers(int num) {
        for (int i = 0; i < num; i++) {
            newCustomer();
        }
    }


    public void getEmployees() {
        for (int i = 0; i < this.employees / 2; i++) {
            this.cooks.push(this.employeeController.newCook(this.tick));
            this.cashiers.push(this.employeeController.newCashier(this.tick));
        }
    }


    public void addTick() {
        if (this.tick < 720) { //12 hours (in minutes) (1 tick = 1 minute)
            this.tick++;
        } else {
            //this.log //saves file
        }
    }

    public Bagel bagel() {
        return new Bagel();
    }

    public Hoagie hoagie() {
        return new Hoagie();
    }

    public Pizza pizza() {
        return new Pizza();
    }


    public void newCustomer() {
        if (this.type == "bagel") { //create new customer with bagel order
            //System.out.println("new customer" + this.tick);
            int wait = getWait(bagel().getWait());
            if (this.tick + wait < 720) {
                this.customerQueue.add(new Customer <Bagel>(this.tick, bagel(), wait));
            }
        } else if (this.type == "hoagie") { //create new customer with hoagie order
            int wait = getWait(hoagie().getWait());
            if (this.tick + wait < 720) {
                this.customerQueue.add(new Customer<Hoagie>(this.tick, hoagie(), wait));
            }
        } else { //create new customer with pizza order
            int wait = getWait(pizza().getWait());
            if (this.tick + wait < 720) {
                this.customerQueue.add(new Customer<Pizza>(this.tick, pizza(), wait));
            }
        }
    }

    public int getWait(int wait) {
        int lineWait = 0;
        for (Customer customer : this.customerQueue) {
            if (customer.orderFilled - this.tick < 0) {
                customer.orderFilled += this.breakTime;
            }
            lineWait += (customer.orderFilled - this.tick);

        }
        lineWait = lineWait / this.cooks.size();
        if (lineWait > 0) {
            this.satisfaction = ((this.satisfaction * this.ordersFilled) + (1 / lineWait)) / (this.ordersFilled + 1);
        } else {
            this.satisfaction = ((this.satisfaction * this.ordersFilled) + 1) / (this.ordersFilled + 1);
        }
        return wait + lineWait;
    }

}
