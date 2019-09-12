/**
 * contains the methods from abstract class "StringListADT" that it extends
 */

public class StringList extends StringListADT {
    private Cell root;

    /**
     * create new stringList in the contructor (root as null)
     */

    public StringList() {
        this.root = null;
    }

    /**
     * appends string x to the list
     * @param x
     */

    public void append(String x) {
        if (this.root == null) { //if root is empty then create a new cell object at root;
            this.root = new Cell();
            this.root.val = x;
        } else {
            this.root.append(x); //re-call append on root --> keep doing until root is null / gets to the end
        }
    }

    /**
     * goes through each element in the list and calls its toString method;
     * @return
     */

    public String toString() {
        if (this.root == null) { //reached the end
            return "";
        } else {
            return this.root.toString(); //call the Cell's toString method
        }
    }

    /**
     * returns true if empty / false if not empty
     * @return
     */

    public boolean isEmpty() {
        if (this.root == null) { //if the root element is null its empty (return true)
            return true;
        } else {
            return false; //not empty
        }
    }
}
