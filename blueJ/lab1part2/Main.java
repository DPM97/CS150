 
import java.util.*;

public class Main {

    public static void main(String[] args) {
	    RandomStringContainer stringContainer = new RandomStringContainer();
        RandomIntegerContainer integerContainer = new RandomIntegerContainer();
        ExperimentController experimentController = new ExperimentController();

        experimentController.timeAddToFront(2000, 88087987);
        experimentController.timeAddToBack(2000, 88087987);
        experimentController.timePrependSorted(2000, 88087987);
        experimentController.timeAddSorted(2000, 88087987);
        experimentController.timeSortofUnsortedList(2000, 88087987);
        experimentController.timeSortofSortedList(2000, 88087987);

    }

    public static class RandomStringContainer {
        private final ArrayList<String> arrayList;

        public RandomStringContainer() {
            this.arrayList = new ArrayList();
        }

        public void addToFront(String string) {
            this.arrayList.add(0, string);
        }

        public void addToBack(String string) {
            this.arrayList.add(string);
        }

        public void addSorted(String string) {
            int index = search(this.arrayList, string, this.arrayList);
            this.arrayList.add(index, string);
        }

        public void prependSorted(String string) {
            String initial = this.arrayList.get(0);
            initial = string += initial;
            int index = search(this.arrayList, initial, this.arrayList);
            this.arrayList.add(index, initial);
        }

        /**
         * binary search method to fetch the correct index to insert the given element & keep sorted
         * @param arrayList
         * @param string
         * @param ogList
         * @return
         */

        public int search(ArrayList<String> arrayList, String string, ArrayList<String> ogList) {
            if (arrayList.toArray().length == 2) {
                if (string.compareToIgnoreCase(arrayList.get(1)) > 0) {
                    return ogList.indexOf(arrayList.get(1)) + 1;
                } else if (string.compareToIgnoreCase(arrayList.get(0)) > 0) {
                    return ogList.indexOf(arrayList.get(0)) + 1;
                } else {
                    return ogList.indexOf(arrayList.get(0));
                }
                //return 0;
            }
            for (int i = 0; i < arrayList.toArray().length; i++) {
                int index = arrayList.toArray().length/2;
                String half = arrayList.get(index);
                if (half == string) {
                    return this.arrayList.indexOf(half);
                } else if (half.compareToIgnoreCase(string) > 0) {
                    if (arrayList.toArray().length == 3) {
                        ArrayList<String> arrayList2 = new ArrayList<String> (arrayList.subList(0, 2));
                        return search(arrayList2, string, ogList);
                    }
                    ArrayList<String> arrayList2 = new ArrayList<String> (arrayList.subList(0, arrayList.toArray().length/2));
                    return search(arrayList2, string, ogList);
                } else {
                    if (arrayList.toArray().length == 3) {
                        ArrayList<String> arrayList2 = new ArrayList<String> (arrayList.subList(1, 3));
                        return search(arrayList2, string, ogList);
                    }
                    ArrayList<String> arrayList2 = new ArrayList<String> (arrayList.subList(arrayList.toArray().length/2, arrayList.toArray().length));
                    return search(arrayList2, string, ogList);
                }
            }
            return -1;
        }

        /**
         * selection sort algorithm to sort the array
         * @return
         */

        public ArrayList<String> selectionSort() {
            for (int i = 0; i < this.arrayList.size(); i++) {
                int currentSmallestIndex = i; //index out of the smallest in the unsorted bunch
                for (int j = i; j < this.arrayList.size(); j++) {
                    if (this.arrayList.get(j).compareToIgnoreCase(this.arrayList.get(currentSmallestIndex)) < 0) {
                        currentSmallestIndex = j;
                    }
                }
                String temp = this.arrayList.get(i);
                this.arrayList.set(i, this.arrayList.get(currentSmallestIndex));
                this.arrayList.set(currentSmallestIndex, temp);
            }
            return this.arrayList;
        }

    }


    public static class RandomIntegerContainer {
        private final ArrayList<Integer> arrayList;

        public RandomIntegerContainer() {
            this.arrayList = new ArrayList();
        }

        public int[] addToFront(int integer) {

            this.arrayList.add(0, integer);

            int[] intArr = new int[this.arrayList.size()];
            for (int i = 0; i < intArr.length; i++) {
                intArr[i] = this.arrayList.get(i);
            }
            System.out.println(Arrays.toString(intArr));
            return intArr;
        }

    }

