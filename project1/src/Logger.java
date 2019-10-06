import java.io.FileWriter;
import java.io.IOException;

/**
 * logger class
 * controls logging to CSV file and
 * CSV file creation
 */

public class Logger {

    private final String type;
    private final int business;
    private final int shifts;
    private final int breakTime;
    private final int cooks;
    private final int cashiers;
    private final String dir;
    //private final int test;

    Logger(String dir, String type, int business, int shifts, int breakTime, int cooks, int cashiers) {
        this.dir = dir;
        this.type = type;
        this.business = business;
        this.shifts = shifts;
        this.breakTime = breakTime;
        this.cooks = cooks;
        this.cashiers = cashiers;
    }

    //public String type;


    /**
     * creates CSV file given dir and type
     * adds all of the simulation input data
     * to the CSV file
     * @return
     * @throws IOException
     */


    public FileWriter createCSV() throws IOException {
        FileWriter file = new FileWriter(this.dir + this.type + ".csv");
        file.append(this.type.toUpperCase() + " Simulation Output:");
        file.append('\n');
        file.append('\n');
        file.append("::::::SIMULATION SETTINGS::::::");
        file.append('\n');
        file.append("Store Type: " + this.type);
        file.append('\n');
        file.append("Busyness: " + this.business);
        file.append('\n');
        file.append("Shifts per day: " + this.shifts);
        file.append('\n');
        file.append("Time in between shifts: " + this.breakTime);
        file.append('\n');
        file.append("Number of Cooks: " + this.cooks);
        file.append('\n');
        file.append("Number of Cashiers: " + this.cashiers);
        return file;
    }

    /**
     * adds the simulation output
     * data correct to the CSV file
     * @param file
     * @param ordersFilled
     * @param satisfaction
     * @param waitTime
     * @param waitTimeWithCook
     * @param price
     * @throws IOException
     */

    public void logData(FileWriter file, int ordersFilled, float satisfaction, float waitTime, float waitTimeWithCook, int price) throws IOException {
        file.append("\n");
        file.append('\n');
        file.append("::::::SIMULATION OUTPUT DATA::::::");
        file.append('\n');
        file.append("Orders Filled: " + ordersFilled + " orders");
        file.append('\n');
        file.append("Avg. Percent Satisfaction: " + satisfaction * 100 + "%");
        file.append('\n');
        file.append("Avg. Wait in Line: " + waitTime + " minutes");
        file.append('\n');
        file.append("Avg. Wait from entering to receiving order: " + waitTimeWithCook + " minutes");
        file.append('\n');
        file.append("Amount of Food Sold: $" + price);
        file.close();
    }
}
