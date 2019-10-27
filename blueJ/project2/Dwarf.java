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
    public Stack<Integer> goldLoc;
    /**
     * stack to pit loc
     */
    public Stack<Integer> pitLoc;

    /**
     * constructor
     * @param map map obj
     */

    public Dwarf(Map map) {
        this.map = map;
        this.location = 0;
        this.memory = new Stack<Integer>();
    }

    /**
     * go back 1 tile
     */

    public void goBack() {
        this.memory.pop();
        this.location = this.memory.peek();
    }

    /**
     * return to home (0)
     */

    public void returnHome() {
        while (this.memory.peek() != 0) {
            this.memory.pop();
        }
    }

    /**
     * go left
     */

    public void goLeft() {
        int left = this.map.getLeft(this.location);
        if (left != -1) {
            this.location = left;
            this.memory.push(left);
        }
    }

    /**
     * go right
     */

    public void goRight() {
        int right = this.map.getRight(this.location);
        if (right != -1) {
            this.location = right;
            this.memory.push(right);
        }
    }

    /**
     * go down
     */

    public void goDown() {
        int under = this.map.getBelow(this.location);
        if (under != -1){
            this.location = under;
            this.memory.push(under);
        }
    }

    /**
     * go up
     */

    public void goUp() {
        int above = this.map.getAbove(this.location);
        if (above != -1) {
            this.location = above;
            this.memory.push(above);
        }
    }

    abstract void left();
    abstract void right();
    abstract void down();
    abstract void up();
    abstract void dig(int index);
    abstract void fill(int index);
    abstract void move();

    /**
     * reverse stack
     * @param stack input stack
     * @return reversed stack
     */

    public Stack<Integer> reverse(Stack<Integer> stack) { //reverse memory so that builders/harvesters can get to location
        Stack<Integer> output = new Stack<Integer>();
        for (Integer item : stack) {
            output.push(stack.pop());
        }
        return output;
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
