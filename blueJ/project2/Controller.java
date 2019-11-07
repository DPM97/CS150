import java.io.IOException;

/**
 * controller class
 * creates and starts game
 */

public class Controller {

    /**
     * main class
     * imports map and starts simulation
     * @param args input args (not needed)
     * @throws IOException file reader exception
     * @throws InterruptedException exception for sleep method
     */

    public static void main(String[] args) throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        new Game(map).start();
    }
}
