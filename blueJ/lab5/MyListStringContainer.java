/**
 * string container class
 * for interracting with the
 * generic linked list and
 * iterator
 */

public class MyListStringContainer {
    public MyLinkedList<String> list;

    public MyListStringContainer() {
        this.list = new MyLinkedList<String>();
    }

    /**
     * calls the lists
     * addEnd() method
     * @param string
     */

    public void addToBack(String string) {
        this.list.addEnd(string);
    }

    /**
     * calls the lists
     * addFirst() method
     * @param string
     */

    public void addToFront(String string) {
        this.list.addFirst(string);
    }

    /**
     * fetches first element that
     * contains the given subString
     * using the iterator
     * @param substring
     * @return
     */

    public int subStringIterator(String substring) {
        int index = 0;
        for(MyLinkedListIterator<String> i = this.list.iterator(); i.hasNext();) {
            if (i.next().value.contains(substring)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * fetches first element that
     * contains the given subString
     * without using the iterator
     * @param substring
     * @return
     */

    public int subString(String substring) {
        Node<String> node = this.list.getHead();
        int index = 0;
        while(node != null) {
            if (node.value.contains(substring)) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    /**
     * calls list's get()
     * method && returns
     * @param index
     * @return
     */

    public String get(int index) {
        return this.list.get(index);
    }

}
