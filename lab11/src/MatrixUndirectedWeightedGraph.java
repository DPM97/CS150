import java.util.ArrayList;
import java.util.Arrays;

/**
 * matrix graph class
 */

public class MatrixUndirectedWeightedGraph extends UndirectedWeightedGraph {

    /**
     * matrix of node objects
     */
    Float[][] nodes;

    /**
     * size
     */

    int size;

    /**
     * list of verticies
     */

    ArrayList verticies;

    /**
     * total amount of nodes
     */

    int total;

    /**
     * constructor
     * @param amount amount of verticies being added...
     */

    MatrixUndirectedWeightedGraph(int amount) {
        this.nodes = new Float[amount][amount];
        this.size = 0;
        this.verticies = new ArrayList<String>(amount);
        this.total = amount;
    }

    /**
     * add a vertex to the graph
     * @param name name of node being added
     * @return 1 if successful
     */

    @Override
    int addNode(String name) {
        this.size++;
        if (this.verticies.contains(name)) {
            return -1;
        } else {
            this.verticies.add(name);
            return 1;
        }
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
        if (this.verticies.contains(name1) && this.verticies.contains(name2)) {
            this.nodes[this.verticies.indexOf(name1)][this.verticies.indexOf(name2)] = weight;
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
        if (node1 <= this.verticies.size() && node2 <= this.verticies.size()) {
            this.nodes[node1][node2] = weight;
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
        addEdge(name1, name2, weight);
        return true;
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
        addEdge(node1, node2, weight);
        return true;
    }

    /**
     * get all node names in graph
     * @return list of node names
     */

    @Override
    String[] getNodeNames() {
        String[] arr = new String[this.verticies.size()];
        for (int i = 0; i < this.verticies.size(); i++) {
            arr[i] = (String) this.verticies.get(i);
        }
        return arr;
    }

    /**
     * get a list of neighbor names for a certain vertex
     * @param name vertex name
     * @return list of names
     */

    @Override
    String[] getNeighborNames(String name) {
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < this.nodes.length; i++) {
            if (this.nodes[this.verticies.indexOf(name)][i] != null) {
                arr.add(this.verticies.get(i).toString());
            }
        }
        String[] strArr = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            strArr[i] = arr.get(i);
        }
        return strArr;
    }

    /**
     * get the numbers of the neighbors of a certain vertex
     * @param node vertex number
     * @return number array
     */

    @Override
    int[] getNeighborNums(int node) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < this.nodes.length; i++) {
            if (this.nodes[node][i] != null) {
                arr.add(i);
            }
        }
        int[] intArr = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            intArr[i] = arr.get(i);
        }
        return intArr;
    }

    /**
     * get a list of the named edges in the graph
     * @return list of named edges
     */

    @Override
    NamedEdge[] getEdgeNames() {
        NamedEdge[] strArr = new NamedEdge[this.verticies.size()];
        int covered = 0;
        for (int i = 0; i < this.nodes.length; i++) {
            for (int j = 0; j < this.nodes[i].length; j++) {
                if (this.nodes[i][j] != null) {
                    strArr[covered] = new NamedEdge((String) this.verticies.get(i), (String) this.verticies.get(j), this.nodes[i][j]);
                    covered++;
                }
            }
        }
        if (strArr[strArr.length - 1] == null) {
            strArr = Arrays.copyOf(strArr, strArr.length - 1);
        }
        return strArr;
    }

    /**
     * get a list of numbered edges in the graph
     * @return list of numbered edges
     */

    @Override
    NumberedEdge[] getEdgeNumbers() {
        NumberedEdge[] intArr = new NumberedEdge[this.verticies.size()];
        int covered = 0;
        for (int i = 0; i < this.nodes.length; i++) {
            for (int j = 0; j < this.nodes[i].length; j++) {
                if (this.nodes[i][j] != null) {
                    intArr[covered] = new NumberedEdge(i, j, this.nodes[i][j]);
                    covered++;
                }
            }
        }
        if (intArr[intArr.length - 1] == null) {
            intArr = Arrays.copyOf(intArr, intArr.length - 1);
        }
        return intArr;
    }

    /**
     * empty graph
     */

    @Override
    void empty() {
        this.verticies = new ArrayList();
        this.nodes = new Float[this.total][this.total];
    }
}
