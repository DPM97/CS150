/**
 * bagel class
 */

public class Bagel {
    
    /*** price to order */
    int price;
    /*** cook time */
    int wait;
    /*** cost to make */
    int cost;
    
    /**
     * constructor
     * @param price cost for customer
     * @param wait how long it takes to cook
     * @param cost cost to make
     */
    
    public Bagel() {
        this.price = 3;
        this.wait = 3;
        this.cost = 1;
    }

    /**
     * return wait
     * @return bagel cook time
     */

    public int getWait() {
        return this.wait;
    }

}
