import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.create(1000, 1, 100);
        Simulation sim = new Simulation(graph, 24, 42);
        sim.start();
    }
}
