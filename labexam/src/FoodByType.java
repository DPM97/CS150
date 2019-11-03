import java.util.LinkedList;

public class FoodByType {
    LinkedList<Food> list;
    String type;
    public FoodByType(String meal) {
        this.type = meal;
        this.list = new LinkedList<Food>();
    }

    public boolean add(Food obj) {
        if (obj.getMeal().equals(this.type)) {
            this.list.add(obj);
            return true;
        } else {
            System.out.println("Object " + obj.getName() + " could not be added...");
            return false;
        }
    }

    /**
     * method to display which items
     * fall into the parameters given
     * @param type type of food wanted
     * @param budget budget for food wanted
     */

    public void display(String type, int budget) {
        for (Food obj : this.list) {
            if (obj.getPrice() < budget && obj.getType().equals(type)) {
                obj.display();
            }
        }
    }

}
