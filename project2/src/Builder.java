import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Stack;

/**
 * builder class
 */
public class Builder extends Dwarf {
    /**
     * constructor
     * @param map map object
     */
    /**
     * dwarf we are going to
     */
    Dwarf dwarf;

    /**
     * status of object
     */
    String status;

    /**
     * stack to pit that is generated
     */

    Stack<Integer> stack;

    public Builder(Map map, Game game) {
        super(map, game);
        this.memory = new Stack<Integer>();
        this.pitLoc = 0;
        this.status = "IDLE";
        this.stack = new Stack<Integer>();
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
     * fill pit with dirt (try left and right)
     *
     * @param dwarf dwarf
     */

    @Override
    public void fill(Dwarf dwarf) {
        this.dwarf = dwarf;
        Stack stack = new Stack();
        stack.push(0);
        int index = 0;
        System.out.println("LOCATION: " + this.location);
        while (index != this.pitLoc && this.stack.empty()) {
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
                System.out.println("TEMP" + temp);
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
            System.out.println("NOTHING HITTING");
            //either dead end or gold
        }
        System.out.println(Arrays.toString(stack.toArray()));
        if (this.stack.empty()) {
            if (check(index)) {
                System.out.println("PIT IS HERE");
                this.memory = stack;
                this.stack = stack;
                this.stack = reverse(); //reverse stack
            } else {
                System.out.println("PIT IS NOT HERE");
            }
        }
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


    public boolean check(int indexx) {
        int index = this.map.getLeft(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P")) {
                return true;
            }
        }
        index = this.map.getRight(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P")) {
                return true;
            }
        }
        index = this.map.getBelow(indexx);
        if (index != -1) {
            System.out.println("TYPE" + this.map.map.get(index).type);
            if (this.map.map.get(index).type.contains("P")) {
                return true;
            }
        }
        index = this.map.getAbove(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P")) {
                return true;
            }
        }
        return false;
    }

    public boolean harvest(int indexx) {
        int index = this.map.getLeft(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P")) {
                this.map.map.get(index).type = "0";
                System.out.println("BUILT OVER PIT");
                return true;
            }
        }
        index = this.map.getRight(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P")) {
                this.map.map.get(index).type = "0";
                System.out.println("BUILT OVER PIT");
                return true;
            }
        }
        index = this.map.getBelow(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P")) {
                this.map.map.get(index).type = "0";
                System.out.println("BUILT OVER PIT");
                return true;
            }
        }
        index = this.map.getAbove(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("P")) {
                this.map.map.get(index).type = "0";
                System.out.println("BUILT OVER PIT");
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
    void dig(Dwarf dwarf) {
    }

    /**
     * not needed
     */

    @Override
    void move() {
        if (!this.stack.isEmpty()) {
            int element = this.stack.pop();
            System.out.println("ELEMENT" + element);
            if (element != this.pitLoc) {
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

}
