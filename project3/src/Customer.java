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
     * wait time
     */

    int waitTime;

    /**
     * satisfaction
     */

    double satisfaction;

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
        this.waitTime = 0;
        this.satisfaction = 0;
    }
}
