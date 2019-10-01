import java.util.Random;

public class Worker extends Person implements Comparable<Worker> {
    long id;
    Random random;

    public Worker(long id) {
        this.random =  new Random();
        this.id = id;
        this.firstName = createName();
        this.lastName = createName();
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public int compareTo(Worker o) {
        return Long.compare(this.id, o.id);
    }
}
