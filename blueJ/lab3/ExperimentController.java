import java.util.Random;

/**
 * called from main --> main container that calls the stringList methods / returns value and time depending on which method is called;
 */

public class ExperimentController {
    /**
     * create a string array with the given elements
     */
    private static String[] twoLetterWords = {"aa", "ab", "ad", "ae", "ag", "ah", "ai", "al", "am", "an", "ar", "as", "at", "aw", "ax", "ay", "ba", "be", "bi", "bo", "by", "da", "de", "do", "ed", "ef", "eh", "el", "em", "en", "er", "es", "et", "ew", "ex", "fa", "fe", "gi", "go", "ha", "he", "hi", "hm", "ho", "id", "if", "in", "is", "it", "jo", "ka", "ki", "la", "li", "lo", "ma", "me", "mi", "mm", "mo", "mu", "my", "na", "ne", "no", "nu", "od", "oe", "of", "oh", "oi", "ok", "om", "on", "op", "or", "os", "ow", "ox", "oy", "pa", "pe", "pi", "po", "qi", "re", "sh", "si", "so", "ta", "te", "ti", "to", "uh", "um", "un", "up", "us", "ut", "we", "wo", "xi", "xu", "ya", "ye", "yo", "za"};
    public ExperimentController() { }

    /**
     * get the runtime for appending (numberOfItems) to the stringList for the given seed.
     * @param numberOfItems
     * @param seed
     */

    public void timeAddToBack(int numberOfItems, int seed) {
        Random random = new Random(seed);
        StringListADT list = new StringList();
        long startTime = System.currentTimeMillis();
        /**
         * appends (numberOfItems input) number of items to array
         */
        for (int i = 0; i < numberOfItems; i++) {
            list.append(twoLetterWords[random.nextInt(twoLetterWords.length)]);
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Execute time (timeAddToback): " + (stopTime - startTime) + "ms");
    }

    /**
     * get the runtime for returning the string value of the stringList
     * @param numberOfItems
     * @param seed
     */

    public void timeToString( int numberOfItems, int seed) {
        Random random = new Random(seed);
        StringListADT list = new StringList();
        /**
         * appends (numberOfItems input) number of items to array before toString();
         */
        for (int i = 0; i < numberOfItems; i++) {
            list.append(twoLetterWords[random.nextInt(twoLetterWords.length)]);
        }
        long startTime = System.currentTimeMillis();
        list.toString();
        long stopTime = System.currentTimeMillis();
        System.out.println("Execute time (toString): " + (stopTime - startTime) + "ms");
    }


}
