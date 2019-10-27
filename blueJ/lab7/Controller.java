/**
 * main controller class
 * handles input args
 */

public class Controller {
    /**
     * main method
     * @param args input args
     */
    public static void main(String[] args) {
        Tree<Integer> tree = new BinarySearchTree<Integer>(); //create new tree
        for(String arg : args) {
            if (tree.insert(Integer.parseInt(arg))) { //if insert returns true
                System.out.println(arg + " added to the tree");
            } else { //if insert returns false
                System.out.println(arg + " could not be added to the tree because it is a duplicate");
            }
        }
        System.out.println("Tree contains 1 = " + tree.contains(1));
        System.out.println("Tree contains 2 = " + tree.contains(2));
        System.out.println("Number of elements at depth 2 = " + tree.numOfElementsDepth(2));
        System.out.println("Largest Element = " + tree.findMax());
        System.out.println("Smallest Element = " + tree.findMin());
        System.out.println("Inordertraversal = " + tree.inOrderString());
        System.out.println("Preordertraversal = " + tree.preOrderString());
        System.out.println("Postordertraversal = " + tree.postOrderString());
        tree.empty();
        System.out.println("Empty = " + tree.isEmpty());

    }
}
