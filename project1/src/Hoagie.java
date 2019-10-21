/*
will contain all hoagie shop data
    - prices
    - wait times
    - calories (maybe)
 */

/**
 * hoagie class
 */

public class Hoagie {
    int price;
    int wait;
    int customers; //customers per minute
    int employees; //number of employees
    public Hoagie() {
        this.price = 7;
        this.wait = 5;
    }

    /**
     * @return hoagie wait time
     */

    public int getWait() {
        return this.wait;
    }

}
