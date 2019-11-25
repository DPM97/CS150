import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * abstract graph class
 */

public abstract class UndirectedWeightedGraph {

    /**
     * constructor
     */

    public UndirectedWeightedGraph() {

    }
    /**
     * add node
     * @param name node name
     * @return -1 if already in set
     */
    abstract int addNode(String name);
    /**
     * add edge
     * @param name1 name of start node
     * @param name2 name of end node
     * @param weight weight of edge
     * @return return true if successful
     */
    abstract boolean addEdge(String name1, String name2, float weight);
    /**
     * add edge
     * @param node1 name of start node
     * @param node2 name of end node
     * @param weight weight of edge
     * @return return true if successful
     */
    abstract boolean addEdge(int node1, int node2, float weight);
    /**
     * update weight of an edge
     * @param name1 start name
     * @param name2 end name
     * @param weight new weight
     * @return true if successful
     */
    abstract boolean updateWeight(String name1, String name2, float weight);
    /**
     * update weight of an edge
     * @param node1 start num
     * @param node2 end num
     * @param weight new weight
     * @return true if successful
     */
    abstract boolean updateWeight(int node1, int node2, float weight);
    /**
     * get node names
     * @return arr of node names
     */
    abstract String[] getNodeNames();
    /**
     * get neighbor names of node
     * @param name name of node being checked
     * @return arr of names
     */
    abstract String[] getNeighborNames(String name);
    /**
     * get neighbor nums of node
     * @param node num of node being checked
     * @return arr of nums
     */
    abstract int[] getNeighborNums(int node);
    /**
     * get edge names
     * @return arr of edge names
     */
    abstract NamedEdge[] getEdgeNames();
    /**
     * get edge numbers
     * @return arr of edge nums
     */
    abstract NumberedEdge[] getEdgeNumbers();
    /**
     * empty graph
     */
    abstract void empty();

    /**
     * check if graph is connected
     * @return true if connected
     */

    boolean isConnected() {
        ArrayList<String> list = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push(getNodeNames()[0]);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (!list.contains(current)) {
                list.add(current);
                String[] neighbors = getNeighborNames(current);
                for (int i = 0; i < neighbors.length; i++) {
                    stack.push(neighbors[i]);
                }
            }
        }
        if (list.size() == getNodeNames().length) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * implememnt kruskal's algorithm
     * @param minTree empty tree
     * @return true if successful
     */

    boolean kruskal(UndirectedWeightedGraph minTree) {
        if (!this.isConnected()) {
            return false;
        }
        minTree.empty();

        String[] nodeNames = getNodeNames();
        for (int i = 0; i < nodeNames.length; i++) {
            minTree.addNode(nodeNames[i]);
        }

        NamedEdge[] edges = getEdgeNames();
        PriorityQueue<NamedEdge> edgeList = new PriorityQueue<NamedEdge>();
        for (int i = 0; i < edges.length; i++) {
            edgeList.add(edges[i]);
        }
        ArrayList<ArrayList<String>> vertexSets = new ArrayList<>();
        String[] names = getNodeNames();
        for (int i = 0; i < names.length; i++) {
            ArrayList<String> list = new ArrayList<>();
            list.add(names[i]);
            vertexSets.add(list);
        }
        ArrayList<NamedEdge> resultList = new ArrayList<>();

        while (vertexSets.size() > 1 && edgeList.size() > 0) {
            NamedEdge nextEdge = edgeList.poll();
            ArrayList<String> vSet1 = null;
            for (ArrayList<String> list : vertexSets) {
                if (list.contains(nextEdge.name1)) {
                    vSet1 = list;
                }
            }
            ArrayList<String> vSet2 = null;
            for (ArrayList<String> list : vertexSets) {
                if (list.contains(nextEdge.name2)) {
                    vSet2 = list;
                }
            }

            if (!vSet1.equals(vSet2)) {
                resultList.add(nextEdge);
                vertexSets.remove(vSet1);
                vertexSets.remove(vSet2);
                vSet1.addAll(vSet2);
                vertexSets.add(vSet1);
            }
        }

        for (int i = 0; i < resultList.size(); i++) {
            minTree.addEdge(resultList.get(i).name1, resultList.get(i).name2, resultList.get(i).weight);
        }

        //System.out.println(Arrays.toString(minTree.getEdgeNames()));

        return true;
    }
}
