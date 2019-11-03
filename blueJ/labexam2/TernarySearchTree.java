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
    public void insert(E val, TernaryNode nodee) {
        TernaryNode node = new TernaryNode(val);
        if (this.root == null) {
            this.root = node;
        } else {
            int outcome = this.root.data.compareTo(val);
            if (outcome > 0) {
                if (nodee.left == null) {
                    nodee.left = node;
                } else {
                    insert(val, nodee.left);
                }
            } else if (outcome < 0) {
                if (nodee.right == null) {
                    nodee.right = node;
                } else {
                    insert(val, nodee.right);
                }
            } else {
                if (nodee.middle == null) {
                    nodee.middle = node;
                } else {
                    insert(val, nodee.right);
                }
            }
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
