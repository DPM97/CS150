import java.util.Random;

public abstract class Person {
    String firstName;
    String lastName;
    private Random random;

    public Person() {

    }

    public abstract String getFirstName();

    ;

    public abstract String getLastName();

    ;

    public String createName() {
        String string = "";
        for (int i = 0; i < this.random.nextInt(6) + 3; i++) {
            string += (char) (this.random.nextInt(26) + 'a');
        }
        return string;
    }
}
