import java.util.ArrayList;
import java.util.Arrays;

public class RandomIntegerContainer {
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
        return intArr;
    }

}
