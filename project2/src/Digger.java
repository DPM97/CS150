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
     * consttructor
     *
     * @param map  game map object
     */

    public Digger(Map map, Game game) {
        super(map, game);
        this.game = game;
    }

    /**
     * algorithm to fetch nearest dirt tile
     */

    @Override
    void move() {
        //checkForGold();
        System.out.println("Gnome location: " + this.location);
        int nearestLeft = this.location;
        System.out.println(this.map.getLeft(this.location));
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

        /*

        System.out.println("Nearest dirt node above = " + nearestTop);
        System.out.println("Nearest dirt node below = " + nearestBottom);
        System.out.println("Nearest dirt node left = " + nearestLeft);
        System.out.println("Nearest dirt node right = " + nearestRight);
        System.out.println("Distance to nearest left dirt node = " + distanceLeft);
        System.out.println("Distance to nearest right dirt node = " + distanceRight);
        System.out.println("Distance to nearest bottom dirt node = " + distanceBottom);
        System.out.println("Distance to nearest top dirt node = " + distanceTop);


         */


        int smallest = 100;
        int index = 0;
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] != 0 && distances[i] < smallest) {
                smallest = distances[i];
                index = i;
            }
        }
        System.out.println(index);
        if (index == 0) {
            System.out.println("Moving right");
            right();
            checkForGold();
            checkForPits();
        } else if (index == 1) {
            System.out.println("Moving down");
            down();
            checkForPits();
        } else if (index == 2) {
            System.out.println("Moving left");
            left();
            checkForGold();
            checkForPits();
        } else if (index == 3) {
            System.out.println("Moving up");
            up();
            checkForGold();
            checkForPits();
        }
        //System.out.println(this.location);
    }

    /**
     * move down
     */

    @Override
    public void down() {
        int index = this.map.getBelow(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("D")) {
                this.map.map.get(index).type = "0";
                goDown();
            } else if (this.map.map.get(index).type.equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                checkForPits();
                right();
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("G")) {
                checkForGold();
                right();
            } else if (this.map.map.get(index).type.equals("GD")) {
                right();
            } else if (this.map.map.get(index).type.equals("PD")) {
                right();
            } else {
                goDown();
            }
        } else {
            right();
        }
    }

    /**
     * move up
     */

    @Override
    public void up() {
        int index = this.map.getAbove(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("D")) {
                this.map.map.get(index).type = "0";
                goUp();
            } else if (this.map.map.get(index).type.equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                checkForPits();
                left();
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("G")) {
                checkForGold();
                left();
            } else if (this.map.map.get(index).type.equals("GD")) {
                left();
            } else if (this.map.map.get(index).type.equals("PD")) {
                left();
            } else {
                System.out.println("Already Dug" + index);
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
     */

    @Override
    public void left() {
        int index = this.map.getLeft(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("D")) {
                this.map.map.get(index).type = "0";
                goLeft();
            } else if (this.map.map.get(index).type.equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                checkForPits();
                up();
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("G")) {
                checkForGold();
                up();
            } else if (this.map.map.get(index).type.equals("GD")) {
                System.out.println("Waiting for harvester to harvest gold");
                up();
            } else if (this.map.map.get(index).type.equals("PD")) {
                up();
                System.out.println("Waiting for builder to fill pit");
            } else {
                System.out.println("Already Dug" + index);
                goLeft();
            }
        } else {
            down();
        }
    }

    /**
     * move right
     */

    @Override
    public void right() {
        int index = this.map.getRight(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("D")) {
                this.map.map.get(index).type = "0";
                goRight();
            } else if (this.map.map.get(index).type.equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                System.out.println("Encountered pit");
                checkForPits();
                right();
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("G")) {
                checkForGold();
                right();
            } else if (this.map.map.get(index).type.equals("GD")) {
                down();
                System.out.println("Waiting for harvester to harvest gold");
            } else if (this.map.map.get(index).type.equals("PD")) {
                down();
                System.out.println("Waiting for builder to fill pit");
            } else {
                System.out.println("Already Dug" + index);
                goRight();
            }
        } else {
            up();
        }
    }


    /**
     * not needed
     *
     * @param dwarf dwarf
     */

    @Override
    boolean fill(Dwarf dwarf) {
        return false;
    }



    void checkForGold() {
        int index = this.map.getLeft(this.location);
        if (index != -1 && this.map.map.get(index).type.equals("G")) {
            this.map.map.get(index).type = "GD";
            this.game.goldDiscovered.push(this);
        }

        index = this.map.getRight(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("G")) {
            this.map.map.get(index).type = "GD";
            this.game.goldDiscovered.push(this);
        }

        index = this.map.getAbove(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("G")) {
            this.map.map.get(index).type = "GD";
            this.game.goldDiscovered.push(this);
        }

        index = this.map.getBelow(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("G")) {
            this.map.map.get(index).type = "GD";
            this.game.goldDiscovered.push(this);
        }
    }

    void checkForPits() {
        int index = this.map.getLeft(this.location);
        if (index != -1 && this.map.map.get(index).type.equals("P")) {
            this.map.map.get(index).type = "PD";
            this.game.pitsDiscovered.push(this);
        }

        index = this.map.getRight(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("P")) {
            this.map.map.get(index).type = "PD";
            this.game.pitsDiscovered.push(this);
        }


        index = this.map.getAbove(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("P")) {
            this.map.map.get(index).type = "PD";
            this.game.pitsDiscovered.push(this);
        }


        index = this.map.getBelow(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("P")) {
            this.map.map.get(index).type = "PD";
            this.game.pitsDiscovered.push(this);
        }

    }

    public boolean harvest(int indexx) {
        int index = this.map.getLeft(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("D")) {
                this.map.map.get(index).type = "0";
                System.out.println("BUILT OVER PIT");
                return true;
            }
        }
        index = this.map.getRight(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("D")) {
                this.map.map.get(index).type = "0";
                System.out.println("BUILT OVER PIT");
                return true;
            }
        }
        index = this.map.getBelow(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("D")) {
                this.map.map.get(index).type = "0";
                System.out.println("BUILT OVER PIT");
                return true;
            }
        }
        index = this.map.getAbove(indexx);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("D")) {
                this.map.map.get(index).type = "0";
                System.out.println("BUILT OVER PIT");
                return true;
            }
        }
        return false;
    }
}