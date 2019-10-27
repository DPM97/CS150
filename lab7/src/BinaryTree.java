import java.util.ArrayList;

/**
 * binary tree class
 * @param <E> generic type
 */

public abstract class BinaryTree<E> implements Tree<E> {
    /** root node */
    BinaryNode root;
    public BinaryTree() {
        this.root = null;
    }

    /**
     * make string from inOrderTraversal output
     * @return inorder traversal string
     */

    @Override
    public String inOrderString() {
        ArrayList<E> arr = new ArrayList<>();
        arr = inOrderTraversal(this.root, arr);
        return arr.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\,", "");
    }

    /**
     * return arraylist (generic) of elements in order
     * @param node input node (root)
     * @param arr input array (blank)
     * @return filled array in order
     */

    public ArrayList<E> inOrderTraversal(BinaryNode node, ArrayList<E> arr) {
        if (node == null) {
            return null;
        }
        inOrderTraversal(node.left, arr);
        arr.add((E) node.data);
        inOrderTraversal(node.right, arr);
        return arr;
    }

    /**
     * make string from postOrderTraversal output
     * @return postorder traversal string
     */

    @Override
    public String postOrderString() {
        ArrayList<E> arr = new ArrayList<>();
        arr = postOrderTraversal(this.root, arr);
        return arr.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\,", "");
    }

    /**
     * return arraylist (generic) of elements using post order traversal
     * @param node input node (root)
     * @param arr input array (blank)
     * @return filled array
     */

    public ArrayList<E> postOrderTraversal(BinaryNode node, ArrayList<E> arr) {
        if (node == null) {
            return null;
        }
        inOrderTraversal(node.left, arr);
        inOrderTraversal(node.right, arr);
        arr.add((E) node.data);
        return arr;
    }

    /**
     * make string from preOrderTraversal output
     * @return preorder traversal string
     */

    @Override
    public String preOrderString() {
        ArrayList<E> arr = new ArrayList<>();
        arr = preOrderTraversal(this.root, arr);
        return arr.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\,", "");
    }

    /**
     * return arraylist (generic) of elements using pre order traversal
     * @param node input node (root)
     * @param arr input array (blank)
     * @return filled array
     */

    public ArrayList<E> preOrderTraversal(BinaryNode node, ArrayList<E> arr) {
        if (node == null) {
            return null;
        }
        arr.add((E) node.data);
        inOrderTraversal(node.left, arr);
        inOrderTraversal(node.right, arr);
        return arr;
    }

    /**
     * empty tree
     */

    @Override
    public void empty() {
        this.root = null;
    }

    /**
     * check if tree is empty
     * @return boolean if empty or not
     */

    @Override
    public boolean isEmpty() {
        if (this.root == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * get amount of elements at given depth
     * @param node start node (root)
     * @param depth (input depth)
     * @param current current depth (start at 0)
     * @return amount of elements at depth "depth"
     */

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
