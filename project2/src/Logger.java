import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * logger class
 */
public class Logger {
    /**
     * game obj
     */

    Game game;

    /**
     * constructor
     * @param game for tick
     */
    BufferedWriter file;
    public Logger(Game game) throws IOException {
        this.file = new BufferedWriter(new FileWriter(new File("logs.txt")));
        this.game = game;
    }

    /**
     * log message to file
     * @param message message to add
     * @throws IOException exception for writer
     */

    public void log(String message) throws IOException {
        this.file.append(this.game.tries + ": " + message);
        this.file.append("\n");
    }

    /**
     * close fileWriter
     * @throws IOException exception for writer
     */

    public void close() throws IOException {
        this.file.close();
    }
}
