import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

/**
 * digger class: contains digger classes
 */

public class Digger extends Dwarf {
    /**
     * game object
     */
    Game game;

    /**
     * constructor
     * @param map  game map object
     */

    public Digger(Map map, Game game) {
        super(map, game);
        this.game = game;
    }

    /**
     * algorithm to fetch nearest dirt tile
     * in regard to the dwarfs current location
     * @throws IOException exception for logger
     */

    @Override
    void move() throws IOException {
        //checkForGold();
        //System.out.println("Gnome location: " + this.location);
        int nearestLeft = this.location;
        //System.out.println(this.map.getLeft(this.location));
        int distanceLeft = 0;
        while (this.map.getLeft(nearestLeft) != -1) {
            if (!this.map.map.get(nearestLeft).type.equals("D") && !this.map.map.get(nearestLeft).type.equals("L")) {
                nearestLeft = this.map.getLeft(nearestLeft);
            } else if (this.map.map.get(nearestLeft).type.equals("L")) {
                distanceLeft = 0;
                break;
            } else {
                break;
            }
        }
        if (!this.map.map.get(nearestLeft).type.equals("L")) {
            distanceLeft = this.location - nearestLeft;
        }
        if (this.map.map.get(nearestLeft).dwarfs.size() > 0) {
            distanceLeft = 0;
        }

        int nearestRight = this.location;
        int distanceRight = 0;
        while (this.map.getRight(nearestRight) != -1) {
            if (!this.map.map.get(nearestRight).type.equals("D") && !this.map.map.get(nearestRight).type.equals("L")) {
                nearestRight = this.map.getRight(nearestRight);
            } else if (this.map.map.get(nearestRight).type.equals("L")) {
                distanceRight = 0;
                break;
            } else {
                break;
            }
        }
        if (!this.map.map.get(nearestRight).type.equals("L")) {
            distanceRight = nearestRight - this.location;
        }
        if (this.map.map.get(nearestRight).dwarfs.size() > 0) {
            distanceRight = 0;
        }

        int nearestTop = this.location;
        int distanceTop = 0;
        while (this.map.getAbove(nearestTop) != -1) {
            if (!this.map.map.get(nearestTop).type.equals("D") && !this.map.map.get(nearestTop).type.equals("L")) {
                nearestTop = this.map.getAbove(nearestTop);
                distanceTop++;
            } else if (this.map.map.get(nearestTop).type.equals("L")) {
                distanceTop = 0;
                break;
            } else {
                break;
            }
        }

        if (this.map.map.get(nearestTop).dwarfs.size() > 0) {
            distanceTop = 0;
        }


        int nearestBottom = this.location;
        int distanceBottom = 0;
        while (this.map.getBelow(nearestBottom) != -1) {
            if (!this.map.map.get(nearestBottom).type.equals("D") && !this.map.map.get(nearestBottom).type.equals("L")) {
                nearestBottom = this.map.getBelow(nearestBottom);
                distanceBottom++;
            } else if (this.map.map.get(nearestBottom).type.equals("L")) {
                distanceBottom = 0;
                break;
            } else {
                break;
            }
        }
        if (this.map.map.get(nearestBottom).dwarfs.size() > 0) {
            distanceBottom = 0;
        }

        int[] distances = new int[]{distanceRight, distanceBottom, distanceLeft, distanceTop};


        int smallest = 100;
        int index = 0;
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] != 0 && distances[i] < smallest) {
                smallest = distances[i];
                index = i;
            }
        }
        //System.out.println(index);
        if (index == 0) {
            //System.out.println("Moving right");
            right();
            checkForGold();
            checkForPits();
        } else if (index == 1) {
            //System.out.println("Moving down");
            down();
            checkForPits();
        } else if (index == 2) {
            //System.out.println("Moving left");
            left();
            checkForGold();
            checkForPits();
        } else if (index == 3) {
            //System.out.println("Moving up");
            up();
            checkForGold();
            checkForPits();
        }
        //System.out.println(this.location);
    }

    /**
     * not needed
     * @param index index
     * @return not needed
     * @throws IOException exception for logger
     */
    @Override
    boolean harvest(int index) throws IOException {
        return false;
    }

    /**
     * move down
     * @throws IOException exception for logger
     */

    @Override
    public void down() throws IOException {
        int index = this.map.getBelow(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("D")) {
                this.map.map.get(index).type = "0";
                goDown();
            } else if (this.map.map.get(index).type.equals("L")) {
                //System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                checkForPits();
                left();
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                //System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("G")) {
                checkForGold();
                left();
            } else if (this.map.map.get(index).type.equals("GD")) {
                left();
            } else if (this.map.map.get(index).type.equals("PD")) {
                left();
            } else {
                goDown();
            }
        } else {
            right();
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
            if (this.map.map.get(index).type.equals("D")) {
                this.map.map.get(index).type = "0";
                goUp();
            } else if (this.map.map.get(index).type.equals("L")) {
                //System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                checkForPits();
                right();
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                //System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("G")) {
                checkForGold();
                right();
            } else if (this.map.map.get(index).type.equals("GD")) {
                right();
            } else if (this.map.map.get(index).type.equals("PD")) {
                right();
            } else {
                //System.out.println("Already Dug" + index);
                goUp();
            }
        } else {
            left();
        }
    }

    /**
     * dig
     * @param dwarf current dwarf
     */

    @Override
    boolean dig(Dwarf dwarf) {
        return false;
    }

    /**
     * move left
     * @throws IOException exception for logger
     */

    @Override
    public void left() throws IOException {
        int index = this.map.getLeft(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("D")) {
                this.map.map.get(index).type = "0";
                goLeft();
            } else if (this.map.map.get(index).type.equals("L")) {
                //System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                checkForPits();
                up();
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                //System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("G")) {
                checkForGold();
                up();
            } else if (this.map.map.get(index).type.equals("GD")) {
                //System.out.println("Waiting for harvester to harvest gold");
                up();
            } else if (this.map.map.get(index).type.equals("PD")) {
                up();
                //System.out.println("Waiting for builder to fill pit");
            } else {
                //System.out.println("Already Dug" + index);
                goLeft();
            }
        } else {
            down();
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
            if (this.map.map.get(index).type.equals("D")) {
                this.map.map.get(index).type = "0";
                goRight();
            } else if (this.map.map.get(index).type.equals("L")) {
                //out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                //System.out.println("Encountered pit");
                checkForPits();
                down();
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                //System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("G")) {
                checkForGold();
                down();
            } else if (this.map.map.get(index).type.equals("GD")) {
                down();
                //System.out.println("Waiting for harvester to harvest gold");
            } else if (this.map.map.get(index).type.equals("PD")) {
                down();
                //System.out.println("Waiting for builder to fill pit");
            } else {
                //System.out.println("Already Dug" + index);
                goRight();
            }
        } else {
            up();
        }
    }


    /**
     * not needed
     * @param dwarf dwarf
     */

    @Override
    boolean fill(Dwarf dwarf) {
        return false;
    }

    /**
     * check around current location for gold tiles
     * @throws IOException exception for logger
     */


    void checkForGold() throws IOException {
        int index = this.map.getLeft(this.location);
        if (index != -1 && this.map.map.get(index).type.equals("G")) {
            this.map.map.get(index).type = "GD";
            this.game.goldDiscovered.push(this);
            this.game.logger.log(this + " FOUND GOLD AT " + index);
        }

        index = this.map.getRight(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("G")) {
            this.map.map.get(index).type = "GD";
            this.game.goldDiscovered.push(this);
            this.game.logger.log(this + " FOUND GOLD AT " + index);
        }

        index = this.map.getAbove(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("G")) {
            this.map.map.get(index).type = "GD";
            this.game.goldDiscovered.push(this);
            this.game.logger.log(this + " FOUND GOLD AT " + index);
        }

        index = this.map.getBelow(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("G")) {
            this.map.map.get(index).type = "GD";
            this.game.goldDiscovered.push(this);
            this.game.logger.log(this + " FOUND GOLD AT " + index);
        }
    }

    /**
     * check around current location for pit tiles
     * @throws IOException exception for logger
     */

    void checkForPits() throws IOException {
        int index = this.map.getLeft(this.location);
        if (index != -1 && this.map.map.get(index).type.equals("P")) {
            this.map.map.get(index).type = "PD";
            this.game.pitsDiscovered.push(this);
            this.game.logger.log(this + " FOUND PIT AT " + index);
        }

        index = this.map.getRight(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("P")) {
            this.map.map.get(index).type = "PD";
            this.game.pitsDiscovered.push(this);
            this.game.logger.log(this + " FOUND PIT AT " + index);
        }


        index = this.map.getAbove(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("P")) {
            this.map.map.get(index).type = "PD";
            this.game.pitsDiscovered.push(this);
            this.game.logger.log(this + " FOUND PIT AT " + index);
        }


        index = this.map.getBelow(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("P")) {
            this.map.map.get(index).type = "PD";
            this.game.pitsDiscovered.push(this);
            this.game.logger.log(this + " FOUND PIT AT " + index);
        }

    }
}