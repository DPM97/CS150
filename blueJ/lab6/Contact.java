import java.util.Random;

/**
 * contact class
 * implments comparable (contact type)
 */

public class Contact extends Person implements Comparable<Contact> {
    /*** random phone number */
    String number;
    /*** email */
    String email;
    /*** random from controller */
    Random random;

    /**
     * constructor
     * @param random random from 
     */
    public Contact(Random random) {
        super();
        this.random = random;
        this.number = generateNumber();
        this.email = "dummy@domain.net";
        this.firstName = createName(this.random);
        this.lastName = createName(this.random);
    }

    /**
     * compares contact from input contact
     * and current contact
     * @return compare result
     */
    
    @Override
    public int compareTo(Contact o) {
        String fullName = this.lastName + " " + this.firstName;
        return fullName.compareTo(o.lastName + " " + o.firstName);
    }
    
    /**
     * method to generate random phone number
     * @return random phone number
     */

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
