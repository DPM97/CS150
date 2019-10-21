import java.util.Random;

/**
 * worker list class
 * extends sortedList (worker type)
 */

public class WorkerList extends SortedList <Worker> {
    
    /**
     * random from controller
     */
    
    public Random random;
    public WorkerList(Random random) {
        this.random = random;
    }

    
    /**
     * add random worker to 
     * worker list
     */
    
    public void add() {
        this.list.add(new Worker(this.random));
    }
    
    /**
     * print list contents 
     * @return string for testing purposes
     */

    @Override
    public String printData() {
        String string = "";
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("ID: " + this.list.get(i).id + 
            "\nLast Name: " + this.list.get(i).lastName + 
            "\nFirst Name: " + this.list.get(i).firstName + "\n"
            );
            string += this.list.get(i).id + " ";
        }
        return string;
    }
}
