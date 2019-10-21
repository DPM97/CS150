import java.util.Random;

/**
 * contactList class
 * extends sortedList (contact type)
 */

public class ContactList extends SortedList <Contact> {
    /**
     * random from controller
     */
    public Random random;
    public ContactList(Random random) {
        this.random = random;
    }
    
    /**
     * add random contact
     * to the list
     */

    public void add() {
        this.list.add(new Contact(this.random));
    }
    
    /**
     * print list data
     * @return data for testing purposes
     */
    
    @Override
    public String printData() {
        String string = "";
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(
            "Number: " + this.list.get(i).number + 
            "\nEmail: " + this.list.get(i).email + 
            "\nLast Name: " + this.list.get(i).lastName + 
            "\nFirst Name: " + this.list.get(i).firstName + "\n"
            );
            string += this.list.get(i).lastName + this.list.get(i).firstName + " ";
        }
        return string;
    }
}
