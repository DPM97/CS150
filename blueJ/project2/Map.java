import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * map class
 */

public class Map {
    /**
     * mmap (arraylist of tiles)
     */
    ArrayList<Tile> map;
    /**
     * map height
     */
    int height;
    /**
     * map length
     */
    int length;
    /**
     * total # of elements in map
     */
    int totalElements;

    /**
     * constructor
     */

    public Map() {
        this.map = new ArrayList<Tile>();
        this.height = 0;
        this.totalElements = 0;
        this.length = 0;
    }

    /**
     * import map from file
     * @throws IOException file reader exception
     */

    public void importMap() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./map.txt"));
        String line = reader.readLine();
        while(line != null) {
            String[] curLine = line.split(",");
            for (int i = 0; i < curLine.length; i++) {
                this.map.add(new Tile(curLine[i]));
                this.totalElements++;
            }
            this.height++;
            line = reader.readLine();
        }
        this.length = this.totalElements / this.height;
    }

    /**
     * get index of element below
     * @param index location
     * @return index below input
     */

    public int getBelow(int index) {
        if ((index + this.length) < this.totalElements && index + this.length < this.map.size()) {
            return index + this.length;
        } else { //off the map
            return -1;
        }
    }

    /**
     * get index of element above
     * @param index location
     * @return index above input
     */

    public int getAbove(int index) {
        if ((index - this.length) > 0) {
            return index - this.length;
        } else { //off the map
            return -1;
        }
    }

    /**
     * get index of element left
     * @param index location
     * @return index left input
     */

    public int getLeft(int index) {
        if (Math.floor(index / this.length) == Math.floor((index - 1)/ this.length)) {
            return index - 1;
        } else { //off the map
            return -1;
        }
    }

    /**
     * get index of element right
     * @param index location
     * @return index right input
     */

    public int getRight(int index) {
        if (Math.floor(index / this.length) == Math.floor((index + 1)/ this.length) && index + 1 < this.map.size()) {
            return index + 1;
        } else { //off the map
            return -1;
        }
    }

    /**
     * print map
     */

    public void print() {
        String string = "";
        for (int i = 0; i < map.size(); i++) {
            string += (this.map.get(i).type + " ");
            if (i % (this.length + 1) == 0 || i == this.length + 1) {
                if (i != 0) {
                    string += ("\n");
                }
            }
        }
        System.out.println(string);
    }

    /**
     * check if there is any dirt left
     * on the map
     * @return true if no dirt
     */

    public boolean checkDirt() {
        int total = 0;
        for (int i = 0; i < this.totalElements; i++) {
            if (this.map.get(i).type.equals("D")) {
                total++;
            }
        }
        if (total < 50) {
            return true;
        } else {
            return false;
        }
    }
}
