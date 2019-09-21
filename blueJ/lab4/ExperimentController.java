import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class ExperimentController {

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
        
        int numMethods = 8;

        //get amount of times to run each test && avg
        int runs = Integer.valueOf(args[1]);

        double dub = args.length - 2; //amount of different amounts
        long[] mainArr = new long[(int) (Math.pow(dub, dub) * numMethods)]; //get correct length for all the times

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
                timeToSelectionSortUnsorted += timeToSelectionSortUnsorted(amount, seed);
                timeToSelectionSortsorted += timeToSelectionSortsorted(amount, seed);
                timeToInsertionSortsorted += timeToInsertionSortsorted(amount, seed);
                timeToInsertionSortUnsorted += timeToInsertionSortUnsorted(amount, seed);
                timeToSearchLinearFound += timeToSearchLinearFound(amount, seed);
                timeToSearchLinearNotFound += timeToSearchLinearNotFound(amount, seed);
                timeToSearchBinaryFound += timeToSearchBinaryFound(amount, seed);
                timeToSearchBinaryNotFound += timeToSearchBinaryNotFound(amount, seed);
            }

            /**
             * fetch avg's over x runs and add them to the main data Array
             */
            
            /*
            mainArr[j*numMethods] = timeToSearchLinearFound / runs;
            mainArr[j*numMethods + 1] = timeToSearchLinearNotFound / runs;
            mainArr[j*numMethods + 2] = timeToSearchBinaryFound / runs;
            mainArr[j*numMethods + 3] = timeToSearchBinaryNotFound / runs;
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

    public static long timeToInsertionSortUnsorted(int numberOfItems, int seed) {
        /**
         * create string
         */
        RandomStringGenerator generator = new RandomStringGenerator(seed, numberOfItems);
        String[] string = generator.nextString().split(" ");
        StringContainer container = new StringContainer();
        for (int i = 0; i < string.length; i++) {
            container.addToList(Integer.valueOf(string[i]));
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

    public static long timeToInsertionSortsorted(int numberOfItems, int seed) {
        /**
         * create string
         */
        RandomStringGenerator generator = new RandomStringGenerator(seed, numberOfItems);
        String[] string = generator.nextString().split(" ");
        StringContainer container = new StringContainer();
        //add values to list
        for (int i = 0; i < string.length; i++) {
            container.addToList(Integer.valueOf(string[i]));
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

    public static long timeToSelectionSortUnsorted(int numberOfItems, int seed) {
        /**
         * create string
         */
        RandomStringGenerator generator = new RandomStringGenerator(seed, numberOfItems);
        String[] string = generator.nextString().split(" ");
        StringContainer container = new StringContainer();
        for (int i = 0; i < string.length; i++) {
            container.addToList(Integer.valueOf(string[i]));
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

    public static long timeToSelectionSortsorted(int numberOfItems, int seed) {
        /**
         * create string
         */
        RandomStringGenerator generator = new RandomStringGenerator(seed, numberOfItems);
        String[] string = generator.nextString().split(" ");
        StringContainer container = new StringContainer();
        for (int i = 0; i < string.length; i++) {
            container.addToList(Integer.valueOf(string[i]));
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

    public static long timeToSearchLinearFound(int numberOfItems, int seed) {
        RandomStringGenerator generator = new RandomStringGenerator(seed, numberOfItems);
        String[] string = generator.nextString().split(" ");
        StringContainer container = new StringContainer();
        Random random = new Random(seed);
        int randomInt = random.nextInt(numberOfItems);
        /**
         * set random integer that is IN the list to find
         */

        while (!Arrays.asList(string).contains(Integer.toString(randomInt))) {
            randomInt = random.nextInt(8999) + 1000; //loop until number is in list
        }


        for (int i = 0; i < string.length; i++) {
            container.addToList(Integer.valueOf(string[i]));
        }
        long startTime = System.currentTimeMillis();
        container.linear(randomInt);
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

    public static long timeToSearchLinearNotFound(int numberOfItems, int seed) {
        RandomStringGenerator generator = new RandomStringGenerator(seed, numberOfItems);
        String[] string = generator.nextString().split(" ");
        StringContainer container = new StringContainer();
        Random random = new Random(seed);
        int randomInt = random.nextInt(numberOfItems);

        /**
         * set random integer that is not IN the list to find
         */

        while (Arrays.asList(string).contains(Integer.toString(randomInt))) {
            randomInt = (random.nextInt(8999) + 1000); //make sure its not in the list

        }

        //System.out.println(Arrays.toString(string));

        for (int i = 0; i < string.length; i++) {
            container.addToList(Integer.valueOf(string[i]));
        }

        long startTime = System.currentTimeMillis();
        container.linear(randomInt);
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

    public static long timeToSearchBinaryFound(int numberOfItems, int seed) {
        RandomStringGenerator generator = new RandomStringGenerator(seed, numberOfItems);
        String[] string = generator.nextString().split(" ");
        StringContainer container = new StringContainer();
        Random random = new Random(seed);
        int randomInt = random.nextInt(8999) + 1000;

        while (!Arrays.asList(string).contains(Integer.toString(randomInt))) {
            randomInt = random.nextInt(8999) + 1000; //make sure its in the list before running the search
        }


        for (int i = 0; i < string.length; i++) {
            container.addToList(Integer.valueOf(string[i]));
        }
        container.selection();
        long startTime = System.currentTimeMillis();
        container.binary(randomInt);
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

    public static long timeToSearchBinaryNotFound(int numberOfItems, int seed) {
        RandomStringGenerator generator = new RandomStringGenerator(seed, numberOfItems);
        String[] string = generator.nextString().split(" ");
        StringContainer container = new StringContainer();
        Random random = new Random(seed);
        int randomInt = random.nextInt(8999) + 1000;

        while (Arrays.asList(string).contains(Integer.toString(randomInt))) {
            randomInt = random.nextInt(8999) + 1000; //make sure it's not in the list
        }


        for (int i = 0; i < string.length; i++) {
            container.addToList(Integer.valueOf(string[i]));
        }
        container.selection();
        long startTime = System.currentTimeMillis();
        container.binary(randomInt);
        long stopTime = System.currentTimeMillis();
        //System.out.println("Execute time (timeToSearchBinaryNotFound): " + (stopTime - startTime) + "ms");
        return stopTime - startTime;
    }

}
