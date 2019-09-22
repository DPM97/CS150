import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class ExperimentController {
    private int strLen;
    ExperimentController() {
        this.strLen = 4;
    }

    public static void main(String[] args) throws IOException {
        int seed = 1004023;
        String csvFileName = args[0];
        FileWriter file = new FileWriter(csvFileName);
        /**
         * formulate csv header row
         */
        file.append("Method");
        for (int f = 2; f < args.length; f++) {
            file.append(',');
            file.append("Items: " +  args[f]);
        }
        file.append('\n');
        
        int numMethods = 8; //amount of method names for formatting sake
        //get amount of times to run each test && avg
        int runs = Integer.valueOf(args[1]);

        double dub = args.length - 2; //amount of different amounts
        long[] mainArr = new long[(int) (Math.pow(dub, dub) * numMethods)]; //get correct length for all the times
        ExperimentController controller = new ExperimentController(); //create a new controller so we can call non-static methods from main
        for (int j = 0; j < args.length - 2; j++) {
            long timeToSelectionSortUnsorted = 0;
            long timeToSelectionSortsorted = 0;
            long timeToInsertionSortsorted = 0;
            long timeToInsertionSortUnsorted = 0;
            long timeToSearchLinearFound = 0;
            long timeToSearchLinearNotFound = 0;
            long timeToSearchBinaryFound = 0;
            long timeToSearchBinaryNotFound = 0;
            int amount = Integer.valueOf(args[2 + j]);
            for (int i = 0; i < runs; i++) {
                /**
                 * get times (add all runs together for avg)
                 */
                timeToSelectionSortUnsorted += controller.timeToSelectionSortUnsorted(amount, seed);
                timeToSelectionSortsorted += controller.timeToSelectionSortsorted(amount, seed);
                timeToInsertionSortsorted += controller.timeToInsertionSortsorted(amount, seed);
                timeToInsertionSortUnsorted += controller.timeToInsertionSortUnsorted(amount, seed);
                timeToSearchLinearFound += controller.timeToSearchLinearFound(amount, seed);
                timeToSearchLinearNotFound += controller.timeToSearchLinearNotFound(amount, seed);
                timeToSearchBinaryFound += controller.timeToSearchBinaryFound(amount, seed);
                timeToSearchBinaryNotFound += controller.timeToSearchBinaryNotFound(amount, seed);
            }

            /**
             * fetch avg's over x runs and add them to the main data Array
             */
            
            mainArr[j*numMethods] = timeToSelectionSortUnsorted / runs;
            mainArr[j*numMethods + 1] = timeToSelectionSortsorted / runs;
            mainArr[j*numMethods + 2] = timeToInsertionSortsorted / runs;
            mainArr[j*numMethods + 3] = timeToInsertionSortUnsorted / runs;
            mainArr[j*numMethods + 4] = timeToSearchLinearFound / runs;
            mainArr[j*numMethods + 5] = timeToSearchLinearNotFound / runs;
            mainArr[j*numMethods + 6] = timeToSearchBinaryFound / runs;
            mainArr[j*numMethods + 7] = timeToSearchBinaryNotFound / runs;
            
        }

        /**
         * string list of methodnames for loop
         */

        String[] methodNames = {
                "timeToSelectionSortUnsorted",
                "timeToSelectionSortsorted",
                "timeToInsertionSortsorted",
                "timeToInsertionSortUnsorted",
                "timeToSearchLinearFound",
                "timeToSearchLinearNotFound",
                "timeToSearchBinaryFound",
                "timeToSearchBinaryNotFound"
        };

        /**
         * add methods & times to csv in order
         */

        for (int i = 0; i < numMethods; i++) {
            file.append(methodNames[i]);
            for (int j = 0; j < args.length - 2; j++) {
                file.append(',');
                file.append(Long.toString(mainArr[j*8 + i]) + "ms");
            }
            file.append("\n");
        }
        file.close();
    }

    /**
     * return time to sort unsorted list using insertion sort
     * @param numberOfItems
     * @param seed
     * @return
     */

    public long timeToInsertionSortUnsorted(int numberOfItems, int seed) {
        /**
         * create string
         */
        RandomStringGenerator generator = new RandomStringGenerator(seed, this.strLen);
        StringContainer container = new StringContainer();
        for (int i = 0; i < numberOfItems; i++) {
            container.addToList(generator.nextString());
        }
        long startTime = System.currentTimeMillis();
        container.insertion();
        long stopTime = System.currentTimeMillis();
        //System.out.println("Execute time (timeToInsertionSortUnsorted): " + (stopTime - startTime) + "ms");
        return stopTime - startTime;
    }

    /**
     * return time to sort sorted list using insertion sort
     * @param numberOfItems
     * @param seed
     * @return
     */

    public long timeToInsertionSortsorted(int numberOfItems, int seed) {
        /**
         * create string
         */
        RandomStringGenerator generator = new RandomStringGenerator(seed, this.strLen);
        StringContainer container = new StringContainer();
        for (int i = 0; i < numberOfItems; i++) {
            container.addToList(generator.nextString());
        }
        container.insertion(); //sort

        long startTime = System.currentTimeMillis();
        container.insertion();
        long stopTime = System.currentTimeMillis();
        //System.out.println("Execute time (timeToInsertionSortsorted): " + (stopTime - startTime) + "ms");
        return stopTime - startTime;
    }

    /**
     * return time to sort unsorted list using selection sort
     * @param numberOfItems
     * @param seed
     * @return
     */

    public long timeToSelectionSortUnsorted(int numberOfItems, int seed) {
        /**
         * create string
         */
        RandomStringGenerator generator = new RandomStringGenerator(seed, this.strLen);
        StringContainer container = new StringContainer();
        for (int i = 0; i < numberOfItems; i++) {
            container.addToList(generator.nextString());
        }
        long startTime = System.currentTimeMillis();
        container.selection();
        long stopTime = System.currentTimeMillis();
        //System.out.println("Execute time (timeToSelectionSortUnsorted): " + (stopTime - startTime) + "ms");
        return stopTime - startTime;
    }

    /**
     * return time to sort sorted list using selection sort
     * @param numberOfItems
     * @param seed
     * @return
     */

    public long timeToSelectionSortsorted(int numberOfItems, int seed) {
        /**
         * create string
         */
        RandomStringGenerator generator = new RandomStringGenerator(seed, this.strLen);
        StringContainer container = new StringContainer();
        for (int i = 0; i < numberOfItems; i++) {
            container.addToList(generator.nextString());
        }
        container.selection(); //sort list first time
        long startTime = System.currentTimeMillis();
        container.selection();
        long stopTime = System.currentTimeMillis();
        //System.out.println("Execute time (timeToSelectionSortsorted): " + (stopTime - startTime) + "ms");
        return stopTime - startTime;
    }

    /**
     * get time to search list linearly for a number (which is in the list)
     * @param numberOfItems
     * @param seed
     * @return
     */

    public long timeToSearchLinearFound(int numberOfItems, int seed) {
        RandomStringGenerator generator = new RandomStringGenerator(seed, this.strLen);
        StringContainer container = new StringContainer();
        Random random = new Random(seed);
        int randomInt = random.nextInt(10);
        /**
         * set random integer that is IN the list to find
         */



        for (int i = 0; i < numberOfItems; i++) {
            int integer = Integer.parseInt(generator.nextString());
            if (i == numberOfItems / randomInt) {
                randomInt = integer;
            }
            container.addToList(generator.nextString());
        }
        
        
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            container.linear(Integer.toString(randomInt));
        }
        long stopTime = System.currentTimeMillis();
        //System.out.println("Execute time (timeToSearchLinearFound): " + (stopTime - startTime) + "ms");
        return stopTime - startTime;
    }

    /**
     * get time to search list linearly for a number (which isn't in the list)
     * @param numberOfItems
     * @param seed
     * @return
     */

    public long timeToSearchLinearNotFound(int numberOfItems, int seed) {
        RandomStringGenerator generator = new RandomStringGenerator(seed, this.strLen);
        StringContainer container = new StringContainer();
        Random random = new Random(seed);
        int randomInt = random.nextInt(numberOfItems);

        /**
         * set random integer that is not IN the list to find
         */


        //System.out.println(Arrays.toString(string));

        for (int i = 0; i < numberOfItems; i++) {
            container.addToList(generator.nextString());
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            container.linear(Integer.toString((int)Math.pow(10, this.strLen)));
        }
        long stopTime = System.currentTimeMillis();
        //System.out.println("Execute time (timeToSearchLinearNotFound): " + (stopTime - startTime) + "ms");
        return stopTime - startTime;
    }

    /**
     * get time to search list using binary search for int (which is in the list)
     * @param numberOfItems
     * @param seed
     * @return
     */

    public long timeToSearchBinaryFound(int numberOfItems, int seed) {
        RandomStringGenerator generator = new RandomStringGenerator(seed, this.strLen);
        String string = generator.nextString();
        StringContainer container = new StringContainer();
        Random random = new Random(seed);
        int randomInt = random.nextInt(10);
        /**
         * set random integer that is IN the list to find
         */

        for (int i = 0; i < numberOfItems; i++) {
            int integer = Integer.parseInt(generator.nextString());
            if (i == numberOfItems / randomInt) {
                randomInt = integer;
            }
            container.addToList(generator.nextString());
        }
        container.selection();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            container.binary(Integer.toString(randomInt));
        }
        long stopTime = System.currentTimeMillis();
        //System.out.println("Execute time (timeToSearchBinaryFound): " + (stopTime - startTime) + "ms");
        return stopTime - startTime;
    }

    /**
     * get time to search list using binary search for int (which isn't in the list)
     * @param numberOfItems
     * @param seed
     * @return
     */

    public long timeToSearchBinaryNotFound(int numberOfItems, int seed) {
        RandomStringGenerator generator = new RandomStringGenerator(seed, this.strLen);
        for (int i = 0; i < numberOfItems; i++) {
            generator.nextString();
        }
        StringContainer container = new StringContainer();

        for (int i = 0; i < numberOfItems; i++) {
            container.addToList(generator.nextString());
        }
        container.selection();
        long startTime = System.currentTimeMillis();
            for (int i = 0; i < 5000; i++) {
              container.binary(Integer.toString((int)Math.pow(10, this.strLen)));
            }
        long stopTime = System.currentTimeMillis();
        //System.out.println("Execute time (timeToSearchBinaryNotFound): " + (stopTime - startTime) + "ms");
        return stopTime - startTime;
    }

}
