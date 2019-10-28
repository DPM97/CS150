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
     * @param game game object
     */

    public Digger(Map map, Game game) {
        super(map);
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
        System.out.println("Nearest dirt node above = " + nearestTop);
        System.out.println("Nearest dirt node below = " + nearestBottom);
        System.out.println("Nearest dirt node left = " + nearestLeft);
        System.out.println("Nearest dirt node right = " + nearestRight);
        System.out.println("Distance to nearest left dirt node = " + distanceLeft);
        System.out.println("Distance to nearest right dirt node = " + distanceRight);
        System.out.println("Distance to nearest bottom dirt node = " + distanceBottom);
        System.out.println("Distance to nearest top dirt node = " + distanceTop);

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
        } else if (index == 1) {
            System.out.println("Moving down");
            down();
        } else if (index == 2) {
            System.out.println("Moving left");
            left();
        } else if (index == 3) {
            System.out.println("Moving up");
            up();
        }
        System.out.println(this.location);
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
                this.map.map.get(this.location).dwarfs.put(this, this.game.tries);
                goDown();
            } else if (this.map.map.get(index).type.equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                System.out.println("Encountered pit");
                this.game.pitsDiscovered.push(reverse()); //push memory so that builder knows how to get to pit
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("G")) {
                System.out.println("Encountered gold");
                this.map.map.get(index).type = "GD";
                this.game.goldDiscovered.push(reverse());
            } else if (this.map.map.get(index).type.equals("GD")) {
                System.out.println("Waiting for harvester to harvest gold");
            } else if (this.map.map.get(index).type.equals("PD")) {
                System.out.println("Waiting for builder to fill pit");
            } else {
                System.out.println("Already Dug" + index);
                this.map.map.get(this.location).dwarfs.put(this, this.game.tries);
                System.out.println(this.map.map.get(this.location).type);
                goDown();
            }
        } else {
            left();
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
                this.map.map.get(this.location).dwarfs.put(this, this.game.tries);
                goUp();
            } else if (this.map.map.get(index).type.equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                System.out.println("Encountered pit");
                this.map.map.get(index).type = "PD";
                this.game.pitsDiscovered.push(reverse());
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("G")) {
                System.out.println("Encountered gold");
                this.map.map.get(index).type = "GD";
                this.game.goldDiscovered.push(reverse());
            } else if (this.map.map.get(index).type.equals("GD")) {
                System.out.println("Waiting for harvester to harvest gold");
            } else if (this.map.map.get(index).type.equals("PD")) {
                System.out.println("Waiting for builder to fill pit");
            } else {
                System.out.println("Already Dug" + index);
                this.map.map.get(this.location).dwarfs.put(this, this.game.tries);
                goUp();
            }
        } else {
            right();
        }
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
                this.map.map.get(this.location).dwarfs.put(this, this.game.tries);
                goLeft();
            } else if (this.map.map.get(index).type.equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                System.out.println("Encountered pit");
                this.map.map.get(index).type = "PD";
                this.game.pitsDiscovered.push(reverse());
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("G")) {
                System.out.println("Encountered gold");
                this.map.map.get(index).type = "GD";
                this.game.goldDiscovered.push(reverse());
            } else if (this.map.map.get(index).type.equals("GD")) {
                System.out.println("Waiting for harvester to harvest gold");
            } else if (this.map.map.get(index).type.equals("PD")) {
                System.out.println("Waiting for builder to fill pit");
            } else {
                System.out.println("Already Dug" + index);
                this.map.map.get(this.location).dwarfs.put(this, this.game.tries);
                goLeft();
            }
        } else {
            up();
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
                this.map.map.get(this.location).dwarfs.put(this, this.game.tries);
                this.map.map.get(index).type = "0";
                goRight();
            } else if (this.map.map.get(index).type.equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).type.equals("P")) {
                System.out.println("Encountered pit");
                this.map.map.get(index).type = "PD";
                this.game.pitsDiscovered.push(reverse());
            } else if (this.map.map.get(index).type.contains("L") || this.map.map.get(index).type.contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).type.equals("G")) {
                System.out.println("Encountered gold");
                this.map.map.get(index).type = "GD";
                this.game.goldDiscovered.push(reverse());
            } else if (this.map.map.get(index).type.equals("GD")) {
                System.out.println("Waiting for harvester to harvest gold");
            } else if (this.map.map.get(index).type.equals("PD")) {
                System.out.println("Waiting for builder to fill pit");
            } else {
                System.out.println("Already Dug" + index);
                goRight();
            }
        } else {
            down();
        }
    }


    /**
     * not needed
     *
     * @param index index
     */

    @Override
    void dig(int index) {
    }

    /**
     * not needed
     *
     * @param index index
     */

    @Override
    void fill(int index) {
    }

    void checkForGold() {
        int index = this.map.getLeft(this.location);
        if (index != -1 && this.map.map.get(index).type.equals("G")) {
            this.map.map.get(index).type = "GD";
            this.game.goldDiscovered.push(reverse());
        }

        index = this.map.getRight(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("G")) {
            this.map.map.get(index).type = "GD";
            this.game.goldDiscovered.push(reverse());
        }

        index = this.map.getAbove(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("G")) {
            this.map.map.get(index).type = "GD";
            this.game.goldDiscovered.push(reverse());
        }

        index = this.map.getBelow(this.location);

        if (index != -1 && this.map.map.get(index).type.equals("G")) {
            this.map.map.get(index).type = "GD";
            this.game.goldDiscovered.push(reverse());
        }
    }
}