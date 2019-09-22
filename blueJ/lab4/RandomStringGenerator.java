import java.util.Random;

/**
 * generates random string of numbers in between 1000 and 9999
 */

public class RandomStringGenerator {
    private int seed;
    private int length;
    private int high;
    private int low;

    /**
     * takes in seed and length to make string
     * @param seed
     * @param length
     */

    public RandomStringGenerator(int seed, int length) {
        this.length = length;
        this.seed = seed;
        this.high = (int) (Math.pow(10, length) - Math.pow(10, length - 1) - 1);
        this.low = (int) Math.pow(10, length - 1);
    }


    /**
     * creates random string of an integer given info and returns it
     * @return
     */

    public String nextString() {
        Random random = new Random(this.seed);
        String string = Integer.toString(random.nextInt(this.high) + this.low); 
        return string;
    }
}
