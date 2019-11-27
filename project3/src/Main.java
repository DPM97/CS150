import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.create(1000, 1, 10000);
        Simulation sim = new Simulation(graph, 50, 0.32);
        sim.start();
    }
}
