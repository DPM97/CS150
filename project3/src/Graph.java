import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

public class Graph {
    /**
     * arraylist of nodes
     */
    ArrayList<GraphNode> nodes;

    /**
     * random
     */

    Random random;


    /**
     * constructor
     */

    public Graph() {
        this.nodes = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * adds a node to the graph
     * @param val value being added
     * @return true if successful
     */

    public boolean addNode(int val) {
        for (GraphNode node : this.nodes) {
            if (node.key == val) {
                return false;
            }
        }
        this.nodes.add(new GraphNode(val));
        return true;

    }

    /**
     * add an edge to a node
     * @param val1 first node's value
     * @param val2 second node's value
     * @param weight weight of the edge
     * @return true if successful
     */

    public boolean addEdge(int val1, int val2, int weight) {
        GraphNode node1 = null;
        GraphNode node2 = null;
        for (GraphNode node : this.nodes) {
            if (node.key == val1) {
                node1 = node;
            }
        }
        for (GraphNode node : this.nodes) {
            if (node.key == val2) {
                node2 = node;
            }
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        for (GraphEdge edge : node1.edges) {
            if (edge.start.equals(node1) && edge.end.equals(node2)) {
                edge.weight = weight;
                return true;
            }
        }

        GraphEdge edge = new GraphEdge(node1, node2, weight);
        if (node1.edges.add(edge)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * gets neighbors of a node
     * @param val key value of the node in question
     * @return arraylist of node keys
     */

    public ArrayList<Integer> getNeighbors(int val) {
        ArrayList<Integer> list = new ArrayList<>();
        GraphNode node = null;
        for (GraphNode nodes : this.nodes) {
            if (val == nodes.key) {
                node = nodes;
            }
        }
        if (node == null) {
            return null;
        }

        for (GraphEdge edge : node.edges) {
            list.add(edge.end.key);
        }
        return list;
    }

    /**
     * runs dijkstra's algorithm on a node for all other reachable nodes
     * @param val key value of node in question
     */

    public void dijkstra(int val) {

        String string = "";

        PriorityQueue<GraphNode> unvisited = new PriorityQueue();

        GraphNode node = null;

        for (GraphNode nodes : this.nodes) {
            if (nodes.key == val) {
                node = nodes;
            }
        }
        if (node == null) {
            return;
        }



        for (GraphNode nodes : this.nodes) {
            if (nodes != node) {
                nodes.dist = 999999999;
                nodes.pred = null;
                unvisited.add(nodes);
            } else {
                nodes.dist = 0;
                nodes.pred = null;
                unvisited.add(nodes);
            }
        }

        string += (unvisited.peek().key + " " + unvisited.peek().dist + " ");

        while (!unvisited.isEmpty()) {
            GraphNode curNode = unvisited.poll();

            for (GraphEdge edge : curNode.edges) {
                GraphNode node2 = edge.end;
                int curWeight = edge.weight;
                int altDist = curNode.dist + curWeight;

                if (altDist < node2.dist) {
                    unvisited.remove(node2);
                    node2.dist = altDist;
                    node2.pred = curNode;
                    unvisited.add(node2);
                }
            }

            if (!unvisited.isEmpty()) {
                string += (unvisited.peek().key + " " + unvisited.peek().dist + " ");
            }
        }


    }

    /**
     * graph node class
     */

    public class GraphNode implements Comparable<GraphNode> {
        /**
         * key
         */
        int key;
        /**
         * list of node's edges
         */
        ArrayList<GraphEdge> edges;

        /**
         * distance for shortest path
         */

        int dist;

        /**
         * pred vertex
         */

        GraphNode pred;

        /**
         * constructor
         * @param data key
         */

        public GraphNode(int data) {
            this.key = data;
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
        public int compareTo(GraphNode o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    /**
     * edge class
     */

    public class GraphEdge {
        /**
         * start node
         */
        GraphNode start;
        /**
         * end node
         */
        GraphNode end;
        /**
         * edge weight
         */
        int weight;

        /**
         * constructor
         * @param start start node
         * @param end end node
         * @param weight edge weight
         */

        public GraphEdge(GraphNode start, GraphNode end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public void create(int amount, int min, int max) {
        addNode(0);
        for (int i = 1; i < amount; i++) {
            if (getNeighbors(i-1).size() == 0) {
                addNode(i);
                addEdge(i-1, i, this.random.nextInt(max - min) + min); //each graph is connected to atleast 1 node
            }
        }

        float randConnectivity = this.random.nextFloat() * (5 - 3) + 3;
        while (calcConnectivity() < randConnectivity) {
            chooseRandNode(randConnectivity, min, max);
        }


    }

    public float calcConnectivity() {
        float total = 0;
        for (int i = 0; i < this.nodes.size(); i++) {
            total += this.nodes.get(i).edges.size();
        }
        return total / this.nodes.size();
    }

    public void chooseRandNode(float connectivity, int min, int max) {
        GraphNode randNode = this.nodes.get(this.random.nextInt((this.nodes.size())));
        if (randNode.edges.size() < 6 && calcConnectivity() < connectivity) {
            GraphNode endNode = this.nodes.get(this.random.nextInt((this.nodes.size())));
            addEdge(randNode.key, endNode.key, this.random.nextInt(max - min) + min);
        }
    }

    public Stack<GraphNode> updateDist(int start, int end) {
        Stack<GraphNode> stack = new Stack<>();

        PriorityQueue<GraphNode> unvisited = new PriorityQueue();

        GraphNode node = null;
        GraphNode endNode = null;

        for (GraphNode nodes : this.nodes) {
            if (nodes.key == start) {
                node = nodes;
            }
        }

        for (GraphNode nodes : this.nodes) {
            if (nodes.key == end) {
                endNode = nodes;
            }
        }

        if (node == null || endNode == null) {
            return new Stack<>();
        }



        for (GraphNode nodes : this.nodes) {
            if (nodes != node) {
                nodes.dist = 999999999;
                nodes.pred = null;
                unvisited.add(nodes);
            } else {
                nodes.dist = 0;
                nodes.pred = null;
                unvisited.add(nodes);
            }
        }

        while (!unvisited.isEmpty()) {
            GraphNode curNode = unvisited.poll();

            for (GraphEdge edge : curNode.edges) {
                GraphNode node2 = edge.end;
                int curWeight = edge.weight;
                int altDist = curNode.dist + curWeight;

                if (altDist < node2.dist) {
                    unvisited.remove(node2);
                    node2.dist = altDist;
                    node2.pred = curNode;
                    unvisited.add(node2);
                }
            }

            if (!unvisited.isEmpty()) {
                if (unvisited.peek().key == end) {
                    stack = fetchPath(node, endNode);
                }
            }
        }
        return stack;
    }

    public Stack<GraphNode> fetchPath(GraphNode start, GraphNode end) {
        Stack<GraphNode> stack = new Stack<>();
        stack.push(end);
        while (end.key != start.key && end.pred != null) {
            end = end.pred;
            stack.push(end);
        }
        return stack;
    }

    public void adjustTraffic() {
        float multiplier = (float) (this.random.nextFloat() * (0.9 - 1.1) + 1.1);
        for (GraphNode node : this.nodes) {
            for (GraphEdge edge : node.edges) {
                edge.weight *= multiplier;
            }
        }
    }

    public GraphNode randNode() {
        return this.nodes.get(this.random.nextInt((this.nodes.size())));
    }

    public int getDistance(int node1, int node2) {
        dijkstra(node1);
        int dist = 0;
        GraphNode current = this.nodes.get(node2);
        while (current.pred != null) {
            dist += current.dist;
            current = current.pred;
        }
        return (dist + current.dist);
    }

}
