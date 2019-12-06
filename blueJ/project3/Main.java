import java.io.IOException;
import java.util.ArrayList;

/**
 * main class
 */

public class Main {

    /**
     * main method
     * @param args arguments from cmd line (not needed)
     * @throws IOException exception for logger
     */

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.test();
    }

    /**
     * test class generates csv
     * @throws IOException exception for logger
     */

    public void test() throws IOException {
        int complete = 0;
        ArrayList<Integer> nodes = new ArrayList<>();
        ArrayList<Integer> drivers = new ArrayList<>();
        ArrayList<Integer> customers = new ArrayList<>();
        ArrayList<Double> rides = new ArrayList<>();
        ArrayList<Double> satisfaction = new ArrayList<>();
        ArrayList<Double> dWaitTime = new ArrayList<>();
        ArrayList<Double> rideDist = new ArrayList<>();
        ArrayList<Double> connectivity = new ArrayList<>();
        ArrayList<Double> weights = new ArrayList<>();
        ArrayList<Double> edges = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 3; j++) {
                int amount;
                if (j == 1) {
                    amount = 1000;
                } else if (j == 2) {
                    amount = 5000;
                } else {
                    amount = 10000;
                }
                for (int v = 1; v <= 5; v++) {
                    Graph graph = new Graph();
                    graph.create(amount, 1, 1500);
                    int driverAmount = 100 * v;
                    int customerAmount = 50 * i;
                    Simulation sim = new Simulation(graph, driverAmount, customerAmount);
                    double[] data = sim.start();
                    rides.add(data[0]);
                    satisfaction.add(data[1]);
                    dWaitTime.add(data[2]);
                    rideDist.add(data[3]);
                    connectivity.add(data[4]);
                    edges.add(data[5]);
                    weights.add(data[6]);
                    drivers.add(driverAmount);
                    nodes.add(amount);
                    customers.add(customerAmount);
                    System.out.println("test " + complete + " completed...");
                    complete++;
                }
            }
        }
        Csv csv = new Csv();
        csv.file.append("GraphSize,Connectivity,Drivers,Customers,Rides,Satisfaction,DriverWaitTime,RideDist,WeightAvg,Edges");
        csv.addRow();
        for (int i = 0; i < nodes.size(); i++) {
            csv.append(Integer.toString(nodes.get(i)));
            csv.addCol();
            csv.append(Double.toString(connectivity.get(i)));
            csv.addCol();
            csv.append(Integer.toString(drivers.get(i)));
            csv.addCol();
            csv.append(Double.toString(customers.get(i)));
            csv.addCol();
            csv.append(Double.toString(rides.get(i)));
            csv.addCol();
            csv.append(Double.toString(satisfaction.get(i)));
            csv.addCol();
            csv.append(Double.toString(dWaitTime.get(i)));
            csv.addCol();
            csv.append(Double.toString(rideDist.get(i)));
            csv.addCol();
            csv.append(Double.toString(weights.get(i)));
            csv.addCol();
            csv.append(Double.toString(edges.get(i)));
            csv.addRow();
        }
        csv.close();
    }

}
