import java.io.IOException;

public class Controller {

    private Controller() {

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Map map = new Map();
        map.importMap();
        new Game(map).start();
    }
}
