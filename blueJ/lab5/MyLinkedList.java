import java.util.Iterator;

/**
 * generic linked list class
 * @param <E>
 */

public class MyLinkedList <E> implements Iterable< Node<E> > {
    private Node<E> head;
    private Node<E> tail;
    private int length;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    /**
     * creates a new iterator for
     * the linked list when called
     * (starting at the head)
     * @return
     */

    public MyLinkedListIterator<E> iterator() {
        return new MyLinkedListIterator<E>(this.head);
    }

    /**
     * adds the given value to
     * the beginning of the
     * linked list using
     * the iterator hasNext()
     * @param value
     */

    public void addFirst(E value) {
        Node<E> newNode = new Node(value);
        if (!iterator().hasNext()) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            Node<E> currentHead = this.head;
            this.head = newNode;
            this.head.next = currentHead;
        }
        this.length++;
    }

    /**
     * adds given value
     * to the end of the
     * linked list using
     * the iterator hasNext()
     * @param value
     */

    public void addEnd(E value) {
        Node<E> newNode = new Node(value);
        if (!iterator().hasNext()) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = this.tail.next;
        }
        this.length++;
    }

    /**
     * fetches the value of
     * the linked list's item
     * at given index using
     * the iterator hasNext()
     * @param index
     * @return
     */

    public E get(int index) {
        Node<E> curNode = this.head;
        if (index < this.length) { //cant get value from a node that doesn't exist
            for (int i = 0; i < index; i++) {
                curNode = curNode.next;
            }
            return curNode.value;
        }
        return null;
    }

    public Node<E> getHead() {
        return this.head;
    }
}

