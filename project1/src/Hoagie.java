/*
will contain all hoagie shop data
    - prices
    - wait times
    - calories (maybe)
 */
public class Hoagie {
    int price;
    int wait;
    int customers; //customers per minute
    int employees; //number of employees
    public Hoagie() {
        this.price = 7;
        this.wait = 4;
    }

    public int getWait() {
        return this.wait;
    }

}
