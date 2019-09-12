package com.company;

public class Main {

    public static void main(String[] args) {
        ExperimentController experimentController = new ExperimentController();

        int items = 20000;
        int seed = 88087987;
        //experimentController.timeAddToFront(items, seed);
        //experimentController.timeAddToBack(items, seed);
        //experimentController.timePrependSorted(items, seed);
        //experimentController.timeAddSorted(items, seed);
        //experimentController.timeSortofUnsortedList(items, seed);
        experimentController.timeSortofSortedList(items, seed);
    }
}
