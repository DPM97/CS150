

import java.io.FileWriter;
import java.io.IOException;

/**
 * logger class 
 * controls logging to CSV file and
 * CSV file creation
 */

public class Logger {
    /*** logger file FileWriter */
    private FileWriter logger;
    /*** type of sim */
    private final String type;
    /*** busyness var */
    private final int business;
    /*** amount of shifts */
    private final int shifts;
    /*** time in between shifts */
    private final int breakTime;
    /*** amount of cooks */
    private final int cooks;
    /*** amount of cashiers */
    private final int cashiers;
    /*** file directry */
    private final String dir;
    
    /**
     * constructor
     * @param dir log file directory
     * @param type type of sim
     * @param business busyness var
     * @param shifts amount of shifts
     * @param breakTime time in between shifts
     * @param cooks amount of cooks
     * @param cashiers amount of cashiers
     * @throws IOException catch fileIO error
     */

    Logger(String dir, String type, int business, int shifts, int breakTime, int cooks, int cashiers) throws IOException {
        this.dir = dir;
        this.type = type;
        this.business = business;
        this.shifts = shifts;
        this.breakTime = breakTime;
        this.cooks = cooks;
        this.cashiers = cashiers;
        this.logger = createLogger();
    }


    /**
     * creates CSV file given dir and type
     * adds all of the simulation input data
     * to the CSV file
     * @return fileWriter object
     * @throws IOException catch fileIO error
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
     * creates log file
     * @throws IOException catch fileIO error
     */
    
    public FileWriter createLogger() throws IOException {
        return new FileWriter(this.dir + this.type + "logs.txt");
    }
    
    /**
     * adds text to log file
     * @throws IOException catch fileIO error
     */
    
    public void log(int tick, String message) throws IOException {
        this.logger.append("Tick: " + tick + "      Action: " + message);
        this.logger.append('\n');
    }
    
    /**
     * cleans up after log file / closes it
     * @throws IOException catch fileIO error
     */
  
    public void closeLog() throws IOException {
        this.logger.close();
    }

    /**
     * adds the simulation output
     * data correct to the CSV file
     * @param file fileWriter object from earlier
     * @param ordersFilled amount of orders filled
     * @param satisfaction avg. satisfaction %
     * @param waitTime avg. waitTime in line
     * @param waitTimeWithCook avg. waitTime with cookTime
     * @param price total $ in food sold
     * @throws IOException catch fileIO error
     */

    public void logData(FileWriter file, int ordersFilled, float satisfaction, float waitTime, float waitTimeWithCook, int price, int wages) throws IOException {
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
        file.append("Avg. Wait for Food: " + waitTimeWithCook + " minutes");
        file.append('\n');
        file.append("Amount of Food Sold: $" + price);
        file.append('\n');
        file.append("Amount of profit: $" + wages);
        file.close();
    }
}
