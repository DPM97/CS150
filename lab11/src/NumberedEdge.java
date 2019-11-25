/**
 * numbered edge class
 */
public class NumberedEdge {
    /**
     * start node num
     */
    int node1;
    /**
     * end node num
     */
    int node2;
    /**
     * weight
     */
    float weight;

    /**
     * constructor
     * @param node1 num of start node
     * @param node2 num of end node
     * @param weight edge weight
     */
    public NumberedEdge(int node1, int node2, float weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    /**
     * get start node of edge
     * @return start node num
     */

    int getNode1() {
        return this.node1;
    }

    /**
     * get end node of edge
     * @return end node num
     */

    int getNode2() {
        return this.node2;
    }

    /**
     * get weight of edge
     * @return weight
     */

    float getWeight() {
        return this.weight;
    }

    /**
     * new toString method
     * @return string
     */

    @Override
    public String toString() {
        return ("START: " + getNode1() + " END: " + getNode2() + " WEIGHT: " + getWeight());
    }
}
