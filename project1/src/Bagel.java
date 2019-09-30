/*
will contain all bagel shop data
    - prices
    - wait times
    - calories (maybe)
 */
public class Bagel {
    int price;
    int wait;
    int customers; //customers per minute
    int employees; //number of employees
    public Bagel() {
        this.price = 3;
        this.wait = 2;
    }

    public int getWait() {
        return this.wait;
    }

}
