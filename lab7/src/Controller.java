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
        tree.insert(20);
        tree.insert(3);
        tree.insert(5);
        tree.insert(45);
        tree.insert(25);
        System.out.println(tree.findMax());
        System.out.println(tree.findMin());

        for(String arg : args) {
            if (tree.insert(Integer.parseInt(arg))) { //if insert returns true
                System.out.println("Integer " + arg + " added to the tree");
            } else { //if insert returns false
                System.out.println("Integer " + arg + " could not be added to the tree because it is a duplicate");
            }
        }
    }
}
