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
        System.out.println("HARVESTING AROUND " + index);
        down();
        left();
        right();
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
                this.map.map.get(index).type = "0";
                System.out.println("harvested gold");
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
