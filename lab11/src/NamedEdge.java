public class NamedEdge implements Comparable<NamedEdge> {
    /**
     * start node name
     */
    String name1;
    /**
     * end node name
     */
    String name2;
    /**
     * weight
     */
    float weight;

    /**
     * constructor
     * @param name1 start node name
     * @param name2 end node name
     * @param weight edge weight
     */

    public NamedEdge(String name1, String name2, float weight) {
        this.name1 = name1;
        this.name2 = name2;
        this.weight = weight;
    }

    /**
     * get start name
     * @return name
     */

    String getName1() {
        return this.name1;
    }

    /**
     * get end name
     * @return name
     */

    String getName2() {
        return this.name2;
    }

    /**
     * get weight
     * @return weight
     */

    float getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(NamedEdge o) {
        return Float.compare(this.weight, o.getWeight());
    }

    @Override
    public String toString() {
        return ("START: " + getName1() + " END: " + getName2() + " WEIGHT: " + getWeight());
    }
}
