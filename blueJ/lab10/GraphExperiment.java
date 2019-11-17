import java.io.*;
import java.util.Arrays;

/**
 * experiment class
 */

public class GraphExperiment {
    /**
     * main method
     * @param args input args (not needed)
     * @throws IOException exception for file reader
     */
    public static void main(String[] args) throws IOException {
        GraphExperiment exp = new GraphExperiment();
        exp.start();
    }

    /**
     * start method
     * @throws IOException exception for file reader
     */

    public void start() throws IOException {
        DirectedGraph<String> graph = new DirectedGraph<String>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("./graph.txt")));
        String line = reader.readLine();
        String[] split = line.split(" ");
        for (int i = 0; i < split.length; i++) { //add nodes
            graph.addNode(split[i]);
        }

        line = reader.readLine();
        while(line != null) {
            split = line.split(" ");
            graph.addEdge(split[0], split[1], Integer.parseInt(split[2]));
            line = reader.readLine();
        }

        graph.dijkstra("a");
    }
}
