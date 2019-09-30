/**
 * basic generic node class
 * @param <E>
 */

public class Node <E> {
    public E value;
    public Node<E> next;

    /**
     * takes value
     * @param value
     */

    public Node(E value) {
        this.next = null;
        this.value = value;
    }
}
