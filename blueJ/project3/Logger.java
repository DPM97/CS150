import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * logger class
 */
public class Logger {
    /**
     * file
     */
    FileWriter file;

    /**
     * constructor
     * @param testNum test number
     * @throws IOException exception for fileWriter
     */
    public Logger() throws IOException {
        this.file = new FileWriter(new File("sim" + new Random().nextInt() + ".txt"));
    }

    /**
     * log to file
     * @param message log message
     * @throws IOException exception for append
     */

    public void log(String message) throws IOException {
        this.file.append(message);
        this.file.append("\n");
    }

    /**
     * close filewriter
     * @throws IOException exception for writer
     */

    public void close() throws IOException {
        this.file.close();
    }
}
