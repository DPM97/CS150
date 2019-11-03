public class Food {
    String meal;
    String type;
    String name;
    int price;
    public Food() {
        this.meal = "";
        this.type = "";
        this.name = "";
        this.price = -1;
    }

    public String getMeal() {
        return this.meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void display() {
        System.out.println("Name: " + this.name + "    Type: " + this.type + "    Meal Time: " + this.meal + "    Price: $" + this.price);
    }
}
