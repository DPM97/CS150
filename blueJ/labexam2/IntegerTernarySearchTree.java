public class IntegerTernarySearchTree extends TernarySearchTree<Integer> {

    public int total(TernaryNode node) {
        if (node == null) {
            return 0;
        } else {
            return (int) node.data + total(node.left) + total(node.middle) + total(node.right);
        }
    }


}
