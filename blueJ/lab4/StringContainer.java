import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * creates list && contains methods to sort / search items;
 */

public class StringContainer {
    private ArrayList list;

    public StringContainer() {
        this.list = new ArrayList<String>();
    }

    /**
     * appends element to list
     *
     * @param integer
     */

    public void addToList(String string) {
        this.list.add(string);
    }

    /**
     * performs insertion sort on list
     */

    public String insertion() {
        for (int i = 0; i < this.list.size() - 1; i++) {
            for (int j = i; j >= 0; j--) {
                if (this.list.get(j).toString().compareToIgnoreCase(this.list.get(j + 1).toString()) > 0) {
                    //swap elements
                    String temp = this.list.get(j).toString();
                    this.list.set(j, this.list.get(j + 1).toString());
                    this.list.set(j + 1, temp);
                }
            }
        }
        return Arrays.toString(this.list.toArray()); //return sorted array
        //System.out.println(Arrays.toString(this.list.toArray()));
    }

    /**
     * performs selection sort on list
     */

    public String selection() {
        for (int i = 0; i < this.list.size() - 1; i++) {
            int min = i;
            for (int j = i; j < this.list.size(); j++) {
                if (this.list.get(min).toString().compareToIgnoreCase(this.list.get(j).toString()) > 0) {
                    //swap items
                    String temp = this.list.get(min).toString();
                    this.list.set(min, this.list.get(j).toString());
                    this.list.set(j, temp);
                }
            }
        }
        return Arrays.toString(this.list.toArray()); //return sorted array
        //System.out.println(Arrays.toString(this.list.toArray()));
    }

    /**
     * searches the list linearly
     *
     * @param integer
     */

    public int linear(String integer) {
        for (int i = 0; i < this.list.size() - 1; i++) {
            if (this.list.get(i).toString() == integer) {
                //if it finds item then return index
                return i;
            }
        }
        return -1;
    }

    /**
     * performs binary search on the list
     *
     * @param integer
     */

    public int binary(String integer) {
        int high = this.list.size() - 1;
        int low = 0;
        int middle = -1;
        while (low <= high) {
            middle = (low + high) / 2; //get new middle
            if (this.list.get(middle).toString() == integer) {
                //System.out.println("found" + integer + "at" + middle);
                return middle;
            } else if (integer.compareToIgnoreCase(this.list.get(middle).toString()) < 0) {
                //first half
                high = middle - 1; //high is middle (-1) not including old middle bc we know it cannot be the value
            } else {
                //second half
                low = middle + 1; //low is middle (+1) not including the old middle bc we know it cannot be the value
            }
        }
        if (this.list.get(middle).toString() == integer) {
            //System.out.println("found" + integer + "at" + middle);
            return middle;
        } else {
            //System.out.println("not found");
            return -1; //return -1 if not found
        }
    }



}



