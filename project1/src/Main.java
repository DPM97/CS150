import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int tests = 3;
        //Logger log = new Logger();
        //Controller controller = new Controller();
        new File("tests").mkdir();
        for (int i = 1; i <= tests; i++) {
            new File("tests/test" + Integer.toString(i)).mkdir();
        }


        test(1, 99, 5, 5, 6, 2);
        test(2, 95, 3, 5, 4, 4);
        test(3, 85, 3, 10, 2, 2);
    }

    public static void test(int testNum, int busyness, int numOfShifts, int breakLen, int cooks, int cashiers) throws IOException {
        new Restaurant("./tests/test" + testNum + "/","bagel", busyness, numOfShifts, breakLen, cooks, cashiers).start();
        new Restaurant("./tests/test" + testNum + "/", "hoagie", busyness, numOfShifts, breakLen, cooks, cashiers).start();
        new Restaurant( "./tests/test" + testNum + "/","pizza", busyness, numOfShifts, breakLen, cooks, cashiers).start();
    }
}
