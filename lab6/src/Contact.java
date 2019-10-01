import java.util.Random;

public class Contact extends Person implements Comparable<Contact> {
    String number;
    String email;
    Random random;

    public Contact(String number, String email) {
        this.number = number;
        this.email = email;
        this.firstName = createName();
        this.lastName = createName();
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public int compareTo(Contact o) {
        String fullName = getLastName() + " " + getFirstName();
        return fullName.compareTo(o.getLastName() + " " + o.getFirstName());
    }
}
