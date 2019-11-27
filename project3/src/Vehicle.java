import java.util.Stack;

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
    }

    public void move() {
        if (this.loc < this.graph.getDistance(this.node.key, this.gps.peek().key)) {
            this.loc++;
        } else {
            this.node = this.gps.pop();
            this.loc = 0;
            if (this.gps.isEmpty()) {
                this.state = "AT-DEST";
            }
        }
    }
}
