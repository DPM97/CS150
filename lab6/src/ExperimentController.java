import java.util.Random;

public class ExperimentController {
    public int seed;
    public ExperimentController() {
        this.seed = 1303;
    }


    public static void main(String[] args) {
        ExperimentController controller = new ExperimentController();
        controller.test(1000000);

    }

    public void test(int size) {
        ContactList merge = new ContactList(new Random(this.seed));
        for (int i = 0; i < size; i++) {
            merge.add();
        }
        merge(merge);

        ContactList quick = new ContactList(new Random(this.seed));
        for (int i = 0; i < size; i++) {
            quick.add();
        }
        quick(quick);

        WorkerList mergeWorker = new WorkerList(new Random(this.seed));
        for (int i = 0; i < size; i++) {
            mergeWorker.add();
        }
        merge(mergeWorker);

        WorkerList quickWorker = new WorkerList(new Random(this.seed));
        for (int i = 0; i < size; i++) {
            quickWorker.add();
        }
        quick(quickWorker);
    }

    public void merge(ContactList merge) {
        long start = System.currentTimeMillis();
        merge.mergeSort(0, merge.list.size() - 1);
        long end = System.currentTimeMillis();
        System.out.println("Time to mergeSort contacts: " + (end - start) + "ms");
    }

    public void quick(ContactList quick) {
        long start = System.currentTimeMillis();
        quick.quickSort(0, quick.list.size() - 1);
        long end = System.currentTimeMillis();
        System.out.println("Time to quickSort contacts: " + (end - start) + "ms");
    }

    public void quick(WorkerList quick) {
        long start = System.currentTimeMillis();
        quick.quickSort(0, quick.list.size() - 1);
        long end = System.currentTimeMillis();
        System.out.println("Time to quickSort workers: " + (end - start) + "ms");
    }

    public void merge(WorkerList merge) {
        long start = System.currentTimeMillis();
        merge.mergeSort(0, merge.list.size() - 1);
        long end = System.currentTimeMillis();
        System.out.println("Time to mergeSort workers: " + (end - start) + "ms");
    }




}
