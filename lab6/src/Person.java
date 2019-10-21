import java.util.Random;

public abstract class Person <E> {
    String firstName;
    String lastName;
    Long id;

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    };

    ;

    public String getLastName() {
        return lastName;
    };

    ;

    public String createName(Random random) {
        String string = "";
        string += (char) (random.nextInt(26) + 'A');
        for (int i = 0; i < random.nextInt(6) + 3; i++) {
            string += (char) (random.nextInt(26) + 'a');
        }
        return string;
    }

}
