public interface TernaryTree<E> {

    public TernaryTree<E> leftSubTree();
    public TernaryTree<E> midSubTree();
    public TernaryTree<E> rightSubTree();
    public void insert(E val, TernaryNode node);
    public void insertName(E val, int n);
    public void removeDuplicates(E val);
    public boolean isEmpty();
}
