import java.util.*;

/**
 * linear hash roster class
 */

public class LinearHashRoster implements Roster {

    /**
     * amount of buckets
     */

    public int size;

    /**
     * load factor
     */

    public double loadFactor;

    /**
     * table
     */

    public StudentRecord[] table;

    /**
     * constructor
     * @param size amount of buckets
     * @param threshold load factor
     */

    public LinearHashRoster(int size, double threshold) {
        this.size = size;
        this.loadFactor = threshold;
        this.table = new StudentRecord[this.size];
    }


    /**
     * clear roster
     */
    @Override
    public void clear() {
        this.table = new StudentRecord[this.size];
    }

    /**
     * check if contains ID
     * @param ID input ID
     * @return true if contains
     */
    @Override
    public boolean containsKey(String ID) {
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] != null) {
                if (this.table[i].getID().equals(ID)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check if contains record
     * @param record input record
     * @return true if contains
     */
    @Override
    public boolean containsValue(StudentRecord record) {
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] != null) {
                if (this.table[i].equals(record)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * fetch student record
     * @param ID id to fetch
     * @return student record with given ID
     */
    @Override
    public StudentRecord get(String ID) {
        int bucket = hash(ID);
        int visited = 0;
        if (this.table[bucket] != null) {
            if (this.table[bucket].getID().equals(ID)) {
                return this.table[bucket];
            } else {
                while(visited < this.table.length) {
                    bucket = ((bucket + 1) % this.table.length);
                    if (this.table[bucket].getID().equals(ID)) {
                        return this.table[bucket];
                    }
                }
            }
        }
        return null;
    }

    /**
     * get set of ID's
     * @return set of prev. ID's
     */
    @Override
    public Set<String> keySet() {
        Set<String> set = new TreeSet<>();
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] != null) {
                set.add(this.table[i].getID());
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
        int bucket = hash(record.getID());
        int bucketsProbed = 0;

        while (bucketsProbed < this.table.length) {
            if (this.table[bucket] == null) {
                this.table[bucket] = record;
                if (getLoadFactor() >= this.loadFactor) {
                    resize();
                }
                return this.table[bucket];
            } else {
                bucket = ((bucket + 1) % this.table.length);
            }
        }
        return null;
    }

    /**
     * remove student record
     * @param ID to remove
     * @return record removed
     */
    @Override
    public StudentRecord remove(String ID) {
        int bucket = hash(ID);
        int bucketsProbed = 0;

        while (bucketsProbed < this.table.length) {
            if (this.table[bucket] != null) {
                if (this.table[bucket].getID().equals(ID)) {
                    StudentRecord record = this.table[bucket];
                    this.table[bucket] = null;
                    return record;
                } else {
                    bucket = ((bucket + 1) % this.table.length);
                }
            }
        }
        return null;
    }

    /**
     * get amount of records
     * @return amount
     */
    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] != null) {
                size++;
            }
        }
        return size;
    }

    /**
     * returns collection of all stored records
     * @return
     */
    @Override
    public Collection<StudentRecord> values() {
        Collection<StudentRecord> col = new LinkedList<>();
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] != null) {
                col.add(this.table[i]);
            }
        }
        return col;
    }

    /**
     * hash method
     * @param key input
     * @return hashed input
     */
    public int hash(String key) {
        return Math.abs(key.hashCode() % this.size);
    }

    /**
     * calculate load
     */
    public float getLoadFactor() {
        float full = 0;
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] != null) {
                full++;
            }
        }
        return full / this.table.length;
    }

    /**
     * resize
     */
    public void resize() {
        Collection<StudentRecord> col = values();
        this.table = new StudentRecord[this.size *= 2];
        for (StudentRecord record : col) {
            put(record);
        }
    }
}
