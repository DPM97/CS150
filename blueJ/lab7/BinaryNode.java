/**
 * binary node class
 * extends comparable
 * @param <E> generic type
 */
public class BinaryNode<E extends Comparable<E>> {
    /** left child node */
    BinaryNode left;
    /** right child node */
    BinaryNode right;
    /** generic data */
    E data;

    /**
     * constructor
     * @param data input data (generic)
     */
    public BinaryNode(E data) {
        this.left = null;
        this.right = null;
        this.data = data;
    }

}

