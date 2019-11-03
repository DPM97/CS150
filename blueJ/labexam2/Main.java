public class Main
{
    public static void main(String[] args) {
        System.out.println("Compiled - unit tests are in the IntegerTernarySearchTreeTest class");
        IntegerTernarySearchTree tree = new IntegerTernarySearchTree(); //main method so it compiles
        tree.insert(1, tree.root);
        System.out.println("Test total = " + tree.total(tree.root));
    }
}
