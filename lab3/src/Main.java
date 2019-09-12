/**
 * main class
 */

public class Main {

    /**
     * create new experiment controller and run it's methods
     * @param args
     */

    public static void main(String[] args) {
        ExperimentController controller = new ExperimentController();
        controller.timeAddToBack(500, 2030203);
        controller.timeToString(500, 2030203);
    }
}
