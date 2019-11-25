
import java.util.ArrayList;
import java.util.Arrays;

/**
 * list graph
 */

public class ListUndirectedWeightedGraph extends UndirectedWeightedGraph {

    /**
     * list of nodes in graph
     */
    ArrayList<Node> nodes;

    /**
     * size of graph
     */
    int size;

    /**
     * constructor
     */

    ListUndirectedWeightedGraph() {
        this.nodes = new ArrayList<>();
        this.size = 0;
    }

    /**
     * add a vertex to the graph
     * @param name name of node being added
     * @return 1 if successful
     */

    @Override
    int addNode(String name) {
        for (Node node : this.nodes) {
            if (node.key.equals(name)) {
                return -1;
            }
        }
        this.size += 1;
        this.nodes.add(new Node(name, this.size));
        return this.size;
    }

    /**
     * add an edge
     * @param name1 name of start vertex
     * @param name2 name of end vertex
     * @param weight weight of edge
     * @return true if successful
     */

    @Override
    boolean addEdge(String name1, String name2, float weight) {
        Node node1 = null;
        Node node2 = null;
        for (Node node : this.nodes) {
            if (node.key.equals(name1)) {
                node1 = node;
            }
        }
        for (Node node : this.nodes) {
            if (node.key.equals(name2)) {
                node2 = node;
            }
        }
        
        

        if (node1 == null || node2 == null) {
            return false;
        }

        for (Edge edge : node1.edges) {
            if (edge.start.equals(node1) && edge.end.equals(node2)) {
                edge.weight = weight;
                return true;
            }
        }
        

        Edge edge = new Edge(node1, node2, weight);
        if (node1.edges.add(edge)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * add an edge between two verticies
     * @param node1 start vertex
     * @param node2 end vertex
     * @param weight weight of edge
     * @return true if successful
     */

    @Override
    boolean addEdge(int node1, int node2, float weight) {
        Node nodeOne = null;
        Node nodeTwo = null;
        for (Node node : this.nodes) {
            if (node.num == node1) {
                nodeOne = node;
            }
        }
        for (Node node : this.nodes) {
            if (node.num == node2) {
                nodeTwo = node;
            }
        }
        
        if (nodeOne == null || nodeTwo == null) {
            return false;
        }

        for (Edge edge : nodeOne.edges) {
            if (edge.start.equals(nodeOne) && edge.end.equals(nodeTwo)) {
                edge.weight = weight;
                return true;
            }
        }
        

        Edge edge = new Edge(nodeOne, nodeTwo, weight);
        if (nodeOne.edges.add(edge)) {
            System.out.println(Arrays.toString(getEdgeNames()));
            return true;
        } else {
            return false;
        }
    }

    /**
     * update the weight of an edge
     * @param name1 start node of edge
     * @param name2 end node of edge
     * @param weight weight of edge
     * @return true if successful
     */

    @Override
    boolean updateWeight(String name1, String name2, float weight) {
        Node node1 = null;
        Node node2 = null;
        for (Node node : this.nodes) {
            if (node.key.equals(name1)) {
                node1 = node;
            }
        }

        for (Node node : this.nodes) {
            if (node.key.equals(name2)) {
                node2 = node;
            }
        }
        

        if (node1 == null || node2 == null) {
            return false;
        }
        

        for (Edge edge : node1.edges) {
            if (edge.end == node2) {
                edge.weight = weight;
                return true;
            }
        }
        

        return false;
    }

    /**
     * update the weight of an edge
     * @param node1 start node of edge
     * @param node2 end node of edge
     * @param weight weight of edge
     * @return true if successful
     */

    @Override
    boolean updateWeight(int node1, int node2, float weight) {
        Node nodeOne = null;
        Node nodeTwo = null;
        for (Node node : this.nodes) {
            if (node.num == node1) {
                nodeOne = node;
            }
        }
        for (Node node : this.nodes) {
            if (node.num == node2) {
                nodeTwo = node;
            }
        }

        if (nodeOne == null || nodeTwo == null) {
            return false;
        }

        for (Edge edge : nodeOne.edges) {
            if (edge.end == nodeTwo) {
                edge.weight = weight;
                return true;
            }
        }
        return false;
    }

    /**
     * get all node names in graph
     * @return list of node names
     */

    @Override
    String[] getNodeNames() {
        String[] string = new String[this.size];
        for (int i = 0; i < this.size; i++) {
            string[i] = this.nodes.get(i).key;
        }
        return string;
    }

    /**
     * get a list of neighbor names for a certain vertex
     * @param name vertex name
     * @return list of names
     */

    @Override
    String[] getNeighborNames(String name) {
        ArrayList<String> string = new ArrayList<>();
        Node node1 = null;
        for (Node node : this.nodes) {
            if (node.key.equals(name)) {
                node1 = node;
            }
        }

        if (node1 == null) {
            String[] arr = new String[string.size()];
            return string.toArray(arr);
        }

        for (int i = 0; i < node1.edges.size(); i++) {
            string.add(node1.edges.get(i).end.key);

        }

        String[] arr = new String[string.size()];
        return string.toArray(arr);
    }

    /**
     * get the numbers of the neighbors of a certain vertex
     * @param node vertex number
     * @return number array
     */

    @Override
    int[] getNeighborNums(int node) {
        int[] arr = new int[]{};
        Node node1 = null;
        for (Node nodes : this.nodes) {
            if (nodes.num == node) {
                node1 = nodes;
            }
        }

        if (node1 == null) {
            return arr;
        }

        for (int i = 0; i < node1.edges.size(); i++) {
            arr[i] = node1.edges.get(i).end.num;
        }

        return arr;
    }

    /**
     * get a list of the named edges in the graph
     * @return list of named edges
     */

    @Override
    NamedEdge[] getEdgeNames() {
        ArrayList<NamedEdge> arr = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            ArrayList<Edge> edges = this.nodes.get(i).edges;
            for (int j = 0; j < edges.size(); j++) {
                arr.add(new NamedEdge(edges.get(j).start.key, edges.get(j).end.key, edges.get(j).weight));
            }
        }
        NamedEdge[] array = new NamedEdge[arr.size()];

        for (int i = 0; i < arr.size(); i++) {
            array[i] = arr.get(i);
        }
        return array;
    }

    /**
     * get a list of numbered edges in the graph
     * @return list of numbered edges
     */

    @Override
    NumberedEdge[] getEdgeNumbers() {
        ArrayList<NumberedEdge> arr = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            ArrayList<Edge> edges = this.nodes.get(i).edges;
            for (int j = 0; j < edges.size(); j++) {
                arr.add(new NumberedEdge(edges.get(j).start.num, edges.get(j).end.num, edges.get(j).weight));
            }
        }

        NumberedEdge[] array = new NumberedEdge[arr.size()];

        for (int i = 0; i < arr.size(); i++) {
            array[i] = arr.get(i);
        }
        return array;
    }

    /**
     * empty graph
     */

    @Override
    void empty() {
        this.nodes = new ArrayList<>();
        this.size = 0;
    }
}
