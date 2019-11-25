/**
 * experiment controller class
 */
public class ExperimentController {
    /**
     * constructor
     */
    public ExperimentController() {

    }

    /**
     * main method
     * @param args args
     */

    public static void main(String[] args) {
        MatrixUndirectedWeightedGraph graph = new MatrixUndirectedWeightedGraph(4);
        graph.addNode("one");
        graph.addNode("two");
        graph.addNode("three");
        graph.addNode("four");
        graph.addEdge("one", "two", 20);
        graph.addEdge("two", "three", 204);
        graph.addEdge("two", "four", 890);
        graph.addEdge("one", "four", 840);
        graph.kruskal(new MatrixUndirectedWeightedGraph(4));
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
            return new ListUndirectedWeightedGraph();
        }
    }

    /**
     * create random matrix graph
     * @param p determine empty or not
     * @return graph
     */

    MatrixUndirectedWeightedGraph RandomMatrixGraph(float p) {
        if (p < 0 || p > 1) {
            return new MatrixUndirectedWeightedGraph(4);
        } else {
            return new MatrixUndirectedWeightedGraph(4);
        }
    }

    /**
     * time checking connectivity
     * @param graph input graph
     * @return time
     */

    long timeConnected(UndirectedWeightedGraph graph) {
        return 0;
    }

    /**
     * time to do kruskal
     * @param graph input graph
     * @return time
     */

    long timeKruskal(UndirectedWeightedGraph graph) {
        return 0;
    }
}
