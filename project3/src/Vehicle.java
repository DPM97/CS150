import java.util.Stack;

/**
 * vehicle class
 */

public class Vehicle {
    /**
     * graph
     */
    Graph graph;

    /**
     * vehicle / driver state
     */
    String state;

    /**
     * vehicle node
     */
    Graph.GraphNode node;

    /**
     * location between nodes
     */
    int loc;

    /**
     * gps stack
     */
    Stack<Graph.GraphNode> gps;

    /**
     * customer
     */
    Customer customer;

    /**
     * idle time
     */
    int idleTime;

    /**
     * total ride distance
     */

    int rideDist;

    /**
     * constructor
     * @param sim simulation
     * @param graph graph
     */

    public Vehicle(Simulation sim, Graph graph) {
        this.state = "IDLE";
        this.node = graph.randNode();
        this.graph = graph;
        this.loc = 0;
        this.gps = new Stack<>();
        this.customer = null;
        this.idleTime = 0;
        this.rideDist = 0;
    }

    /**
     * move vehicle
     */

    public void move() {
        int curDist = this.graph.getDistance(this.node.key, this.gps.peek().key);
        if (this.loc < curDist) {
            this.loc += 600; // 23mph avg (600 m / min)
        } else {
            this.rideDist += curDist;
            this.node = this.gps.pop();
            this.loc = 0;
            if (this.gps.isEmpty()) {
                this.state = "AT-DEST";
            }
        }
    }
}
