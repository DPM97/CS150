import java.util.Random;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

/**
 * experiment controller class
 */
public class ExperimentController {
    /**
     * random
     */
    Random random;
    /**
     * random seed for testing
     */
    int randInt;
    /**
     * constructor
     */
    public ExperimentController() {
        this.random = new Random(34030035);
        this.randInt = 0;
    }

    /**
     * main method
     * @param args args
     * @throws IOException for test method
     */

    public static void main(String[] args) throws IOException {
        ExperimentController controller = new ExperimentController();
        controller.test();
    }
    
    /**
     * get data for csv file / graphs
     * @throws IOException for file writer
     */
    
    public void test() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("graph.csv")));
        this.randInt = new Random().nextInt();
        for (int i = 0; i < 100; i++) {
            writer.append("" + (float) (.01 * i));
            writer.append(",");
            this.random = new Random(this.randInt);
            long total = 0;
            for (var j = 0; j < 10; j++) {
                total += timeKruskal(RandomMatrixGraph((float) (.01 * i)));
            }
            total = total / 10;
            writer.append(Long.toString(total));
            writer.append(",");
            this.random = new Random(this.randInt);
            total = 0;
            for (var j = 0; j < 10; j++) {
                total += timeConnected(RandomMatrixGraph((float) (.01 * i)));
            }
            total = total / 10;
            writer.append(Long.toString(total));
            //writer.append(Long.toString(timeConnected(RandomMatrixGraph((float) (.01 * i)))));
            writer.append(",");
            this.random = new Random(this.randInt);
            total = 0;
            for (var j = 0; j < 10; j++) {
                total += timeKruskal(RandomListGraph((float) (.01 * i)));
            }
            total = total / 10;
            writer.append(Long.toString(total));
            //writer.append(Long.toString(timeKruskal(RandomListGraph((float) (.01 * i)))));
            writer.append(",");
            this.random = new Random(this.randInt);
            total = 0;
            for (var j = 0; j < 10; j++) {
                total += timeConnected(RandomListGraph((float) (.01 * i)));
            }
            total = total / 10;
            writer.append(Long.toString(total));
            //writer.append(Long.toString(timeConnected(RandomListGraph((float) (.01 * i)))));
            writer.append("\r\n");
            this.randInt = new Random().nextInt();
        }
        writer.close();
    }

    /**
     * create random list graph
     * @param p determine empty or not
     * @return graph
     */

    ListUndirectedWeightedGraph RandomListGraph(float p) {
        if (p < 0 || p > 1) {
            return new ListUndirectedWeightedGraph();
        } else {
            ListUndirectedWeightedGraph graph = new ListUndirectedWeightedGraph();
            for (var i = 0; i < 10; i++) {
                graph.addNode("" + ((char) (97 + i)));
            }
            String[] nodeList = graph.getNodeNames();
            for (var i = 0; i < 10; i++) {
                for (var j = 0; j < 10; j++) {
                    float num = random.nextFloat();
                    if (num < p) {
                       graph.addEdge(i, j, random.nextInt(9) + 1); 
                    }
                }
            }
            return graph;
        }
    }

    /**
     * create random matrix graph
     * @param p determine empty or not
     * @return graph
     */

    MatrixUndirectedWeightedGraph RandomMatrixGraph(float p) {
        if (p < 0 || p > 1) {
            return new MatrixUndirectedWeightedGraph(0);
        } else {
            MatrixUndirectedWeightedGraph graph = new MatrixUndirectedWeightedGraph(100);
            for (var i = 0; i < 10; i++) {
                graph.addNode("" + ((char) (97 + i)));
            }
            String[] nodeList = graph.getNodeNames();
            for (var i = 0; i < 10; i++) {
                for (var j = 0; j < 10; j++) {
                    float num = random.nextFloat();
                    if (num < p) {
                       graph.addEdge(i, j, random.nextInt(9) + 1); 
                    }
                }
            }
            return graph;
        }
    }

    /**
     * time checking connectivity
     * @param graph input graph
     * @return time
     */

    long timeConnected(UndirectedWeightedGraph graph) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            graph.isConnected();
        }
        long endTime = System.currentTimeMillis();
        //System.out.println("TimeConnected: " + graph.getClass().getName() + " (x5000): " + (endTime - startTime) + "ms");
        return (endTime - startTime);
    }

    /**
     * time to do kruskal
     * @param graph input graph
     * @return time
     */

    long timeKruskal(UndirectedWeightedGraph graph) {
            if (graph.getClass().getName() == "MatrixUndirectedWeightedGraph") {
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < 5000; i++) {
                    graph.kruskal(new MatrixUndirectedWeightedGraph(10));
                }
                long endTime = System.currentTimeMillis();
                //System.out.println("TimeKruskal: " + graph.getClass().getName() + " (x5000): " + (endTime - startTime) + "ms");
                return (endTime - startTime);
            } else {
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < 5000; i++) {
                    graph.kruskal(new ListUndirectedWeightedGraph());
                }
                long endTime = System.currentTimeMillis();
                //System.out.println("TimeKruskal: " + graph.getClass().getName() + " (x5000): " + (endTime - startTime) + "ms");
                return (endTime - startTime);
            }
        
    }
}
