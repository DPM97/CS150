public class TernarySearchTree<E extends Comparable<E>> implements TernaryTree<E> {

    TernaryNode root;

    public TernarySearchTree() {
        this.root = null;
    }

    @Override
    public TernaryTree<E> leftSubTree() {
        return null;
    }

    @Override
    public TernaryTree<E> midSubTree() {
        return null;
    }

    @Override
    public TernaryTree<E> rightSubTree() {
        return null;
    }

    @Override
    public void insert(E val, TernaryNode node) {
        if (node == null) { //insert
            node = new TernaryNode(val);
        }
        int outcome = this.root.data.compareTo(val);
        if (outcome < 0) {
            insert(val, node.left);
        } else if (outcome > 0) {
            insert(val, node.right);
        } else {
            insert(val, node.right);
        }
    }

    @Override
    public void insertName(E val, int n) {

    }

    @Override
    public void removeDuplicates(E val) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
