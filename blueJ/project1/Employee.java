/**
 * employee class
 * handles employee objects
 */

public class Employee {
    
    
    /**
     * return cook object
     * @param tick current time
     * @return cook object
     */

    public Cook newCook(int tick) {
        return new Cook(tick);
    }

    /**
     * return cashier object
     * @param tick current time
     * @return cashier object
     */

    public Cashier newCashier(int tick) {
        return new Cashier(tick);
    }
}
