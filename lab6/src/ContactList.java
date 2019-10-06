public class ContactList extends SortedList {
    public ContactList() {
        super();

    }

    @Override
    public void printData() {
        for (int i = 0; i < this.list.size(); i++) {
            //System.out.println(this.list.get(i).number + " " + this.list.get(i).firstName + " " + this.list.get(i).lastName);
        }
    }
}
