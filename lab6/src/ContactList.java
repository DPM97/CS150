import java.util.Random;

public class ContactList extends SortedList <Contact> {
    public Random random;
    public ContactList(Random random) {
        this.random = random;
    }

    public void add() {
        this.list.add(new Contact(this.random));
    }
    @Override
    public void printData() {
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(this.list.get(i).number + " " + this.list.get(i).lastName + " " + this.list.get(i).firstName);
        }
    }
}
