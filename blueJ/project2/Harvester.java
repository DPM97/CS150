import java.util.Stack;

/**
 * harvester class
 */

public class Harvester extends Dwarf {
    /**
     * game object
     */
    Game game;
    /**
     * gold location stack (from digger)
     */
    Stack goldLoc;

    /**
     * constructor
     * @param map map obj
     * @param game game obj
     */
    public Harvester(Map map, Game game) {
        super(map);
        this.game = game;
        this.goldLoc = null;
    }

    /**
     * move down
     */

    @Override
    public void down() {
        int index = this.map.getBelow(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("GD")) {
                System.out.println("Harvested gold");
                this.map.map.get(index).type = "0";
            } else if (this.map.map.get(index).type.equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                System.out.println("Encountered pit");
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("D")) {
                System.out.println("Encountered Dirt");
            } else {
                System.out.println("Already Dug");
                goDown();
            }
        }
    }

    /**
     * move up
     */

    @Override
    public void up() {
        int index = this.map.getAbove(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("GD")) {
                System.out.println("Harvested gold");
                this.map.map.get(index).type = "0";
            } else if (this.map.map.get(index).type.equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                System.out.println("Encountered pit");
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("D")) {
                System.out.println("Encountered Dirt");
            } else {
                System.out.println("Already Dug");
                goUp();
            }
        }
    }

    /**
     * dig around location
     * @param index location index
     */

    @Override
    void dig(int index) {
        this.location = index;
        right();
        left();
        down();
        up();
    }

    /**
     * go left
     */

    @Override
    public void left() {
        int index = this.map.getLeft(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("GD")) {
                System.out.println("Harvested gold");
                this.map.map.get(index).type = "0";
            } else if (this.map.map.get(index).type.equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                System.out.println("Encountered pit");
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("D")) {
                System.out.println("Encountered Dirt");
            } else {
                System.out.println("Already Dug");
                goLeft();
            }
        }
    }

    /**
     * go right
     */

    @Override
    public void right() {
        int index = this.map.getRight(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("GD")) { //gold discovered
                System.out.println("Harvested gold");
                this.map.map.get(index).type = "0";
                System.out.println("harvested gold");
            } else if (this.map.map.get(index).type.equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                System.out.println("Encountered pit");
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("D")) {
                System.out.println("Encountered Dirt");
            } else {
                System.out.println("Already Dug");
                goRight();
            }
        }
    }

    /**
     * not needed
     * @param index index
     */

    @Override
    void fill(int index) { }

    /**
     * not needed
     */

    @Override
    void move() { }
}
