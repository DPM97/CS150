import java.util.Random;

public class Worker extends Person implements Comparable<Worker> {
    long id;
    Random random;

    public Worker(Random random) {
        super();
        this.random = random;
        this.id = genID();
        this.firstName = createName(this.random);
        this.lastName = createName(this.random);
    }


    @Override
    public int compareTo(Worker o) {
        return Long.compare(this.id, o.id);
    }

    public long genID() {
        String id = "";
        for (int i = 0; i < 8; i++) {
            id += this.random.nextInt(8) + 1;
        }
        return Long.parseLong(id);
    }
}
