import java.util.ArrayList;

public abstract class BinaryTree<E> implements Tree<E> {
    BinaryNode root;
    public BinaryTree() {
        this.root = null;
    }

    @Override
    public String inOrderString() {
        ArrayList<E> arr = new ArrayList<>();
        arr = inOrderTraversal(this.root, arr);
        return arr.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\,", "");
    }

    public ArrayList<E> inOrderTraversal(BinaryNode node, ArrayList<E> arr) {
        if (node == null) {
            return null;
        }
        inOrderTraversal(node.left, arr);
        arr.add((E) node.data);
        inOrderTraversal(node.right, arr);
        return arr;
    }

    @Override
    public String postOrderString() {
        ArrayList<E> arr = new ArrayList<>();
        arr = postOrderTraversal(this.root, arr);
        return arr.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\,", "");
    }

    public ArrayList<E> postOrderTraversal(BinaryNode node, ArrayList<E> arr) {
        if (node == null) {
            return null;
        }
        inOrderTraversal(node.left, arr);
        inOrderTraversal(node.right, arr);
        arr.add((E) node.data);
        return arr;
    }

    @Override
    public String preOrderString() {
        ArrayList<E> arr = new ArrayList<>();
        arr = preOrderTraversal(this.root, arr);
        return arr.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\,", "");
    }

    public ArrayList<E> preOrderTraversal(BinaryNode node, ArrayList<E> arr) {
        if (node == null) {
            return null;
        }
        arr.add((E) node.data);
        inOrderTraversal(node.left, arr);
        inOrderTraversal(node.right, arr);
        return arr;
    }

    @Override
    public void empty() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        if (this.root == null) {
            return true;
        } else {
            return false;
        }
    }

    public int getAtDepth(BinaryNode node, int depth, int current) {
        if (node == null) {
            return 0;
        }
        if (current == depth) {
            return 1;
        }
        return (getAtDepth(node.left, depth, current + 1) + getAtDepth(node.right, depth, current + 1));
    }
}
