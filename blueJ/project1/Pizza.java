/**
 * pizza class
 * contains pizza vars
 */

public class Pizza {
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

    public Pizza() {
        this.price = 13;
        this.wait = 6;
        this.cost = 2;
    }

    /**
     * return wait
     * @return pizza cook time
     */

    public int getWait() {
        return this.wait;
    }

}
