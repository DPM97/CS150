/**
 * student record class
 */
public class StudentRecord {
    /**
     * first name
     */
    private String firstName;
    /**
     * last name
     */
    private String lastName;
    /**
     * id
     */
    private String ID;

    /**
     * constructor
     */
    public StudentRecord(String firstName, String lastName, String ID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
    }

    /**
     * get first name
     * @return first name
     */

    public String getFirstName() {
        return this.firstName;
    }

    /**
     * set first name
     */

    public void setFirstName(String name) {
        this.firstName = name;
    }

    /**
     * get last name
     * @return last name
     */

    public String getLastName() {
        return this.lastName;
    }

    /**
     * set last name
     */

    public void setLastName(String name) {
        this.lastName = name;
    }

    /**
     * get id
     * @return id
     */

    public String getID() {
        return this.ID;
    }

    /**
     * set ID
     * @param id id
     */

    public void setID(String id) {
        this.ID = id;
    }
}
