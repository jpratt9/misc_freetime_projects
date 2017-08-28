import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Your implementation of HashMap.
 * 
 * @author John Pratt
 * @version 1.3
 */
public class HashMap<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code STARTING_SIZE}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }

    /**
     * Calculates and returns a hash value for {@code key}. The formula used
     * is "hash = key.hashCode % tableLength".
     *
     * @param key key value whose hash is to be calculated.
     * @return the hash value associated with the inputted key.
     */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    // working (?)
    @Override
    public V set(K key, V value) {
        if (key == null || value == null) {
            return false;
        }

        if (((size + 1) / (double) table.length) > MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }

        return setHelper(key, value);
    }

    /**
     * Does the bulk of the work in the add method. Actually adds a given
     * key, value pair to the backing table (does not regrow).
     *
     * @param key key to add into the HashMap
     * @param value value to add into the HashMap
     * @return null if the key was not already in the map.  If it was in the
     * map, return the old value associated with it
     */
    private boolean setHelper(K key, V value) {
        // When actually adding, we'll loop the backing array until we arrive
        // back at the position we started at.
        int hash = hash(key);
        int startHash = hash;
        V oldValue;
        boolean keymatch;
        MapEntry<K, V> curr;
        do {
            curr = table[hash];
            // If we find an empty spot, add the entry to this spot and return
            // false.
            if (curr == null) {
                table[hash] = new MapEntry<>(key, value);
                size++;
                return false;
            }

            keymatch = curr == null ? false : curr.getKey().equals(key);
            if (curr != null && (keymatch || curr.isRemoved())) {
                if (!keymatch || curr.isRemoved()) {
                    size++;
                }
                boolean rem = curr.isRemoved();
                curr.setRemoved(false);
                oldValue = curr.getValue();
                curr.setValue(value);
                return rem && !keymatch ? null : oldValue;
            }
            hash = (hash + 1) % table.length;
        } while (hash != startHash);
        // This scenario isn't really possible, since it would only happen if
        // the backing array was full of entries whose keys were not equal to
        // the inputted key.
        return false;
    }

    // working (?)
    @Override
    public V delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot remove null key from "
                    + "hash map.");
        }

        int hash = hash(key);
        int startHash = hash;
        MapEntry<K, V> curr;
        do {
            curr = table[hash];
            if (curr == null || (curr.isRemoved()
                    && curr.getKey().equals(key))) {
                throw new NoSuchElementException("Attempted to remove key that "
                        + "was not contained within the hash map.");
            }
            if (table[hash].getKey().equals(key)) {
                table[hash].setRemoved(true);
                size--;
                return table[hash].getValue();
            }
            hash = (hash + 1) % table.length;
        } while (startHash != hash);
        throw new NoSuchElementException("Attempted to remove key that "
                + "was not contained within the hash map.");
    }

    // working (?)
    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }

        int hash = hash(key);
        int startHash = hash;
        do {
            if (table[hash] == null) {
                return null;
            }
            if (table[hash].getKey().equals(key)) {
                return table[hash].getValue();
            }
            hash = (hash + 1) % table.length;
        } while (startHash != hash);
        return null;
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot check if null key is "
                    + "in hash map.");
        }

        int hash = hash(key);
        int startHash = hash;
        do {
            if (table[hash] == null) {
                return false;
            }
            if (table[hash].getKey().equals(key)) {
                return true;
            }
            hash = (hash + 1) % table.length;
        } while (startHash != hash);
        return false;
    }

    // FIXME
    @Override
    public void clear() {
        size = 0;
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
    }

    // working (?)
    @Override
    public int size() {
        return size;
    }

    // working (?)
    @Override
    public Set<K> keySet() {
        Set<K> res = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                res.add(table[i].getKey());
            }
        }
        return res;
    }

    // working (?)
    @Override
    public List<V> values() {
        List<V> res = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                res.add(table[i].getValue());
            }
        }
        return res;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length <= 0 || length < size) {
            throw new IllegalArgumentException("Cannot resize backing table "
                    + "to negative length or length less than current number "
                    + "of elements inside the hash map.");
        }
        MapEntry<K, V>[] tableCopy = table;
        table = (MapEntry<K, V>[]) new MapEntry[length];
        MapEntry<K, V> tmp;
        for (int i = 0; i < tableCopy.length; i++) {
            tmp = tableCopy[i];
            if (tmp != null && !tmp.isRemoved()) {
                addHelper(tmp.getKey(), tmp.getValue());
                size--; // counteracts the incrementation of size
            }
        }
    }

    //private void

    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

    public float load() {
        return ((float) size) / table.length
    }

}
