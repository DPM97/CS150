/*
controls all customer vars
    - place in queue
    - time waiting for food
    - price of said food
 */

/**
 * generic customer class
 * given order object / tick / waitTime
 * @param <E> food object (bagel, pizza, or hoagie)
 */

public class Customer <E> {
    public E order;
    public int spot;
    public int enterTime;
    public int satisfaction;
    public int orderFilled;
    public int cookTime;
    public int endLine;
    public Customer(int time, E order, int waitTime) {
        this.enterTime = time;
        this.satisfaction = 0;
        this.order = order;
        this.orderFilled = time + waitTime;
        this.endLine = 0;
        this.cookTime = 0;
    }
}
