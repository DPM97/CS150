public class TernaryNode<E extends Comparable<E>> {
    TernaryNode left;
    TernaryNode middle;
    TernaryNode right;
    E data;

    public TernaryNode(E data) {
        this.left = null;
        this.middle = null;
        this.right = null;
        this.data = data;
    }
}
