import java.util.Random;

/**
 * worker class
 * implements comparable (worker type)
 */

public class Worker extends Person implements Comparable<Worker> {
    /**
     * random worker id
     */
    long id;
    /**
     * random from controller
     */
    Random random;
    
    /**
     * constructor
     * @param random from controller
     */

    public Worker(Random random) {
        super();
        this.random = random;
        this.id = genID();
        this.firstName = createName(this.random);
        this.lastName = createName(this.random);
    }

    /**
     * compareTo method for 
     * comparing ID's
     * @param o worker objectbeing compared to
     * @return int compare result
     */

    @Override
    public int compareTo(Worker o) {
        return Long.compare(this.id, o.id);
    }
    
    /**
     * generate random 8 digit id
     * @return 8 digit id
     */

    public long genID() {
        String id = "";
        for (int i = 0; i < 8; i++) {
            id += this.random.nextInt(8) + 1;
        }
        return Long.parseLong(id);
    }
}
