

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class IntegerTernarySearchTreeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class IntegerTernarySearchTreeTest
{
    /**
     * Default constructor for test class IntegerTernarySearchTreeTest
     */
    public IntegerTernarySearchTreeTest()
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
    
    @Test
    public void testTotalOne() {
        IntegerTernarySearchTree tree = new IntegerTernarySearchTree();
        tree.insert(2, tree.root);
        tree.insert(1, tree.root);
        tree.insert(5, tree.root);
        assertEquals(8, tree.total(tree.root));
    }
    
    @Test
    public void testTotalTwo() {
        IntegerTernarySearchTree tree = new IntegerTernarySearchTree();
        tree.insert(4, tree.root);
        tree.insert(10, tree.root);
        tree.insert(1, tree.root);
        tree.insert(10, tree.root);
        tree.insert(30, tree.root);
        assertEquals(55, tree.total(tree.root));
    }
    
    @Test
    public void testTotalThree() {
        IntegerTernarySearchTree tree = new IntegerTernarySearchTree();
        tree.insert(20, tree.root);
        tree.insert(1, tree.root);
        tree.insert(1, tree.root);
        tree.insert(1, tree.root);
        tree.insert(74, tree.root);
        assertEquals(97, tree.total(tree.root));
    }
    
    @Test
    public void testTotalFour() {
        IntegerTernarySearchTree tree = new IntegerTernarySearchTree();
        tree.insert(0, tree.root);
        tree.insert(1, tree.root);
        tree.insert(1, tree.root);
        tree.insert(1, tree.root);
        tree.insert(74, tree.root);
        assertEquals(77, tree.total(tree.root));
    }
}
