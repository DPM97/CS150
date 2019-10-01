import java.util.Random;

public class ExperimentController {
    Random random;
    public ExperimentController() {
        this.random = new Random();
    }

    public static void main(String[] args) {
        ExperimentController controller = new ExperimentController();
        System.out.println(controller.generateId());
        System.out.println(controller.generateNumber());
        System.out.println(controller.generateEmail());

    }


    public long generateId() {
        String id = "";
        for (int i = 0; i < 8; i++) {
            id +=  this.random.nextInt(9);
        }
        return Long.parseLong(id);
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

    public String generateEmail() {
        String email = "";
        for (int i = 0; i < this.random.nextInt(9) + 3; i++) {
            email += (char) (this.random.nextInt(26) + 'a');
        }
        email += "@gmail.com";
        return email;
    }
}
