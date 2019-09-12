import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The test class RandomStringContainerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RandomStringContainerTest
{
    private RandomStringContainer container;
    /**
     * Default constructor for test class RandomStringContainerTest
     */
    public RandomStringContainerTest()
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
        this.container = new RandomStringContainer();
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
    public void testAddToFront() {
        String[] testString = new String[]{"fc", "ab", "vl", "gh"};
        this.container.addToFront(testString[3]);
        this.container.addToFront(testString[2]);
        this.container.addToFront(testString[1]);
        ArrayList<String> result = this.container.addToFront(testString[0]);
        String[] resultArr = new String[result.size()];
        resultArr = result.toArray(resultArr);
        assertArrayEquals(testString, resultArr);
    }
    
    @Test
    public void testAddToBack() {
        String[] testString = new String[]{"fc", "ab", "vl", "gh"};
        this.container.addToBack(testString[0]);
        this.container.addToBack(testString[1]);
        this.container.addToBack(testString[2]);
        ArrayList<String> result = this.container.addToBack(testString[3]);
        String[] resultArr = new String[result.size()];
        resultArr = result.toArray(resultArr);
        assertArrayEquals(testString, resultArr);
    }
    
    @Test
    public void testAddSorted() {
        String[] testString = new String[]{"af", "cz", "dx", "ff", "fz", "jk", "lf", "qr", "tf", "ys"};
        String[] expected = new String[]{"af", "cz", "dx", "ff", "fs", "fz", "jk", "lf", "qr", "tf", "ys"};
        //populate the arrayList
        for (int i = 0; i < testString.length; i++) {
            this.container.addToBack(testString[i]);
        }    
        ArrayList<String> result = this.container.addSorted("fs");
        String[] resultArr = new String[result.size()];
        resultArr = result.toArray(resultArr);
        System.out.println(Arrays.toString(resultArr));
        assertArrayEquals(expected, resultArr);
    }
    
    @Test
    public void testPrependSorted() {
        String[] testString = new String[]{"af", "cz", "dx", "ff", "fz", "jk", "lf", "qr", "tf", "ys"};
        String[] expected = new String[]{"cz", "dx", "ff", "fz", "jk", "lf", "qr", "tf", "xsaf", "ys"};
        //populate the arrayList
        for (int i = 0; i < testString.length; i++) {
            this.container.addToBack(testString[i]);
        }    
        ArrayList<String> result = this.container.prependSorted("xs");
        String[] resultArr = new String[result.size()];
        resultArr = result.toArray(resultArr);
        System.out.println(Arrays.toString(resultArr));
        assertArrayEquals(expected, resultArr);
    }
    
    @Test
    public void testSelectionSort() {
        String[] testString = new String[]{"sd", "gk", "ab", "fk", "vf", "re", "ed", "db", "zx", "wv", "wa"};
        String[] expected = new String[]{"ab", "db", "ed", "fk", "gk", "re", "sd", "vf", "wa", "wv", "zx"};
        //populate the arrayList
        for (int i = 0; i < testString.length; i++) {
            this.container.addToBack(testString[i]);
        }    
        ArrayList<String> result = this.container.selectionSort();
        String[] resultArr = new String[result.size()];
        resultArr = result.toArray(resultArr);
        System.out.println(Arrays.toString(resultArr));
        assertArrayEquals(expected, resultArr);
    }
}
