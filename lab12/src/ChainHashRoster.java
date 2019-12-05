import java.util.*;

/**
 * chain hash roster class
 */

public class ChainHashRoster implements Roster {

    /**
     * hash table
     */
    public TreeRoster[] table;
    /**
     * table size
     */
    public int size;
    /**
     * max chain size
     */
    public int threshold;

    /**
     * constructor
     *
     * @param size      amount of buckets
     * @param threshold max chain length
     */
    public ChainHashRoster(int size, int threshold) {
        this.size = size;
        this.table = new TreeRoster[this.size];
        this.threshold = threshold;
    }

    /**
     * clear roster
     */
    @Override
    public void clear() {
        this.table = new TreeRoster[this.size];
    }

    /**
     * check if contains ID
     *
     * @param ID input ID
     * @return true if contains
     */
    @Override
    public boolean containsKey(String ID) {
        int hash = hash(ID);
        TreeRoster bucket = this.table[hash];
        if (bucket.get(ID) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * check if contains record
     *
     * @param record inpout record
     * @return true if contains
     */
    @Override
    public boolean containsValue(StudentRecord record) {
        int hash = hash(record.getID());
        TreeRoster bucket = this.table[hash];
        if (bucket.containsValue(record)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * fetch student record
     *
     * @param ID id to fetch
     * @return student record with given ID
     */
    @Override
    public StudentRecord get(String ID) {
        int hash = hash(ID);
        TreeRoster bucket = this.table[hash];
        return bucket.get(ID);
    }

    /**
     * get set of ID's
     *
     * @return set of prev. ID's
     */
    @Override
    public Set<String> keySet() {
        Set<String> set = new TreeSet<>();
        for (int i = 0; i < this.table.length; i++) {
            for (Map.Entry<String, StudentRecord> entry : this.table[i].entrySet()) {
                set.add(entry.getKey());
            }
        }
        return set;
    }

    /**
     * add student record
     * @param record record to add
     * @return record added
     */
    @Override
    public StudentRecord put(StudentRecord record) {
        int hash = hash(record.getID());
        if (this.table[hash] == null) {
            this.table[hash] = new TreeRoster();
        }
        record = this.table[hash].put(record);
        if (this.table[hash].size() == this.threshold) {
            resize();
        }
        return record;
    }

    /**
     * remove student record
     * @param ID to remove
     * @return record removed
     */
    @Override
    public StudentRecord remove(String ID) {
        int hash = hash(ID);
        return this.table[hash].remove(ID);
    }

    /**
     * get amount of records
     *
     * @return amount
     */
    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < this.table.length; i++) {
            size += this.table[i].size();
        }
        return size;
    }

    /**
     * returns collection of all stored records
     *
     * @return
     */
    @Override
    public Collection<StudentRecord> values() {
        Collection<StudentRecord> col = new LinkedList<>();
        String[] set = (String[]) keySet().toArray();
        for (int i = 0; i < set.length; i++) {
            col.add(get(set[i]));
        }
        return col;
    }

    /**
     * hash method
     *
     * @param key input
     * @return hashed input
     */

    public int hash(String key) {
        return Math.abs(key.hashCode() % this.size);
    }

    /**
     * resize
     */

    public void resize() {
        Collection<StudentRecord> col = values();
        this.table = new TreeRoster[this.size *= 2];
        for (StudentRecord record : col) {
            put(record);
        }
    }

}
