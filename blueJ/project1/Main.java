import java.io.File;
import java.io.IOException;

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
        int tests = 3;
        //Logger log = new Logger();
        //Controller controller = new Controller();
        new File("tests").mkdir();
        for (int i = 1; i <= tests; i++) {
            new File("tests/test" + Integer.toString(i)).mkdir();
        }


        //      test (testNumber (1 or 2 or 3 all the way to int tests), busyness, numOfShifts (in 1 day),
        //      breakLen (amount of time between shifts with no employees, numberOfCooks, numberOfCashiers);
        test(1, 99, 5, 5, 10, 2);
        test(2, 95, 3, 5, 10, 4);
        test(3, 85, 3, 5, 1, 4);
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
