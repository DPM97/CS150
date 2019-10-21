/**
 * hoagie class
 */

public class Hoagie {
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
    
    public Hoagie() {
        this.price = 7;
        this.wait = 5;
        int cost = 2;
    }

    /**
     * return wait
     * @return hoagie cook time
     */

    public int getWait() {
        return this.wait;
    }

}
