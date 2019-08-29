import java.util.*;

public class Main {

    public static void main(String[] args) {
        scan();
    }

    public static void scan() {
        Scanner reader = new Scanner(System.in);
        /* get all of the variables */
        int feet = Integer.parseInt(scanner("Enter the feet: ", reader));
        float inches = Integer.parseInt(scanner("Enter the inches: ", reader));
        String operation = scanner("Enter the operation: ", reader);
        int feet1 = Integer.parseInt(scanner("Enter the feet: ", reader));
        float inches1 = Integer.parseInt(scanner("Enter the inches: ", reader));
        System.out.println(solve(feet, feet1, inches, inches1, operation));
    }

    public static String scanner(String string, Scanner reader) {
        System.out.println(string);
        String input = reader.next();
        String[] cases = new String[]{"gt", "lt", "gte", "lte"};
        if (string != "Enter the operation: ") {
            /* return number */
            return input;
        }
        /* return operation */
        return input;
    }

    public static boolean solve(int feet, int feet1, float inches, float inches1, String operation) {
        String[] cases = new String[]{"gt", "lt", "gte", "lte"};
        if (!Arrays.asList(cases).contains(operation)){
            /* if operator is not in the list of operators then rescan */
            System.out.println("Incorrect Operation...");
            return false;
        }
        /* turn feet into inches & add */
        float total1 = feet*12 + inches;
        float total2 = feet1*12 + inches1;
        /* solve accordingly given operation string */
        switch (operation) {
            case "gt":
                if (total1 > total2) {
                    return true;
                } else {
                    return false;
                }
            case "lt": {
                if (total1 < total2) {
                    return true;
                } else {
                    return false;
                }
            }
            case "gte": {
                if (total1 >= total2) {
                    return true;
                } else {
                    return false;
                }
            }
            case "lte": {
                if (total1 <= total2) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        /* will never run into this */
        return false;
    }
}
