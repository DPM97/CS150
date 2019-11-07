import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Stack;

/**
 * builder class
 */
public class Builder extends Dwarf {


    /**
     * pit location
     */
    int pitLoc;

    /**
     * dwarf we are going to
     */
    Dwarf dwarf;


    /**
     * stack to pit that is generated
     */

    Stack<Integer> stack;

    /**
     * constructor
     * @param map map object
     */

    public Builder(Map map, Game game) {
        super(map, game);
        this.pitLoc = 0;
        this.status = "IDLE";
        this.stack = new Stack<Integer>();
        this.dwarf = null;
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
     * fetch a valid path to the pit that the dwarf found using the trees on each tile
     * @param dwarf digger dwarf that found the pit
     * @return true if successful path found
     */

    @Override
    public boolean fill(Dwarf dwarf) {
        this.dwarf = dwarf;
        Stack stack = new Stack();
        stack.push(0);
        int index = 0;
        stack = path(index, stack);
        if (this.stack.empty()) {
            //System.out.println("PIT IS HERE");
            this.memory = stack;
            this.stack = stack;
            this.stack = reverse(); //reverse stack
            this.memory = reverse();
            //System.out.println(Arrays.toString(this.stack.toArray()));
            return true;
        }
        return false;
    }

    /**
     * not needed
     */

    @Override
    public void down() {
    }

    /**
     * not needed
     */

    @Override
    public void up() {
    }

    /**
     * recursively find the path to the pit using
     * the tree's on each tile
     * @param start start index (0 to start)
     * @param stack path stack
     * @return final path to pit
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
     * check if any blocks around builder are pits
     * @param indexx builder location
     * @return true if one is a pit
     */

    public boolean check(int indexx) {
        int index = this.map.getLeft(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P") && !this.map.map.get(index).type.equals("PH")) {
                this.map.map.get(index).type = "PH";
                this.pitLoc = index;
                return true;
            }
        }
        index = this.map.getRight(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P") && !this.map.map.get(index).type.equals("PH")) {
                this.map.map.get(index).type = "PH";
                this.pitLoc = index;
                return true;
            }
        }
        index = this.map.getBelow(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P") && !this.map.map.get(index).type.equals("PH")) {
                this.map.map.get(index).type = "PH";
                this.pitLoc = index;
                return true;
            }
        }
        index = this.map.getAbove(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P") && !this.map.map.get(index).type.equals("PH")) {
                this.map.map.get(index).type = "PH";
                this.pitLoc = index;
                return true;
            }
        }
        return false;
    }

    /**
     * remove all pits around location
     * @param indexx current location of builder
     * @return successful
     * @throws IOException exception for logger
     */

    public boolean harvest(int indexx) throws IOException {

        int index = this.map.getRight(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P")) {
                this.map.map.get(index).type = "0";
                this.game.logger.log(this + " BUILT OVER PIT AT " + index);
                //System.out.println("BUILT OVER PIT");
                return true;
            }
        }

        index = this.map.getBelow(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P")) {
                this.map.map.get(index).type = "0";
                this.game.logger.log(this + " BUILT OVER PIT AT " + index);
                //System.out.println("BUILT OVER PIT");
                return true;
            }
        }

        index = this.map.getLeft(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P")) {
                this.map.map.get(index).type = "0";
                this.game.logger.log(this + " BUILT OVER PIT AT " + index);
                //System.out.println("BUILT OVER PIT");
                return true;
            }
        }

        index = this.map.getAbove(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P")) {
                this.map.map.get(index).type = "0";
                this.game.logger.log(this + " BUILT OVER PIT AT " + index);
                //System.out.println("BUILT OVER PIT");
                return true;
            }
        }
        return false;
    }

    /**
     * not needed
     *
     * @param dwarf dwarf
     */

    @Override
    boolean dig(Dwarf dwarf) {
        return false;
    }

    /**
     * move dwarf back to home base once finished
     * @throws IOException exception for logger
     */

    @Override
    void move() throws IOException {
        //System.out.println(Arrays.toString(this.stack.toArray()));
        if (!this.stack.isEmpty()) {
            this.location = this.stack.pop();
            harvest(this.location);
        } else {
            this.status = "IDLE";
            this.stack = new Stack<>();
            this.dwarf = null;
            //System.out.println(this.status);
        }
    }
}
