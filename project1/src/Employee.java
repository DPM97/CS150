import java.util.Stack;

/*
will contain all employee vars
    - shift data
    - break data
 */
public class Employee {
    public Stack<Cook> cooks;
    public Stack<Cashier> cashiers;
    public Employee() {
        this.cooks = new Stack<>();
        this.cashiers = new Stack<>();
    }

    public Cook newCook(int tick) {
        return new Cook(tick);
    }

    public Cashier newCashier(int tick) {
        return new Cashier(tick);
    }
}
