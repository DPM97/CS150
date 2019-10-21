import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

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
     * @param args not needed
     * @throws IOException catch fileIO error
     */
    

    public static void main(String[] args) throws IOException {
        
        int numTests = numTests();
                        
        //create test directories
        new File("tests").mkdir();
        for (int i = 1; i <= numTests; i++) {
            new File("tests/test" + Integer.toString(i)).mkdir();
        }
        
        
        //get test data from file && call test methods
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String line = reader.readLine();
        while (line != null) {
            String[] str = line.split(","); //split input 
            test(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4]), Integer.parseInt(str[5]));
            line = reader.readLine();
        }
    }
    
    /**
     * fetch the number of tests
     * in the input file
     * @throws IOException catch fileIO error
     */
    
    public static int numTests() throws IOException {
        int numTests = 0;
        //get num of tests (amount of populated lines in the input file)
        Scanner scanner = new Scanner(new File("input.txt"));
        while(scanner.hasNext()) {
            scanner.next();
            numTests++;
        }
        return numTests;
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
     * @throws IOException catch fileIO error
     */

    public static void test(int testNum, int busyness, int numOfShifts, int breakLen, int cooks, int cashiers) throws IOException {
        //create restaurant objects given params
        new Restaurant("./tests/test" + testNum + "/","bagel", busyness, numOfShifts, breakLen, cooks, cashiers).start();
        new Restaurant("./tests/test" + testNum + "/", "hoagie", busyness, numOfShifts, breakLen, cooks, cashiers).start();
        new Restaurant( "./tests/test" + testNum + "/","pizza", busyness, numOfShifts, breakLen, cooks, cashiers).start();
    }
}
