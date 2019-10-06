import java.util.Stack;

/*
will contain all employee vars
    - shift data
    - break data
 */

/**
 * employee class
 * handles employee objects
 */

public class Employee {
    public Stack<Cook> cooks;
    public Stack<Cashier> cashiers;
    public Employee() {
        this.cooks = new Stack<>();
        this.cashiers = new Stack<>();
    }

    /**
     * return cook object
     * @param tick
     * @return
     */

    public Cook newCook(int tick) {
        return new Cook(tick);
    }

    /**
     * return cashier object
     * @param tick
     * @return
     */

    public Cashier newCashier(int tick) {
        return new Cashier(tick);
    }
}