    public static class ExperimentController {
        RandomStringContainer stringContainer = new RandomStringContainer();
        private static String[] twoLetterWords = {"aa", "ab", "ad", "ae", "ag", "ah", "ai", "al", "am", "an", "ar", "as", "at", "aw", "ax", "ay", "ba", "be", "bi", "bo", "by", "da", "de", "do", "ed", "ef", "eh", "el", "em", "en", "er", "es", "et", "ew", "ex", "fa", "fe", "gi", "go", "ha", "he", "hi", "hm", "ho", "id", "if", "in", "is", "it", "jo", "ka", "ki", "la", "li", "lo", "ma", "me", "mi", "mm", "mo", "mu", "my", "na", "ne", "no", "nu", "od", "oe", "of", "oh", "oi", "ok", "om", "on", "op", "or", "os", "ow", "ox", "oy", "pa", "pe", "pi", "po", "qi", "re", "sh", "si", "so", "ta", "te", "ti", "to", "uh", "um", "un", "up", "us", "ut", "we", "wo", "xi", "xu", "ya", "ye", "yo", "za"};


        public void timeAddToFront (int numberOfItems, int seed) {
            Random random = new Random(seed);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < numberOfItems; i++) {
                stringContainer.addToFront(twoLetterWords[random.nextInt(twoLetterWords.length)]);
            }
            long stopTime = System.currentTimeMillis();
            System.out.println("Execute time (timeAddToFront): " + (stopTime - startTime) + "ms");
        }

        public void timeAddToBack (int numberOfItems, int seed) {
            Random random = new Random(seed);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < numberOfItems; i++) {
                stringContainer.addToBack(twoLetterWords[random.nextInt(twoLetterWords.length)]);
            }
            long stopTime = System.currentTimeMillis();
            System.out.println("Execute time (timeAddToback): " + (stopTime - startTime) + "ms");
        }

        public void timeAddSorted (int numberOfItems, int seed) {
            Random random = new Random(seed);
            for (int i = 0; i < numberOfItems; i++) {
                //populate array before calling
                stringContainer.addToFront(twoLetterWords[random.nextInt(twoLetterWords.length)]);
            }

            /**
             * sort array
             */
            stringContainer.selectionSort();

            long startTime = System.currentTimeMillis();
            stringContainer.addSorted(twoLetterWords[random.nextInt(twoLetterWords.length)]);
            long stopTime = System.currentTimeMillis();
            System.out.println("Execute time (timeAddSorted): " + (stopTime - startTime) + "ms");
        }

        public void timePrependSorted (int numberOfItems, int seed) {
            Random random = new Random(seed);
            for (int i = 0; i < numberOfItems; i++) {
                //populate array before calling
                stringContainer.addToFront(twoLetterWords[random.nextInt(twoLetterWords.length)]);
            }
            /**
             * sort array
             */
            stringContainer.selectionSort();

            long startTime = System.currentTimeMillis();
            stringContainer.prependSorted((twoLetterWords[random.nextInt(twoLetterWords.length)]));
            long stopTime = System.currentTimeMillis();
            System.out.println("Execute time (timePrependSorted): " + (stopTime - startTime) + "ms");
        }

        public void timeSortofUnsortedList (int numberOfItems, int seed) {
            Random random = new Random(seed);
            for (int i = 0; i < numberOfItems; i++) {
                stringContainer.addToBack(twoLetterWords[random.nextInt(twoLetterWords.length)]);
            }
            long startTime = System.currentTimeMillis();
            stringContainer.selectionSort();
            /*
            * sort element using selection sort
            * */
            long stopTime = System.currentTimeMillis();
            System.out.println("Execute time (timeSortofUnsortedList): " + (stopTime - startTime) + "ms");
        }

        public void timeSortofSortedList (int numberOfItems, int seed) {
            Random random = new Random(seed);
            for (int i = 0; i < numberOfItems; i++) {
                stringContainer.addToBack(twoLetterWords[random.nextInt(twoLetterWords.length)]);
            }
            stringContainer.selectionSort();
            /*
             * sort element using selection sort & start time
             *
             */
            long startTime = System.currentTimeMillis();
            stringContainer.selectionSort();
            /*
             * sort element again (should be quicker)
             * */
            long stopTime = System.currentTimeMillis();
            System.out.println("Execute time (timeSortofSortedList): " + (stopTime - startTime) + "ms");
        }
    }
}
