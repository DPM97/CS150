public class WorkerList extends SortedList <Worker> {
    public WorkerList() {

    }

    @Override
    public void printData() {
        System.out.println("called");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(this.list.get(i).firstName + " " + this.list.get(i).lastName);
        }
    }
}
