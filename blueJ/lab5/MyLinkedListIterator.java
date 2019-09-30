import java.util.Iterator;

/**
 * custom generic iterator
 * class for linked list
 * including hasNext() and next()
 * @param <E>
 */

public class MyLinkedListIterator<E> implements Iterator< Node <E> > {

    public Node<E> currentNode;

    public MyLinkedListIterator(Node<E> head) {
        this.currentNode = head;
    }

    /**
     * returns if there
     * is a node
     * @return
     */

    @Override
    public boolean hasNext() {
        return this.currentNode != null;
    }

    /**
     * returns the current node object
     * and makes the current node the
     * the next node in the list
     * @return
     */

    @Override
    public Node<E> next() {
        if (hasNext() == true) {
            Node<E> current = this.currentNode;
            this.currentNode = this.currentNode.next;
            return current;
        } else {
            return null;
        }
    }
}
