package com.company;
import java.util.ArrayList;

public class RandomStringContainer {
    private final ArrayList<String> arrayList;

    public RandomStringContainer() {
        this.arrayList = new ArrayList();
    }

    public ArrayList<String> addToFront(String string) {
        this.arrayList.add(0, string);
        return this.arrayList;
    }

    public ArrayList<String> addToBack(String string) {
        this.arrayList.add(string);
        return this.arrayList;
    }

    public ArrayList<String> addSorted(String string) {
        int index = search(this.arrayList, string, this.arrayList);
        this.arrayList.add(index, string);
        return this.arrayList;
    }

    public ArrayList<String> prependSorted(String string) {
        String initial = this.arrayList.get(0);
        initial = string += initial;
        int index = search(this.arrayList, initial, this.arrayList);
        this.arrayList.add(index, initial);
        this.arrayList.remove(0);
        return this.arrayList;
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
