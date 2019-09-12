package com.company;

import java.util.Random;

public class ExperimentController {
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
