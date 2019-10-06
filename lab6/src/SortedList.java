import java.util.ArrayList;

public abstract class SortedList <E extends Worker> {
    ArrayList<E> list;
    public SortedList() {
        this.list = new ArrayList<>();
    }

    public void addElement(E element) {
        this.list.add(element);
    }

    public void quickSort(int low, int high) {
        int j = 0;

        if (low >= high) {
            return;
        } else {
            j = partition(low, high);

            quickSort(low, j);
            quickSort(j+1, high);
        }
    }

    public int partition(int low, int high) {
        int small = 0;
        int large = 0;
        int mid = 0;
        E pivot;
        E temp;
        boolean finished = false;

        mid = low + (high - low) / 2;
        pivot = this.list.get(mid);

        small = low;
        large = high;

        while (!finished) {
            while(this.list.get(small).compareTo(pivot) < 0) {
                small++;
            }
            System.out.println(large);
            while(this.list.get(large).compareTo(pivot) > 0) {
                large++;
            }
            System.out.println(this.list);

            if (small >= large) {
                finished = true;
            } else {
                temp = this.list.get(small);
                this.list.set(small, this.list.get(large));
                this.list.set(large, temp);
                small++;
                large--;
            }
        }
        return large;
    }

    public <E> void mergeSort(int low, int high) {
        int j = 0;

        if (low < high) {
            j = (low + high) / 2;

            mergeSort(low, j);
            mergeSort(j + 1, high);

            //merge both sides
            merge(low, j, high);
        }
    }

    public void merge(int low, int j, int high) {
        int size = high - low + 1;
        int curPos = 0;
        int left = 0;
        int right = 0;
        System.out.println(high - low + 1);
        ArrayList<E> merged = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            merged.add(i, null);
        }
        System.out.println(merged);

        left = low;
        right = j + 1;

        while (left <= j && right <= high) {
            System.out.println(curPos);
            if (this.list.get(left).compareTo(this.list.get(right)) <= 0) {
                merged.set(curPos, this.list.get(left));
                left++;
            } else {
                merged.set(curPos, this.list.get(right));
                right++;
            }
            curPos++;
        }


        while (this.list.get(left).compareTo(this.list.get(j)) <= 0) {
            merged.set(curPos, this.list.get(left));
            left++;
            curPos++;
        }


        while (this.list.get(right).compareTo(this.list.get(high)) <= 0) {
            merged.set(curPos, this.list.get(right));
            right++;
            curPos++;
        }

        for (int i = 0; i < this.list.size(); i++) {
            this.list.set(i + low, merged.get(i));
        }
    }




    public abstract void printData();
}
