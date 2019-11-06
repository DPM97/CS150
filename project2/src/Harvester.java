import java.io.IOException;
import java.util.ArrayList;
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
     * gold location
     */
    int goldLoc;
    public Stack<Integer> stack;

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
     * @throws IOException exception for logger
     */

    @Override
    public void down() throws IOException {
        int index = this.map.getBelow(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("0")) {
                //System.out.println("Already Dug" + index);
                goDown();
                harvest(this.location);
            }
        }
    }

    /**
     * move up
     * @throws IOException exception for logger
     */

    @Override
    public void up() throws IOException {
        int index = this.map.getAbove(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("0")) {
                //System.out.println("Already Dug" + index);
                goUp();
                harvest(this.location);
            }
        }
    }

    /**
     * fetch optimal path to gold that dwarf found
     * using the tree's on each tile
     * @param dwarf dwarf at gold
     */

    @Override
    public boolean dig(Dwarf dwarf) {
        this.dwarf = dwarf;
        Stack stack = new Stack();
        stack.push(0);
        int index = 0;
        stack = path(index, stack);

        if (this.stack.empty()) {
            //System.out.println("GOLD IS HERE");
            this.memory = stack;
            this.stack = stack;
            this.stack = reverse(); //reverse stack
            this.memory = reverse();
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
            if (this.map.map.get(index).type.contains("G") && this.map.map.get(index).type != "GH") {
                this.map.map.get(index).type = "GH";
                this.goldLoc = index;
                return true;
            }
        }
        index = this.map.getRight(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G") && this.map.map.get(index).type != "GH") {
                this.map.map.get(index).type = "GH";
                this.goldLoc = index;
                return true;
            }
        }
        index = this.map.getBelow(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G") && this.map.map.get(index).type != "GH") {
                this.map.map.get(index).type = "GH";
                this.goldLoc = index;
                return true;
            }
        }
        index = this.map.getAbove(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G") && this.map.map.get(index).type != "GH") {
                this.map.map.get(index).type = "GH";
                this.goldLoc = index;
                return true;
            }
        }
        return false;
    }

    /**
     * go left
     * @throws IOException
     */

    @Override
    public void left() throws IOException {
        int index = this.map.getLeft(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("0")) {
                //System.out.println("Already Dug" + index);
                goLeft();
                harvest(this.location);
            }
        }
    }

    /**
     * move right
     * @throws IOException exception for logger
     */

    @Override
    public void right() throws IOException {
        int index = this.map.getRight(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("0")) {
                //System.out.println("Already Dug" + index);
                goRight();
                harvest(this.location);
            }
        }
    }

    /**
     * fill all gaps
     * @param dwarf
     */

    @Override
    public boolean fill(Dwarf dwarf) throws IOException { //assuming we can see gold now that dirt is gone
        int g = 0;
        for (int i = 0; i < this.map.totalElements; i++) {
            if (this.map.map.get(i).type == "GD") {
                g = i;
            }
        }

        int myCol = this.location % this.map.length;
        int myRow = this.map.height - (this.map.height - (this.location / this.map.length));

        int col = g % this.map.length;
        int row = this.map.height - (this.map.height - (g / this.map.length));

        System.out.println("GOLDLOC");
        System.out.println(g);
        System.out.println(col);
        System.out.println(row);
        System.out.println("MYLOC");
        System.out.println(this.location);
        System.out.println(myCol);
        System.out.println(myRow);

        if (myCol < col && this.map.getRight(this.location) != -1 && !isObstacle(this.map.getRight(this.location))) {
            right();
            System.out.println("RIGHT");
            harvest(this.location);
        } else if (myCol < col && this.map.getRight(this.location) != -1 && isObstacle(this.map.getRight(this.location))) {
            down();
            right();
            System.out.println("DOWN");
            harvest(this.location);
        } else if (myRow < row && this.map.getBelow(this.location) != -1 && !isObstacle(this.map.getBelow(this.location))) {
            down();
            System.out.println("DOWN");
            harvest(this.location);
        } else if (myRow < row && this.map.getBelow(this.location) != -1 && isObstacle(this.map.getBelow(this.location))) {
            right();
            System.out.println("RIGHT");
            harvest(this.location);
        } else if (myCol > col && this.map.getLeft(this.location) != -1 && !isObstacle(this.map.getLeft(this.location))) {
            left();
            System.out.println("LEFT");
            harvest(this.location);
        } else if (myCol > col && this.map.getLeft(this.location) != -1 && isObstacle(this.map.getLeft(this.location))) {
            up();
            left();
            System.out.println("UP");
            harvest(this.location);
        } else if (myRow > row && this.map.getAbove(this.location) != -1 && !isObstacle(this.map.getAbove(this.location))) {
            up();
            System.out.println("UP");
            harvest(this.location);
        } else if (myRow > row && this.map.getAbove(this.location) != -1 && isObstacle(this.map.getAbove(this.location))) {
            left();
            System.out.println("LEFT");
            harvest(this.location);
        }



        return true;
    }

    public boolean isObstacle(int index) {
        if (index == -1) {
            return true;
        } else if (!this.map.map.get(index).type.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * move dwarf back to home base once finished
     * @throws IOException exception for logger
     */

    @Override
    void move() throws IOException {
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
     * @throws IOException exception for logger
     */

    public boolean harvest(int indexx) throws IOException {
        int collected = 0;
        int index = this.map.getRight(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                this.map.map.get(index).type = "0";
                this.game.logger.log(this + " HARVESTED GOLD AT " + index);
                //System.out.println("HARVESTED GOLD");
                this.game.collected++;
                collected++;
            }
        }

        index = this.map.getBelow(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                this.map.map.get(index).type = "0";
                //System.out.println("HARVESTED GOLD");
                this.game.logger.log(this + " HARVESTED GOLD AT " + index);
                this.game.collected++;
                collected++;
            }
        }

        index = this.map.getLeft(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                this.map.map.get(index).type = "0";
                this.game.logger.log(this + " HARVESTED GOLD AT " + index);
                //System.out.println("HARVESTED GOLD");
                this.game.collected++;
                collected++;
            }
        }

        index = this.map.getAbove(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.contains("G")) {
                this.map.map.get(index).type = "0";
                this.game.logger.log(this + " HARVESTED GOLD AT " + index);
                //System.out.println("HARVESTED GOLD");
                this.game.collected++;
                collected++;
            }
        }
        if (collected > 0) {
            return true;
        }
        return false;
    }


}
