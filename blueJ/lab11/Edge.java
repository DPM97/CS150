/**
 * edge class
 */

public class Edge {
    /**
     * start node
     */
    Node start;
    /**
     * end node
     */
    Node end;
    /**
     * edge weight
     */
    float weight;

    /**
     * constructor
     * @param start start node
     * @param end end node
     * @param weight edge weight
     */

    public Edge(Node start, Node end, float weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}