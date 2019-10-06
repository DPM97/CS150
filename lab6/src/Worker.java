import java.util.Random;

public class Worker extends Person implements Comparable<Worker> {
    long id;
    Random random;

    public Worker() {
        this.random =  new Random();
        this.id = genID();
        this.firstName = "dfljslj";
        this.lastName = "fkjdksfj";
    }

    @Override
    public int compareTo(Worker o) {
        return Long.compare(this.id, o.id);
    }

    public long genID() {
        String id = "";
        for (int i = 0; i < 8; i++) {
            id += this.random.nextInt(9);
        }
        return Long.parseLong(id);
    }
}
