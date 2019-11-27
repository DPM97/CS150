public class Customer {
    /**
     * customer state
     */
    String state;

    /**
     * customer node
     */
    Graph.GraphNode node;

    /**
     * destination
     */
    Graph.GraphNode destination;

    /**
     * driver
     */

    Vehicle driver;

    /**
     * constructor
     * @param sim simulation
     * @param graph graph
     */
    public Customer(Simulation sim, Graph graph) {
        this.state = "WAITING";
        this.node = graph.randNode();
        this.destination = graph.randNode();
        this.driver = null;
    }
}
