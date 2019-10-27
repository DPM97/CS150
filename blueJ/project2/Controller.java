import java.io.IOException;

/**
 * main controller class
 */

public class Controller {
    
    /**
     * constructor
     */

    private Controller() {

    }
    
    /**
     * main method
     * create map object &
     * start game with map obj
     * @param args arguments
     */

    public static void main(String[] args) throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        new Game(map).start();
    }
}
