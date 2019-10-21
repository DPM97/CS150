public class Digger extends Dwarf {
    public Digger(Map map) {
        super(map);
    }

    @Override
    public void down() {
        int index = this.map.getBelow(this.location);
        if (index != -1) {
            if (this.map.map.get(index).equals("D")) {
                this.map.map.set(index, "0");
                goDown();
            } else if (this.map.map.get(index).equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).equals("P")) {
                System.out.println("Encountered pit");
            } else if (this.map.map.get(index).contains("L") || this.map.map.get(index).contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).equals("G")) {
                System.out.println("Encountered gold");
            } else {
                System.out.println("Already Dug");
                goDown();
            }
        }
    }

    @Override
    public void up() {
        int index = this.map.getAbove(this.location);
        if (index != -1) {
            if (this.map.map.get(index).equals("D")) {
                this.map.map.set(index, "0");
                goUp();
            } else if (this.map.map.get(index).equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).equals("P")) {
                System.out.println("Encountered pit");
            } else if (this.map.map.get(index).contains("L") || this.map.map.get(index).contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).equals("G")) {
                System.out.println("Encountered gold");
            } else {
                System.out.println("Already Dug");
                goUp();
            }
        }
    }

    @Override
    public void left() {
        int index = this.map.getLeft(this.location);
        if (index != -1) {
            if (this.map.map.get(index).equals("D")) {
                this.map.map.set(index, "0");
                goLeft();
            } else if (this.map.map.get(index).equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).equals("P")) {
                System.out.println("Encountered pit");
            } else if (this.map.map.get(index).contains("L") || this.map.map.get(index).contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).equals("G")) {
                System.out.println("Encountered gold");
            } else {
                System.out.println("Already Dug");
                goLeft();
            }
        }
    }

    @Override
    public void right() {
        int index = this.map.getRight(this.location);
        if (index != -1) {
            if (this.map.map.get(index).equals("D")) {
                this.map.map.set(index, "0");
                goRight();
            } else if (this.map.map.get(index).equals("L")) {
                System.out.println("Encountered lava");
            } else if (this.map.map.get(index).equals("P")) {
                System.out.println("Encountered pit");
            } else if (this.map.map.get(index).contains("L") || this.map.map.get(index).contains("R")) {
                System.out.println("Encountered river");
            } else if (this.map.map.get(index).equals("G")) {
                System.out.println("Encountered gold");
            } else {
                System.out.println("Already Dug");
                goRight();
            }
        }
    }
}
