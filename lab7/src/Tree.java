public interface Tree<E> {
    public boolean insert(E e);
    public boolean contains(E e);
    public int numOfElementsDepth(int i);
    public E findMax();
    public E findMin();
    public String preOrderString();
    public String postOrderString();
    public String inOrderString();
    public void empty();
    public boolean isEmpty();
}
