import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * main class
 * creates and runs
 * tests
 */

public class Main {

    /**
     * main function
     * creates the test directories
     * calls test methods given params
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        
        //get num of tests
        int tests = 0;
        BufferedReader reader1 = new BufferedReader(new FileReader("input.txt"));
        while(reader1.readLine() != null) {
            tests++;
        }
        
        
        //create test directories
        new File("tests").mkdir();
        for (int i = 1; i <= tests; i++) {
            new File("tests/test" + Integer.toString(i)).mkdir();
        }
        
        
        //get test data from file && call test methods
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String line = reader.readLine();
        while (line != null) {
            String[] str = line.split(",");
            System.out.println(str[1]);
            test(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4]), Integer.parseInt(str[5]));
            line = reader.readLine();
        }
    }

    /**
     * creates a restaurant simulation for
     * each type of restaurant given params
     * @param testNum test number
     * @param busyness busyness variable (1-99)
     * @param numOfShifts number of shifts per day
     * @param breakLen amount of ticks in between shifts
     * @param cooks amount of cooks
     * @param cashiers amount of cashiers
     * @throws IOException
     */

    public static void test(int testNum, int busyness, int numOfShifts, int breakLen, int cooks, int cashiers) throws IOException {
        new Restaurant("./tests/test" + testNum + "/","bagel", busyness, numOfShifts, breakLen, cooks, cashiers).start();
        new Restaurant("./tests/test" + testNum + "/", "hoagie", busyness, numOfShifts, breakLen, cooks, cashiers).start();
        new Restaurant( "./tests/test" + testNum + "/","pizza", busyness, numOfShifts, breakLen, cooks, cashiers).start();
    }
}
