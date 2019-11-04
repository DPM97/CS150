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
        stack = path(index, stack);
        if (this.stack.empty()) {
            System.out.println("GOLD IS HERE");
            this.memory = stack;
            this.stack = stack;
            this.stack = reverse(); //reverse stack
            System.out.println(Arrays.toString(this.stack.toArray()));
            return true;
        }
        return false;
    }

    /**
     * recursively find the most efficient path to the gold node
     * via the tree's on each tile that the dwarf has been to
     * @param start starting location (0 at first)
     * @param stack stack that will become the path
     * @return final path to gold node
     */

    public Stack<Integer> path(int start, Stack<Integer> stack) {
        int index = start;
        int temp = this.map.getLeft(index);
        if (temp != -1) {
            if (this.map.map.get(temp).dwarfs.containsKey(this.dwarf) && stack.indexOf(temp) == -1) {
                index = temp;
                stack.push(index);
                if (check(index)) {
                    return stack;
                } else {
                    stack = path(index, stack);
                }
            }
        }

        temp = this.map.getRight(index);
        if (temp != -1) {
            if (this.map.map.get(temp).dwarfs.containsKey(this.dwarf) && stack.indexOf(temp) == -1) {
                index = temp;
                stack.push(index);
                if (check(index)) {
                    return stack;
                } else {
                    stack = path(index, stack);
                }
            }
        }

        temp = this.map.getBelow(index);
        if (temp != -1) {
            if (this.map.map.get(temp).dwarfs.containsKey(this.dwarf) && stack.indexOf(temp) == -1) {
                index = temp;
                stack.push(index);
                if (check(index)) {
                    return stack;
                } else {
                    stack = path(index, stack);
                }
            }
        }

        temp = this.map.getAbove(index);
        if (temp != -1) {
            if (this.map.map.get(temp).dwarfs.containsKey(this.dwarf) && stack.indexOf(temp) == -1) {
                index = temp;
                stack.push(index);
                if (check(index)) {
                    return stack;
                } else {
                    stack = path(index, stack);
                }
            }
        }
        return stack;
    }

    /**
     * check if any gold is around a location on the map
     * @param indexx location given
     * @return true if found
     */

    public boolean check(int indexx) {
        int index = this.map.getLeft(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G") && !this.map.map.get(index).type.equals("GH")) {
                this.map.map.get(index).type = "GH";
                System.out.println(index);
                this.goldLoc = index;
                return true;
            }
        }
        index = this.map.getRight(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G") && !this.map.map.get(index).type.equals("GH")) {
                this.map.map.get(index).type = "GH";
                System.out.println(index);
                this.goldLoc = index;
                return true;
            }
        }
        index = this.map.getBelow(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G") && !this.map.map.get(index).type.equals("GH")) {
                this.map.map.get(index).type = "GH";
                System.out.println(index);
                this.goldLoc = index;
                return true;
            }
        }
        index = this.map.getAbove(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G") && !this.map.map.get(index).type.equals("GH")) {
                this.map.map.get(index).type = "GH";
                System.out.println(index);
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
     * move dwarf back to home base once finished
     */

    @Override
    void move() {
        if (!this.stack.isEmpty()) {
            this.location = this.stack.pop();
            harvest(this.location);
        } else {
            this.status = "IDLE";
            this.stack = new Stack<>();
            this.dwarf = null;
        }
    }

    /**
     * harvest gold around location
     * @param indexx input location
     * @return true if harvested
     */

    public boolean harvest(int indexx) {
        int index = this.map.getRight(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                this.map.map.get(index).type = "0";
                //System.out.println("HARVESTED GOLD");
                this.game.collected++;
                return true;
            }
        }
        index = this.map.getBelow(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                this.map.map.get(index).type = "0";
                //System.out.println("HARVESTED GOLD");
                this.game.collected++;
                return true;
            }
        }
        index = this.map.getLeft(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                this.map.map.get(index).type = "0";
                //System.out.println("HARVESTED GOLD");
                this.game.collected++;
                return true;
            }
        }
        index = this.map.getAbove(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                this.map.map.get(index).type = "0";
                //System.out.println("HARVESTED GOLD");
                this.game.collected++;
                return true;
            }
        }
        return false;
    }

}
