/**
 * tree interface layout
 * @param <E> generic type
 */

public interface Tree<E> {
    /**
     * adds element to tree
     * @param e element
     * @return boolean (successful)
     */
    public boolean insert(E e);

    /**
     * check if tree contains element
     * @param e element
     * @return true or false (contains or doesn't)
     */
    public boolean contains(E e);

    /**
     * get number of elements at the given depth
     * @param i given depth
     * @return number of elements at depth i
     */
    public int numOfElementsDepth(int i);

    /**
     * find largest element in tree
     * @return largest element
     */
    public E findMax();

    /**
     * find smallest element in tree
      * @return smallest element
     */
    public E findMin();

    /**
     * returns string using preorder traversal
     * @return string of elements
     */
    public String preOrderString();

    /**
     * returns string using postorder traversal
     * @return string of elements
     */
    public String postOrderString();

    /**
     * returns string using inorder traversal
     * @return string of elements
     */
    public String inOrderString();

    /**
     * empty tree
     */
    public void empty();

    /**
     * check if tree is empty
     * @return boolean (empty or not empty)
     */
    public boolean isEmpty();
}
