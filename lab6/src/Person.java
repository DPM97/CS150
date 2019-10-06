import java.util.Random;

public abstract class Person {
    String firstName;
    String lastName;
    private Random random;

    public Person() {

    }

    public String getFirstName() {
        return createName();
    };

    ;

    public String getLastName() {
        return createName();
    };

    ;

    public String createName() {
        String string = "";
        string += (char) (this.random.nextInt(26) + 'A');
        for (int i = 0; i < this.random.nextInt(6) + 3; i++) {
            string += (char) (this.random.nextInt(26) + 'a');
        }
        return string;
    }
}
