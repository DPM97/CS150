import java.util.Random;

public class ExperimentController {
    Random random;
    public ExperimentController() {
        this.random = new Random();
    }

    public static void main(String[] args) {
        ExperimentController controller = new ExperimentController();

        WorkerList list = new WorkerList();
        list.addElement(new Worker());
        list.addElement(new Worker());
        list.addElement(new Worker());
        list.addElement(new Worker());
        list.addElement(new Worker());
        list.quickSort(0, list.list.size() - 1);
        list.printData();
    }


}
