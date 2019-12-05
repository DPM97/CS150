import java.util.Collection;
import java.util.Set;

/**
 * record interface
 */
public interface Roster {

    /**
     * clear records
     */

    void clear();

    /**
     * check if record contains key
     * @param ID input ID
     * @return true if true
     */

    boolean containsKey(String ID);

    /**
     *
     * @param record
     * @return
     */

    boolean containsValue(StudentRecord record);

    /**
     * get record of ID if stored
     * @param ID id to fetch
     * @return record with ID
     */

    StudentRecord get(String ID);

    /**
     * get set of ID's
     * @return set of prev. ID's
     */

    Set<String> keySet();

    /**
     * adds record to the roster
     * @param record record to add
     * @return returns the old record
     */

    StudentRecord put(StudentRecord record);

    /**
     *  removes the record for ID from the roster
     * @param ID to remove
     * @return previously stored id
     */

    StudentRecord remove(String ID);

    /**
     * returns number of stored records
     * @return amount of stored records
     */

    int size();

    /**
     * returns collection of all stored records
     * @return
     */

    Collection<StudentRecord> values();
}
