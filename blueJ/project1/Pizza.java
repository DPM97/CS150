/*
will contain all pizza shop data
    - prices
    - wait times
    - calories (maybe)
 */

/**
 * pizza class
 * contains pizza vars
 */

public class Pizza {
    int price;
    int wait;
    int customers; //customers per minute
    int employees; //number of employees

    public Pizza() {
        this.price = 13;
        this.wait = 6;
    }

    /**
     * @return pizza wait time
     */

    public int getWait() {
        return this.wait;
    }

}
