import java.sql.SQLOutput;
import java.util.ArrayList;

public class BinaryNode<E extends Comparable<E>> {
    BinaryNode left;
    BinaryNode right;
    E data;

    public BinaryNode(E data) {
        this.left = null;
        this.right = null;
        this.data = data;
    }

}

