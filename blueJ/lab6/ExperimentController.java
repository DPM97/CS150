import java.util.Random;

/**
 * experiment controller class
 * main class that "tests" other classes
 */

public class ExperimentController {
    //random seed so all tests use same data set
    public int seed;
    
    public ExperimentController() {
        this.seed = 90239024;
    }

    public static void main(String[] args) {
        ExperimentController controller = new ExperimentController();
        controller.test(100000);
    }
    
    /**
     * test sort methods
     * @param size amount of items
     */

    public void test(int size) {
        int quickavg = 0;
        int mergeavg = 0;
        int avgquickWorker = 0;
        int avgmergeWorker = 0;
        for (int i = 0; i < 20; i++) { //average run times over 20 runs
            quickavg += quick(size);        
            mergeavg += merge(size);
            avgquickWorker += quickWorker(size);        
            avgmergeWorker += mergeWorker(size);
        }
        System.out.println("Time to quickSort contacts size=" + size + ": " + (quickavg / 20) + "ms");
        System.out.println("Time to mergeSort contacts size=" + size + ": " + (mergeavg / 20) + "ms");
        System.out.println("Time to quickSort workers size=" + size + ": " + (avgquickWorker / 20) + "ms");
        System.out.println("Time to mergeSort workers size=" + size + ": " + (avgmergeWorker / 20) + "ms");


    }
    
    /**
     * test merge sort for Contacts
     * @param size amount of elements
     * @return time
     */

    public long merge(int size) {
        ContactList merge = new ContactList(new Random(this.seed));
        for (int i = 0; i < size; i++) {
            merge.add();
        }
        
        long start = System.currentTimeMillis();
        merge.mergeSort(0, merge.list.size() - 1);
        long end = System.currentTimeMillis();
        return (end - start);
        //System.out.println("Time to mergeSort contacts size=" + size + ": " + (end - start) + "ms");

    }
    
    /**
     * test quick sort for Contacts
     * @param size amount of elements
     * @return time
     */

    public long quick(int size) {
        ContactList quick = new ContactList(new Random(this.seed));
        for (int i = 0; i < size; i++) {
            quick.add();
        }
        
        long start = System.currentTimeMillis();
        quick.quickSort(0, quick.list.size() - 1);
        long end = System.currentTimeMillis();
        return (end - start);
        //System.out.println("Time to quickSort contacts size=" + size + ": " + (end - start) + "ms");
    }
    
    /**
     * test quick sort for Workers
     * @param size amount of elements
     * @return time
     */

    public long quickWorker(int size) {
        WorkerList quick = new WorkerList(new Random(this.seed));
        for (int i = 0; i < size; i++) {
            quick.add();
        }
        
        long start = System.currentTimeMillis();
        quick.quickSort(0, quick.list.size() - 1);
        long end = System.currentTimeMillis();
        return (end - start);
        //System.out.println("Time to quickSort workers size=" + size + ": " + (end - start) + "ms");
    }
    
    /**
     * test merge sort for workers
     * @param size amount of elements
     * @return time
     */

    public long mergeWorker(int size) {
        WorkerList merge = new WorkerList(new Random(this.seed));
        for (int i = 0; i < size; i++) {
            merge.add();
        }
        long start = System.currentTimeMillis();
        merge.mergeSort(0, merge.list.size() - 1);
        long end = System.currentTimeMillis();
        return (end - start);
        //System.out.println("Time to mergeSort workers size=" + size + ": " + (end - start) + "ms");
    }




}
