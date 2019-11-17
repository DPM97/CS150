import java.util.*;

/**
 * graph class
 * @param <E> generic
 */

public class DirectedGraph <E> {
    /**
     * arraylist of nodes
     */
    ArrayList<DirectedGraphNode> nodes;

    /**
     * dijkstra string for unit testing
     */

    String dijkstraString = "";


    /**
     * constructor
     */

    public DirectedGraph() {
        this.nodes = new ArrayList<>();
    }

    /**
     * adds a node to the graph
     * @param val value being added
     * @return true if successful
     */

    public boolean addNode(E val) {
        for (DirectedGraphNode node : this.nodes) {
            if (node.key == val) {
                return false;
            }
        }
        this.nodes.add(new DirectedGraphNode(val));
        return true;

    }

    /**
     * add an edge to a node
     * @param val1 first node's value
     * @param val2 second node's value
     * @param weight weight of the edge
     * @return true if successful
     */

    public boolean addEdge(E val1, E val2, int weight) {
        DirectedGraphNode node1 = null;
        DirectedGraphNode node2 = null;
        for (DirectedGraphNode node : this.nodes) {
            if (node.key.equals(val1)) {
                node1 = node;
            }
        }
        for (DirectedGraphNode node : this.nodes) {
            if (node.key.equals(val2)) {
                node2 = node;
            }
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        for (DirectedGraphEdge edge : node1.edges) {
            if (edge.start.equals(node1) && edge.end.equals(node2)) {
                edge.weight = weight;
                return true;
            }
        }

        DirectedGraphEdge edge = new DirectedGraphEdge(node1, node2, weight);
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

    public ArrayList<E> getNeighbors(E val) {
        ArrayList<E> list = new ArrayList<>();
        DirectedGraphNode node = null;
        for (DirectedGraphNode nodes : this.nodes) {
            if (val.equals(nodes.key)) {
                node = nodes;
            }
        }
        if (node == null) {
            return null;
        }

        for (DirectedGraphEdge edge : node.edges) {
            list.add(edge.end.key);
        }
        return list;
    }

    /**
     * runs dijkstra's algorithm on a node for all other reachable nodes
     * @param val key value of node in question
     */

    public void dijkstra(E val) {
        
        String string = "";

        PriorityQueue<DirectedGraphNode> unvisited = new PriorityQueue();

        DirectedGraphNode node = null;

        for (DirectedGraphNode nodes : this.nodes) {
            if (nodes.key.equals(val)) {
                node = nodes;
            }
        }
        if (node == null) {
            return;
        }



        for (DirectedGraphNode nodes : this.nodes) {
            if (nodes != node) {
                nodes.dist = 1000000000;
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
            DirectedGraphNode curNode = unvisited.poll();
            
            for (DirectedGraphEdge edge : curNode.edges) {
                DirectedGraphNode node2 = edge.end;
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
                
        dijkstraString = string;
        System.out.println(string);

    }

    /**
     * graph node class
     */

    public class DirectedGraphNode implements Comparable<DirectedGraphNode> {
        /**
         * key
         */
        E key;
        /**
         * list of node's edges
         */
        ArrayList<DirectedGraphEdge> edges;

        /**
         * distance for shortest path
         */

        int dist;

        /**
         * pred vertex
         */

         DirectedGraphNode pred;

        /**
         * constructor
         * @param data key
         */

        public DirectedGraphNode(E data) {
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
        public int compareTo(DirectedGraphNode o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    /**
     * edge class
     */

    public class DirectedGraphEdge {
        /**
         * start node
         */
        DirectedGraphNode start;
        /**
         * end ndoe
         */
        DirectedGraphNode end;
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

        public DirectedGraphEdge(DirectedGraphNode start, DirectedGraphNode end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
