import java.util.Stack;

public abstract class Dwarf implements Comparable<Dwarf> {
    public Map map;
    public int location;
    public Stack<Integer> memory;
    public Dwarf(Map map) {
        this.map = map;
        this.location = 0;
        this.memory = new Stack<Integer>();
    }

    public Dwarf() {

    }

    public void goBack() {
        this.memory.pop();
        this.location = this.memory.peek();
    }

    public void returnHome() {
        while (this.memory.peek() != 0) {
            this.memory.pop();
        }
    }

    public void goLeft() {
        int left = this.map.getLeft(this.location);
        if (left != -1) {
            this.location = left;
            this.memory.push(left);
        }
    }

    public void goRight() {
        int right = this.map.getRight(this.location);
        if (right != -1) {
            this.location = right;
            this.memory.push(right);
        }
    }

    public void goDown() {
        int under = this.map.getBelow(this.location);
        if (under != -1){
            this.location = under;
            this.memory.push(under);
        }
    }

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

    public Stack<Integer> reverse(Stack<Integer> stack) { //reverse memory so that builders/harvesters can get to location
        Stack<Integer> output = new Stack<Integer>();
        for (Integer item : stack) {
            System.out.println(item);
        }
        return output;
    }

    @Override
    public int compareTo(Dwarf o) {
        return Integer.compare(this.location, o.location);
    }

}
