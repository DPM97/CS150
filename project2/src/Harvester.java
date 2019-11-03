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
     *
     * @param map  map obj
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
     *
     * @param dwarf dwarf at gold
     */

    @Override
    boolean dig(Dwarf dwarf) {
        this.dwarf = dwarf;
        Stack stack = new Stack();
        stack.push(0);
        int index = 0;
        while (!check(index) && this.stack.empty()) {
            int temp = this.map.getLeft(index);
            if (temp != -1) {
                if (this.map.map.get(temp).dwarfs.containsKey(this.dwarf) && stack.indexOf(temp) == -1) {
                    index = temp;
                    stack.push(index);
                    continue;
                }
            }

            temp = this.map.getRight(index);
            if (temp != -1) {
                if (this.map.map.get(temp).dwarfs.containsKey(this.dwarf) && stack.indexOf(temp) == -1) {
                    index = temp;
                    stack.push(index);
                    continue;
                }
            }

            temp = this.map.getBelow(index);
            if (temp != -1) {
                if (this.map.map.get(temp).dwarfs.containsKey(this.dwarf) && stack.indexOf(temp) == -1) {
                    index = temp;
                    stack.push(index);
                    continue;
                }
            }

            temp = this.map.getAbove(index);
            if (temp != -1) {
                if (this.map.map.get(temp).dwarfs.containsKey(this.dwarf) && stack.indexOf(temp) == -1) {
                    index = temp;
                    stack.push(index);
                    continue;
                }
            }

            return false;
            //System.out.println("NOTHING HITTING");
            //either dead end or gold
        }
        /*
        int test = this.map.getLeft((int) stack.toArray()[stack.size() - 1]);
        System.out.println(this.map.map.get(test).type);
        test = this.map.getRight((int) stack.toArray()[stack.size() - 1]);
        System.out.println(this.map.map.get(test).type);
        test = this.map.getAbove((int) stack.toArray()[stack.size() - 1]);
        System.out.println(this.map.map.get(test).type);
        test = this.map.getBelow((int) stack.toArray()[stack.size() - 1]);
        System.out.println(this.map.map.get(test).type);
        */
        System.out.println(Arrays.toString(stack.toArray()));
        if (this.stack.empty()) {
            System.out.println("GOLD IS HERE");
            this.memory = stack;
            this.stack = stack;
            this.stack = reverse(); //reverse stack
            return true;
        }
        return false;
    }

    public boolean check(int indexx) {
        int index = this.map.getLeft(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G") && !this.map.map.get(index).type.equals("GH")) {
                this.map.map.get(index).type = "GH";
                this.goldLoc = index;
                return true;
            }
        }
        index = this.map.getRight(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G") && !this.map.map.get(index).type.equals("GH")) {
                this.map.map.get(index).type = "GH";
                this.goldLoc = index;
                return true;
            }
        }
        index = this.map.getBelow(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G") && !this.map.map.get(index).type.equals("GH")) {
                this.map.map.get(index).type = "GH";
                this.goldLoc = index;
                return true;
            }
        }
        index = this.map.getAbove(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G") && !this.map.map.get(index).type.equals("GH")) {
                this.map.map.get(index).type = "GH";
                this.goldLoc = index;
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
     *
     * @param dwarf index
     */

    @Override
    boolean fill(Dwarf dwarf) {
        return false;
    }

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
                this.status = "IDLE";
                this.stack = new Stack<>();
                this.dwarf = null;
            }
            harvest(this.location);
        }
    }

    public boolean harvest(int indexx) {
        int index = this.map.getRight(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("GH")) {
                this.map.map.get(index).type = "0";
                System.out.println("HARVESTED GOLD");
                this.game.collected++;
                return true;
            }
        }
        index = this.map.getBelow(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("GH")) {
                this.map.map.get(index).type = "0";
                System.out.println("HARVESTED GOLD");
                this.game.collected++;
                return true;
            }
        }
        index = this.map.getLeft(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("GH")) {
                this.map.map.get(index).type = "0";
                System.out.println("HARVESTED GOLD");
                this.game.collected++;
                return true;
            }
        }
        index = this.map.getAbove(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("GH")) {
                this.map.map.get(index).type = "0";
                System.out.println("HARVESTED GOLD");
                this.game.collected++;
                return true;
            }
        }
        return false;
    }

}
