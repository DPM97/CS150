public class Controller {
    public static void main(String[] args) {
        Tree<Integer> tree = new BinarySearchTree<Integer>();
        for(String arg : args) {
            if (tree.insert(Integer.parseInt(arg))) {
                System.out.println("Integer " + arg + " added to the tree");
            } else {
                System.out.println("Integer " + arg + " could not be added to the tree because it is a duplicate");
            }
        }
    }
}
