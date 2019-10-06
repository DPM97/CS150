import java.util.Random;

public class Contact extends Person implements Comparable<Contact> {
    String number;
    String email;
    Random random;

    public Contact() {
        this.number = generateNumber();
        this.email = "dummy@domain.net";
        this.firstName = createName();
        this.lastName = createName();
    }

    @Override
    public int compareTo(Contact o) {
        String fullName = this.lastName + " " + this.firstName;
        return fullName.compareTo(o.lastName + " " + o.firstName);
    }

    public String generateNumber() {
        String num = "(";
        for (int i = 0; i < 3; i++) {
            num +=  this.random.nextInt(9);
        }
        num += ")";
        for (int i = 0; i < 3; i++) {
            num +=  this.random.nextInt(9);
        }
        num += "-";
        for (int i = 0; i < 4; i++) {
            num +=  this.random.nextInt(9);
        }
        return num;
    }
}
