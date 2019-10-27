/**
 * builder class
 */
public class Builder extends Dwarf {
    /**
     * constructor
     * @param map map object
     */
    public Builder(Map map) {
        super(map);
        this.pitLoc = null;
    }

    /**
     * go left
     */

    @Override
    public void left() {
        if(this.location == 0) {
            return;
        } else {
            int index = this.map.getLeft(this.location);
            if (index != -1) {
                if (this.map.map.get(index).type.equals("PD")) {
                    System.out.println("Filling pit");
                    this.map.map.get(index).type = "0";
                }
            }
        }
    }

    /**
     * go right
     */

    @Override
    public void right() {
        int index = this.map.getRight(this.location);
        if (index != -1) {
            if (this.map.map.get(index).type.equals("PD")) {
                System.out.println("Filling pit");
                this.map.map.get(index).type = "0";
            }
        }
    }

    /**
     * fill pit with dirt (try left and right)
     * @param index current location
     */

    @Override
    void fill(int index) {
        this.location = index;
        left();
        right();
    }

    /**
     * not needed
     */

    @Override
    public void down() { }

    /**
     * not needed
     */

    @Override public void up() { }

    /**
     * not needed
     * @param index index
     */

    @Override
    void dig(int index) { }

    /**
     * not needed
     */

    @Override
    void move() { }

}
