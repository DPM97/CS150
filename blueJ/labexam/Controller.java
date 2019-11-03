import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Controller {
    FoodByType container;
    public Controller() {
        this.container = new FoodByType("Breakfast");;
    }

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        FoodByType container = new FoodByType("Breakfast");
        controller.run();
    }

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("food.txt"));
        String line = reader.readLine();
        while (line != null) {
            String[] lineArr = line.split(" ");
            Food element = new Food();
            element.setMeal(lineArr[0]);
            element.setType(lineArr[1]);
            element.setName(lineArr[2]);
            element.setPrice(Integer.parseInt(lineArr[3]));
            if (!this.container.add(element)) {
                System.out.println("Item " + lineArr[2] + " could not be added");
            }
            line = reader.readLine();
        }
    }
}
