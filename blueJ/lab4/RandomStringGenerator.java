import java.util.Random;

/**
 * generates random string of numbers in between 1000 and 9999
 */

public class RandomStringGenerator {
    private int seed;
    private int length;

    /**
     * takes in seed and length to make string
     * @param seed
     * @param length
     */

    public RandomStringGenerator(int seed, int length) {
        this.length = length;
        this.seed = seed;
    }


    /**
     * creates random string given info and returns it
     * @return
     */

    public String nextString() {
        String string = "";
        Random random = new Random(this.seed);
        for (int i = 0; i < this.length; i++) {
            string += Integer.toString(random.nextInt(8999) + 1000) + " "; //adds random element to string
        }
        return string;
    }
}
