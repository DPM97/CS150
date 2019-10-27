import java.util.ArrayList;

/**
 * BinarySearchTree class
 * includes BST methods
 * @param <E> generic type
 */

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

    public BinarySearchTree() { }

    /**
     * binary search tree
     * insert method
     * @param key value you want to insert
     * @return boolean
     */

    public boolean insert(E key) {
            BinaryNode node = new BinaryNode(key);
            if (this.root == null) {
                this.root = node;
            } else if (inOrderTraversal(this.root, new ArrayList<E>()).contains(key)) {
                return false;
            } else {
                BinaryNode current = this.root;
                while (current != null) {
                    if (node.data.compareTo(current.data) < 0) {
                        if (current.left == null) {
                            current.left = node;
                            break;
                        } else {
                            current = current.left;
                        }
                    } else {
                        if (current.right == null) {
                            current.right = node;
                            break;
                        } else {
                            current = current.right;
                        }
                    }
                }
            }
            return true;
    }

    /**
     * check if the in order array
     * of elements from tree contains element
     * @param e element
     * @return boolean
     */

    @Override
    public boolean contains(E e) {
        ArrayList<E> arr = new ArrayList<E>();
        arr = this.inOrderTraversal(this.root, arr);
        for (E obj : arr) {
            if (obj.equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * fetch nuber of elements at i depth
     * @param i given depth
     * @return number of elements
     */

    @Override
    public int numOfElementsDepth(int i) {
        return getAtDepth(this.root, i, 0);
    }

    /**
     * find biggest element in tree
     * @return biggest element
     */

    @Override
    public E findMax() {
        String str = this.inOrderString();
        String[] arr = str.split(" ");
        if (arr.length > 0) {
            return (E) arr[arr.length - 1];
        } else {
            return null;
        }
    }

    /**
     * find smallest element in tree
     * @return smallest element
     */

    @Override
    public E findMin() {
        String str = this.inOrderString();
        String[] arr = str.split(" ");
        if (arr.length > 0) {
            return (E) arr[0];
        } else {
            return null;
        }
    }


}
