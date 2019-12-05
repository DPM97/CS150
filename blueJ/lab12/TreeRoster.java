import java.util.Map;
import java.util.TreeMap;

/**
 * treeRoster class
 */

public class TreeRoster extends TreeMap<String, StudentRecord> implements Roster {

    public TreeMap<String,StudentRecord> roster;

    /**
     * constructor
     */
    public TreeRoster() {
        this.roster = new TreeMap<String, StudentRecord>();
    }

    /**
     * check if contains ID
     * @param ID input ID
     * @return true if contains
     */

    @Override
    public boolean containsKey(String ID) {
        for (int i = 0; i < this.roster.size(); i++) {
            if (this.get(ID) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if contains record
     * @param record inpout record
     * @return true if contains
     */

    @Override
    public boolean containsValue(StudentRecord record) {
        if (containsKey(record.getID())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * fetch student record
     * @param ID id to fetch
     * @return student record with given ID
     */

    @Override
    public StudentRecord get(String ID) {
        for (Map.Entry<String, StudentRecord> entry : this.roster.entrySet()) {
            if (entry.getKey().equals(ID)) {
                return entry.getValue();
            }
        }
        return null;
    }


    /**
     * add student record
     * @param record record to add
     * @return record added
     */

    @Override
    public StudentRecord put(StudentRecord record) {
        return this.roster.put(record.getID(), record);
    }

    /**
     * remove student record
     * @param ID to remove
     * @return record removed
     */

    @Override
    public StudentRecord remove(String ID) {
        return this.roster.remove(ID);
    }
}
