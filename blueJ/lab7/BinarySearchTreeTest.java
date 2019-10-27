

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BinarySearchTreeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BinarySearchTreeTest
{
    /**
     * Default constructor for test class BinarySearchTreeTest
     */
    public BinarySearchTreeTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * test inserting element to right child (int)
     */
    
    @Test
    public void testInsertRightInt() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.insert(4);
        tree.insert(7); 
        if (tree.root.data.equals(4) && tree.root.right.data.equals(7)) {
            assertEquals(1,1);
        } else {
            assertEquals(0,1);
        }
    }
    
    
    /**
     * test inserting element to left child (int)
     */
    
    @Test
    public void testInsertLeftInt() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.insert(4);
        tree.insert(3);
        if (tree.root.data.equals(4) && tree.root.left.data.equals(3)) {
            assertEquals(1,1);
        } else {
            assertEquals(0,1);
        }
    }
    
    /**
     * test inserting element to both children (int)
     */
    
    
    @Test
    public void testInsertBothInt() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.insert(4);
        tree.insert(7);
        tree.insert(3);
        tree.insert(5);
        if (tree.root.data.equals(4) && tree.root.left.data.equals(3) && tree.root.right.data.equals(7) && tree.root.right.left.data.equals(5)) {
            assertEquals(1,1);
        } else {
            assertEquals(0,1);
        }
    }
    
    /**
     * test inserting to right child (string)
     */
    
    @Test
    public void testInsertRightStr() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.insert("4");
        tree.insert("7"); 
        if (tree.root.data.equals("4") && tree.root.right.data.equals("7")) {
            assertEquals(1,1);
        } else {
            assertEquals(0,1);
        }
    }
    
    /**
     * test inserting to left child (string)
     */
    
    @Test
    public void testInsertLeftStr() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.insert("4");
        tree.insert("3");
        if (tree.root.data.equals("4") && tree.root.left.data.equals("3")) {
            assertEquals(1,1);
        } else {
            assertEquals(0,1);
        }
    }
    
    /**
     * test inserting to both children (string)
     */
    
    @Test
    public void testInsertBothStr() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.insert("4");
        tree.insert("7");
        tree.insert("3");
        tree.insert("5");
        if (tree.root.data.equals("4") && tree.root.left.data.equals("3") && tree.root.right.data.equals("7") && tree.root.right.left.data.equals("5")) {
            assertEquals(1,1);
        } else {
            assertEquals(0,1);
        }
    }
    
    /**
     * test if tree contains element
     */
    
    @Test
    public void testContainsTrue() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.insert("30");
        tree.insert("4");
        tree.insert("7");
        tree.insert("3");
        tree.insert("5");
        assertEquals(tree.contains("3"), true);
    }
    
    /**
     * test if tree contains element that doesnt exist
     */
    
    @Test
    public void testContainsFalse() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.insert("30");
        tree.insert("4");
        tree.insert("7");
        tree.insert("3");
        tree.insert("5");
        assertEquals(tree.contains("53"), false);
    }
    
    /**
     * test number of elements at depth 2
     */
    
    @Test
    public void testNumOfElementsDepth() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.insert("20");
        tree.insert("3");
        tree.insert("5");
        tree.insert("45");
        tree.insert("25");
        assertEquals(tree.numOfElementsDepth(2), 2);
    }
    
    /**
     * test finding the largest element in the tree
     */
    
    @Test
    public void testfindMax() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.insert(20);
        tree.insert(3);
        tree.insert(5);
        tree.insert(45);
        tree.insert(25);
        assertEquals(45, tree.findMax());
    }
    
    /**
     * test finding the smallest element in the tree
     */
    
    @Test
    public void testfindMin() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.insert(20);
        tree.insert(3);
        tree.insert(5);
        tree.insert(45);
        tree.insert(25);
        assertEquals(3, tree.findMin());
    }
    
    /**
     * test if array is empty (when it is)
     */
    
    @Test
    public void testIsEmptyTrue() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        assertEquals(true, tree.isEmpty());
    }
    
    /**
     * test if array is empty (when it isn't)
     */
    
    @Test
    public void testIsEmptyFalse() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.insert(0);
        assertEquals(false, tree.isEmpty());
    }
    
    /**
     * test emptying the tree
     */
    
    @Test
    public void testEmpty() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.insert(0);
        tree.empty();
        assertEquals(true, tree.isEmpty());
    }
}
