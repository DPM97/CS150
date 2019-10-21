/**
 * cashier class
 * stores tick that cashier enters restaurant
 */

public class Cashier {
    /*** check in tick */
    int enter;
    /*** avg. wage */
    int wage;
    
    /**
     * constructor 
     * @param tick enter tick
     */
    public Cashier(int tick) {
        this.enter = tick;
        this.wage = 11;
    }
}
