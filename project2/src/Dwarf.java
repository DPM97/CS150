import java.util.Arrays;
import java.util.Stack;

/**
 * dwarf class (implements comparable)
 */

public abstract class Dwarf implements Comparable<Dwarf> {
    /**
     * map object
     */
    public Map map;
    /**
     * current map location
     */
    public int location;
    /**
     * memory stack
     */
    public Stack<Integer> memory;
    /**
     * stack to gold loc
     */
    public int goldLoc;
    /**
     * stack to pit loc
     */
    public int pitLoc;
    /**
     * idle boolean
     */
    public boolean idle;
    /**
     * dwarf obj
     */
    public Dwarf dwarf;
    /**
     * dwarf current status string
     */
    public String status;
    /**
     * main game obj
     */
    public Game game;

    /**
     * constructor
     * @param map map obj
     * @param game
     */

    public Dwarf(Map map, Game game) {
        this.map = map;
        this.location = 0;
        this.memory = new Stack<Integer>();
        this.idle = true;
        this.game = game;
    }

    /**
     * go back 1 tile
     */

    public void goBack() {
        if (this.memory.size() > 1) {
            this.memory.pop();
            this.location = this.memory.peek();
        }
    }


    /**
     * go left
     */

    public void goLeft() {
        int left = this.map.getLeft(this.location);
        if (left != -1) {
            this.location = left;
            this.memory.push(this.location);
            this.map.map.get(this.location).dwarfs.put(this, this.game.tries);
        }
    }

    /**
     * go right
     */

    public void goRight() {
        int right = this.map.getRight(this.location);
        if (right != -1) {
            this.location = right;
            this.memory.push(this.location);
            this.map.map.get(this.location).dwarfs.put(this, this.game.tries);
        }
    }

    /**
     * go down
     */

    public void goDown() {
        int under = this.map.getBelow(this.location);
        if (under != -1){
            this.location = under;
            this.memory.push(this.location);
            this.map.map.get(this.location).dwarfs.put(this, this.game.tries);
        }
    }

    /**
     * go up
     */

    public void goUp() {
        int above = this.map.getAbove(this.location);
        if (above != -1) {
            this.location = above;
            this.memory.push(this.location);
            this.map.map.get(this.location).dwarfs.put(this, this.game.tries);
        }
    }

    /**
     * move left
     */
    abstract void left();

    /**
     * move right
     */
    abstract void right();

    /**
     * move down
     */
    abstract void down();

    /**
     * move up
     */
    abstract void up();

    /**
     * dig tile
     * @param dwarf dwarf obj
     * @return boolean
     */
    abstract boolean dig(Dwarf dwarf);

    /**
     * fill tile
     * @param dwarf dwarf obj
     * @return boolean
     */
    abstract boolean fill(Dwarf dwarf);

    /**
     * move back to base
     */
    abstract void move();

    /**
     * reverse stack
     * @return reversed stack
     */

    public Stack<Integer> reverse() { //reverse memory so that builders/harvesters can get to location
        Stack<Integer> mem = this.memory;
        reverseStack(mem);
        return mem;
    }

    public void reverseStack(Stack<Integer> stack) {
        if (stack.size() == 0) {
            return;
        } else {
            int element = stack.pop();
            reverseStack(stack);
            insertStack(stack , element);
        }
    }

    /**
     * part of reversing the stack
     * @param stack stack which is being created
     * @param element element being inserted to stack
     */

    public void insertStack(Stack<Integer> stack, int element) {
        if (stack.size() == 0) {
            stack.push(element);
        } else {
            int e = stack.pop();
            insertStack(stack , element);
            stack.push(e);
        }
    }

    /**
     * compare dwarf by location
     * @param o dwarf compared to
     * @return compare output
     */

    @Override
    public int compareTo(Dwarf o) {
        return Integer.compare(this.location, o.location);
    }

}
