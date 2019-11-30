import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * csv class
 */

public class Csv {
    /**
     * file
     */
    FileWriter file;

    /**
     * constructor
     * @throws IOException exception for writer
     */

    public Csv() throws IOException {
        this.file = new FileWriter(new File("out.txt"));
    }

    /**
     * add column
     * @throws IOException exception for writer
     */

    public void addCol() throws IOException {
        this.file.append(",");
    }

    /**
     * add data
     * @param data data val
     * @throws IOException
     */

    public void append(String data) throws IOException {
        this.file.append(data);
    }

    /**
     * add row
     * @throws IOException exception for writer
     */

    public void addRow() throws IOException {
        this.file.append("\n");
    }

    /**
     * close writer
     * @throws IOException exception for writer
     */

    public void close() throws IOException {
        this.file.close();
    }
}
