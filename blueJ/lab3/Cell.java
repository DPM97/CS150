/**
 * creates a new cell object with append and toString methods
 */
public class Cell {
    public String val;
    private Cell next;

    /**
     * set value to "" and next to null for new cell
     */
    public Cell() {
        this.val = "";
        this.next = null;
    }

    /**
     * recursive append method
     * @param x
     */
    public void append(String x) {
        if (this.next == null) { //if there is no next element create a new cell as this cells next and set its value to x
            this.next = new Cell();
            this.next.val = x;
        } else { //call the append method from the next cell
            this.next.append(x);
        }
    }

    /**
     * constructs a string of all of the cell's values
     * @return
     */
    public String toString() {
        if (this.next != null) { //if there is a next cell, return current cells value + " " + the return value of the next cells toString
            return this.val + " " + this.next.toString();
        } else { //if there is no next cell return current cells value;
            return this.val;
        }
    }
}
