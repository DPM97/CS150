/**
 * generic customer class
 * given order object / tick / waitTime
 * @param <E> food object (bagel, pizza, or hoagie)
 */

public class Customer <E> {
    /*** order (food) object */
    public E order;
    /*** tick enter restaurant */
    public int enterTime;
    /*** time order is filled (time they get out of line at first) */
    public int orderFilled;
    /*** cookTime of food */
    public int cookTime;
    /*** time they get out of line (for manipulating sim stats) */
    public int endLine;
    
    /**
     * constructor
     * @param time - enter time tick
     * @param order - customer order (food object)
     * @param waitTime - wait (in line at first)
     */
    
    public Customer(int time, E order, int waitTime) {
        this.enterTime = time;
        this.order = order;
        this.orderFilled = time + waitTime;
        this.endLine = 0;
        this.cookTime = 0;
    }
}
