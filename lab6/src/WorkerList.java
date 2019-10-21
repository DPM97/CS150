import java.util.Random;

public class WorkerList extends SortedList <Worker> {
    public Random random;


    public WorkerList(Random random) {
        this.random = random;
    }

    public void add() {
        this.list.add(new Worker(this.random));
    }

    @Override
    public void printData() {
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(this.list.get(i).id + " " + this.list.get(i).lastName + " " + this.list.get(i).firstName);
        }
    }
}
