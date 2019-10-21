import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
    ArrayList<String> map;
    int height;
    int length;
    int totalElements;
    public Map() {
        this.map = new ArrayList<>();
        this.height = 0;
        this.totalElements = 0;
        this.length = 0;
    }

    public void importMap() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/map.txt"));
        String line = reader.readLine();
        while(line != null) {
            String[] curLine = line.split(",");
            for (int i = 0; i < curLine.length; i++) {
                this.map.add(curLine[i]);
                this.totalElements++;
            }
            this.height++;
            line = reader.readLine();
        }
        this.length = this.totalElements / this.height;
    }

    public int getBelow(int index) {
        if (Math.floor(index / this.length) != Math.floor((index + this.length)/ this.length) && Math.floor((index + this.length)/ this.length) < this.height) {
            return index + this.length;
        } else { //off the map
            return -1;
        }
    }

    public int getAbove(int index) {
        if (Math.floor(index / this.length) != Math.floor((index + this.length)/ this.length) && Math.floor((index + this.length)/ this.length) > 0) {
            return index - this.length;
        } else { //off the map
            return -1;
        }
    }

    public int getLeft(int index) {
        if (Math.floor(index / this.length) == Math.floor((index - 1)/ this.length)) {
            return index - 1;
        } else { //off the map
            return -1;
        }
    }

    public int getRight(int index) {
        if (Math.floor(index / this.length) == Math.floor((index + 1)/ this.length)) {
            return index + 1;
        } else { //off the map
            return -1;
        }
    }

    /*


    public boolean dig(int index) {
        if (index != -1 && this.map.get(index) == "D") {
            this.map.set(index, "0");
            return true;
        } else {
            return false;
        }
    }

    public boolean harvest(int index) {
        if (index != -1 && this.map.get(index) == "G") {
            this.map.set(index, "0");
            return true;
        } else {
            return false;
        }
    }

    public boolean build(int index) {
        if (index != -1 && this.map.get(index) == "P") {
            this.map.set(index, "D"); //fill pit with dirt
            return true;
        } else {
            return false;
        }
    }



     */


}
