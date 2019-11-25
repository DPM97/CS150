import java.util.ArrayList;

/**
 * node class
 */

public class Node implements Comparable<Node> {
    /**
     * key
     */
    String key;
    /**
     * list of node's edges
     */
    ArrayList<Edge> edges;

    /**
     * distance for shortest path
     */

    int dist;

    /**
     * pred vertex
     */

    Node pred;

    /**
     * node number
     */

    int num;

    /**
     * constructor
     * @param data key
     * @param num node number
     */

    public Node(String data, int num) {
        this.key = data;
        this.num = num;
        this.edges = new ArrayList<>();
        this.dist = 0;
        this.pred = null;
    }

    /**
     * compareTo method
     * for priorityQueue
     * @param o object being compared
     */

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.dist, o.dist);
    }
}