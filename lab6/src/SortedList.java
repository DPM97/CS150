import java.util.ArrayList;

public abstract class SortedList<E extends Comparable<E>>  {
    ArrayList<E> list;
    public SortedList() {
        this.list = new ArrayList<>();
    }

    public void addElement(E element) {
        this.list.add(element);
    }

    public void quickSort(int low, int high) {
        if (low < high) {
            int j = partition(low, high);

            quickSort(low, j);
            quickSort(j+1, high);
        }
    }

    public int partition(int low, int high) {
        int l = 0;
        int h = 0;
        int mid = 0;
        E pivot;
        E temp;
        boolean finished = false;

        mid = low + (high - low) / 2;
        pivot = this.list.get(mid);

        l = low;
        h = high;

        while (!finished) {

            while(this.list.get(l).compareTo(pivot) < 0) {
                l++;
            }
            while(this.list.get(h).compareTo(pivot) > 0) {
                h--;
            }

            if (l >= h) {
                finished = true;
            } else {
                temp = this.list.get(l);
                this.list.set(l, this.list.get(h));
                this.list.set(h, temp);
                l++;
                h--;
            }
        }
        return h;
    }

    public <E> void mergeSort(int low, int high) {

        if (low < high) {
            int j = (low + high) / 2;

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
        ArrayList<E> merged = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            merged.add(i, null);
        }

        left = low;
        right = j + 1;

        while (left <= j && right <= high) {
            if (this.list.get(left).compareTo(this.list.get(right)) <= 0) {
                merged.set(curPos, this.list.get(left));
                left++;
            } else {
                merged.set(curPos, this.list.get(right));
                right++;
            }
            curPos++;
        }


        while (left <= j) {
            merged.set(curPos, this.list.get(left));
            left++;
            curPos++;
        }


        while (right <= high) {
            merged.set(curPos, this.list.get(right));
            right++;
            curPos++;
        }

        for (int i = 0; i < merged.size(); i++) {
            this.list.set(low + i, merged.get(i));
        }
    }




    public abstract void printData();
}
