import java.util.HashMap;

/**
 * tile class
 */

public class Tile {
    /**
     * hashmap (dwarf, time on tile)
     */
    HashMap<Dwarf, Integer> dwarfs;
    /**
     * type of tile
     */
    String type;

    /**
     * constructor
     * @param type type of tile
     */

    public Tile(String type) {
        this.dwarfs = new HashMap<Dwarf, Integer>();
        this.type = type;
    }
}
