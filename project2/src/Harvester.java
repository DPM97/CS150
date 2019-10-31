import java.util.Arrays;
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
    int goldLoc;
    Stack<Integer> stack;

    /**
     * constructor
     * @param map map obj
     * @param game game obj
     */
    public Harvester(Map map, Game game) {
        super(map, game);
        this.game = game;
        this.goldLoc = 0;
        this.stack = new Stack<Integer>();
        this.dwarf = null;
    }

    /**
     * move down
     */

    @Override
    public void down() {
    }

    /**
     * move up
     */

    @Override
    public void up() {

    }

    /**
     * dig around location
     * @param dwarf dwarf at gold
     */

    @Override
    void dig(Dwarf dwarf) {
        this.dwarf = dwarf;
        Stack stack = new Stack();
        stack.push(0);
        int index = 0;
        System.out.println("LOCATION: " + this.location);
        this.goldLoc = this.dwarf.location;
        while (index != this.goldLoc && this.stack.empty()) {
            int temp = this.map.getLeft(index);
            if (temp != -1) {
                if (this.map.map.get(temp).dwarfs.containsKey(this.dwarf) && stack.indexOf(temp) == -1) {
                    index = temp;
                    stack.push(index);
                    System.out.println("INDEX: " + index);
                    continue;
                }
            }

            temp = this.map.getRight(index);
            if (temp != -1) {
                System.out.println("TEMP" + temp);
                if (this.map.map.get(temp).dwarfs.containsKey(this.dwarf) && stack.indexOf(temp) == -1) {
                    index = temp;
                    stack.push(index);
                    System.out.println("INDEX: " + index);
                    continue;
                }
            }

            temp = this.map.getBelow(index);
            if (temp != -1) {
                if (this.map.map.get(temp).dwarfs.containsKey(this.dwarf) && stack.indexOf(temp) == -1) {
                    index = temp;
                    stack.push(index);
                    System.out.println("INDEX: " + index);
                    continue;
                }
            }

            temp = this.map.getAbove(index);
            if (temp != -1) {
                if (this.map.map.get(temp).dwarfs.containsKey(this.dwarf) && stack.indexOf(temp) == -1) {
                    index = temp;
                    stack.push(index);
                    System.out.println("INDEX: " + index);
                    continue;
                }
            }
            System.out.println("NOTHING HITTING");
            //either dead end or gold
        }
        System.out.println(Arrays.toString(stack.toArray()));
        if (this.stack.empty()) {
            if (check(index)) {
                System.out.println("GOLD IS HERE");
                this.memory = stack;
                this.stack = stack;
                this.stack = reverse(); //reverse stack
            } else {
                System.out.println("GOLD IS NOT HERE");
            }
        }
    }

    public boolean check(int indexx) {
        int index = this.map.getLeft(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                return true;
            }
        }
        index = this.map.getRight(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                return true;
            }
        }
        index = this.map.getBelow(indexx);
        if (index != -1) {
            System.out.println("TYPE" + this.map.map.get(index).type);
            if (this.map.map.get(index).type.contains("G")) {
                return true;
            }
        }
        index = this.map.getAbove(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                return true;
            }
        }
        return false;
    }

    /**
     * go left
     */

    @Override
    public void left() {

    }

    /**
     * go right
     */

    @Override
    public void right() {

    }

    /**
     * not needed
     * @param dwarf index
     */

    @Override
    void fill(Dwarf dwarf) { }

    /**
     * not needed
     */

    @Override
    void move() {
        if (!this.stack.isEmpty()) {
            int element = this.stack.pop();
            System.out.println("ELEMENT" + element);
            if (element != this.goldLoc) {
                this.location = element;
            } else {
                this.location = element;
                harvest(this.location);
                this.status = "IDLE";
                this.stack = new Stack<>();
                this.dwarf = null;
            }
        }
    }

    public boolean harvest(int indexx) {
        int index = this.map.getLeft(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                this.map.map.get(index).type = "0";
                System.out.println("HARVESTED GOLD");
                this.game.collected++;
                return true;
            }
        }
        index = this.map.getRight(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                this.map.map.get(index).type = "0";
                System.out.println("HARVESTED GOLD");
                this.game.collected++;
                return true;
            }
        }
        index = this.map.getBelow(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                this.map.map.get(index).type = "0";
                System.out.println("HARVESTED GOLD");
                this.game.collected++;
                return true;
            }
        }
        index = this.map.getAbove(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                this.map.map.get(index).type = "0";
                System.out.println("HARVESTED GOLD");
                this.game.collected++;
                return true;
            }
        }
        return false;
    }

}
