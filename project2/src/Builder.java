public class Builder extends Dwarf {
    public Builder(Map map) {
        super(map);
    }


    @Override
    public void left() {
        if(this.location == 0) {
            return;
        } else {
            int index = this.map.getLeft(this.location);
            if (index != -1) {
                if (this.map.map.get(index).equals("P")) {
                    this.map.map.set(index, "D"); //fill with dirt
                }
            }
        }
    }

    @Override
    public void right() {
        int index = this.map.getRight(this.location);
        if (index != -1) {
            System.out.println(this.map.map.get(index));
            if (this.map.map.get(index).equals("P")) {
                this.map.map.set(index, "D"); //fill with dirt
            }
        }
    }

    @Override
    public void down() {

    }

    @Override public void up() {

    }
}
