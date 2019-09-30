import java.util.Random;

/**
 * main experiment controller
 * controls the whole program
 */

public class ExperimentController {
    
    Random random;
    ExperimentController(){ this.random = new Random(102302); }
    public static void main(String[] args) {
        int length = 2000000;
        String subString = "9902";
        ExperimentController controller = new ExperimentController();
        MyListStringContainer container = controller.generateTestString(length);
        controller.timeSubStringIterator(container, subString);
        controller.timeSubString(container, subString);
    }
    
    /**
     * generate random string of 
     * integer's 4 int's long
     * (add 9902 to the end for testing purposes)
     */

    public MyListStringContainer generateTestString(int length) {
        MyListStringContainer container = new MyListStringContainer();
        for (int i = 0; i < length; i++) {
            String string = "";
            for (int j = 0; j < 4; j++) {
                string += this.random.nextInt(8) + 1;
            }
            container.addToBack(string);
        }
        container.addToBack("9902");
        return container;
    }

    /**
     * time to fetch first
     * element with the input substring
     * with the iterator
     * @param container
     * @param subString
     */

    public void timeSubStringIterator(MyListStringContainer container, String subString) {
        long startTime = System.currentTimeMillis();
        int index = container.subStringIterator(subString);
        long stopTime = System.currentTimeMillis();
        if (index != -1) {
            System.out.println("timeSubStringIterator: " + (stopTime - startTime) + "ms : substring " + subString + " found at index " + index);
        } else {
            System.out.println("timeSubStringIterator: " + (stopTime - startTime) + "ms : substring " + subString + " not found");
        }
    }

    /**
     * time to fetch first
     * element with the input substring
     * without the iterator
     * @param container
     * @param subString
     */

    public void timeSubString(MyListStringContainer container, String subString) {
        long startTime = System.currentTimeMillis();
        int index = container.subString(subString);
        long stopTime = System.currentTimeMillis();
        if (index != -1) {
            System.out.println("timeSubString: " + (stopTime - startTime) + "ms : substring " +  subString + " found at index " + index);
        } else {
            System.out.println("timeSubString: " + (stopTime - startTime) + "ms : substring " +  subString + " not found");
        }
    }
}
