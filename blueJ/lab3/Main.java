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
        System.out.println("500, 2030203"); 
        controller.timeAddToBack(500, 2030203);
        controller.timeToString(500, 2030203);
        System.out.println("500, 2030204"); 
        controller.timeAddToBack(500, 2030204);
        controller.timeToString(500, 2030204);
        
        controller = new ExperimentController();
        System.out.println("1000, 2030203"); 
        controller.timeAddToBack(1000, 2030203);
        controller.timeToString(1000, 2030203);
        System.out.println("1000, 2030204"); 
        controller.timeAddToBack(1000, 2030204);
        controller.timeToString(1000, 2030204);
        
        controller = new ExperimentController();
        System.out.println("5000, 2030203"); 
        controller.timeAddToBack(5000, 2030203);
        controller.timeToString(5000, 2030203);
        System.out.println("5000, 2030204"); 
        controller.timeAddToBack(5000, 2030204);
        controller.timeToString(5000, 2030204); 
    }
}
