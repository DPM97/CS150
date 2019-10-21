import java.util.Random;

/**
 * abstract person class
 * class for the different types of people
 * to extend
 */

public abstract class Person <E> {
    String firstName;
    String lastName;
    Long id;

    public Person() {
    }
    
    /**
     * @return first name
     */

    public String getFirstName() {
        return firstName;
    };

    ;
    
    /**
     * @return last name
     */

    public String getLastName() {
        return lastName;
    };

    ;
    
    /**
     * create random name string
     * @param random random from controller
     * @return random name
     */

    public String createName(Random random) {
        String string = "";
        string += (char) (random.nextInt(26) + 65);
        for (int i = 0; i < random.nextInt(6) + 3; i++) {
            string += (char) (random.nextInt(26) + 97);
        }
        return string;
    }

}
