package cs4370;
/************************************************************************************
 * @file LinHashMap.java
 *
 * @author  John Miller
 */

import java.io.*;
import java.lang.reflect.Array;
import static java.lang.System.out;
import java.util.*;

/************************************************************************************
 * This class provides hash maps that use the Linear Hashing algorithm.
 * A hash table is created that is an array of buckets.
 */
public class LinHashMap <K, V>
        extends AbstractMap <K, V>
        implements Serializable, Cloneable, Map <K, V>
{
    /** The number of slots (for key-value pairs) per bucket.
     */
    private static final int SLOTS = 4;

    /** The class for type K.
     */
    private final Class <K> classK;

    /** The class for type V.
     */
    private final Class <V> classV;

    /********************************************************************************
     * This inner class defines buckets that are stored in the hash table.
     */
    private class Bucket
    {
        int    nKeys;
        K []   key;
        V []   value;
        Bucket next = null;
        Bucket activeBucket;

        @SuppressWarnings("unchecked")
        Bucket ()
        {
            nKeys = 0;
            key   = (K []) Array.newInstance (classK, SLOTS);
            value = (V []) Array.newInstance (classV, SLOTS);
        } // constructor
    } // Bucket inner class

    /** The list of buckets making up the hash table.
     */
    private final List <Bucket> hTable;

    /** The modulus for low resolution hashing
     */
    private int mod1;

    /** The modulus for high resolution hashing
     */
    private int mod2;

    /** Counter for the number buckets accessed (for performance testing).
     */
    private int count = 0;

    /** The index of the next bucket to split.
     */
    private int split = 0;

    private double loadFactor = 0.75;

    private int itemsInserted = 0;

    private Bucket newBucket, insertionBucket, activeBucket;

    //private int capacity;

    /********************************************************************************
     * Construct a hash table that uses Linear Hashing.
     * @param classK    the class for keys (K)
     * @param classV    the class for keys (V)
     * @param initSize  the initial number of home buckets (a power of 2, e.g., 4)
     */
    public LinHashMap (Class <K> _classK, Class <V> _classV) //, int initSize)
    {
        classK = _classK;
        classV = _classV;
        hTable = new ArrayList <> ();
        mod1   = 4;                        // initSize;
        mod2   = 2 * mod1;
        //capacity = mod1;

        for (int i = 0; i < mod1; i++) {
            newBucket = new Bucket();
            newBucket.activeBucket = newBucket;
            hTable.add(i, newBucket);
        }
    } // constructor

    /********************************************************************************
     * Return a set containing all the entries as pairs of keys and values.
     * @return  the set view of the map
     */
    public Set <Map.Entry <K, V>> entrySet ()
    {
        List<K> keyList = new ArrayList();
        List<V> valueList = new ArrayList();
        Map <K, V> map = new HashMap<K, V> ();

        for(int i = 0; i < hTable.size(); i++) {
            Bucket currentBucket = hTable.get(i);
            while (currentBucket != null) {
                for (int j = 0; j < currentBucket.nKeys; j++) {
                    keyList.add(currentBucket.key[j]);
                    valueList.add(currentBucket.value[j]);
                }
                currentBucket = currentBucket.next;
            }
        }

        for(int i = 0; i < keyList.size(); i++){
            map.put(keyList.get(i), valueList.get(i));
        }
        return map.entrySet();
    } // entrySet

    /********************************************************************************
     * Given the key, look up the value in the hash table.
     * @param key  the key used for look up
     * @return  the value associated with the key
     */
    public V get (Object key)
    {
        count++;
        int indexHash = h(key);
        if (indexHash < split) {
            indexHash = h2(key);
        }
        Bucket currentBucket = hTable.get(indexHash);
        while (currentBucket != null) {
            for (int i = 0; i < currentBucket.nKeys; i++) {
                if (currentBucket.key[i].toString().equalsIgnoreCase(key.toString())) {
                    return currentBucket.value[i];
                }
            }
            currentBucket = currentBucket.next;
        }
        return null;
    } // get

    /********************************************************************************
     * Put the key-value pair in the hash table.
     * @param key    the key to insert
     * @param value  the value to insert
     * @return  null (not the previous value)
     */
    public V put (K key, V value)
    {
        activeBucket = hTable.get(split);  //get the bucket pointed to by the split value.

        int indexHash = h (key);
        if (indexHash < split) {
            indexHash = h2 (key);
        }
	//        out.println ("LinearHashMap.put: key = " + key + ", h() = " + indexHash + ", value = " + value);

        insertionBucket = hTable.get(indexHash);  //insertion bucket

        if (insertionBucket.nKeys == SLOTS) {
            newBucket = new Bucket();
            newBucket.activeBucket = newBucket;
            hTable.add(newBucket);
            reallocate(activeBucket);

            //increase split
            split++;
            if ( split == mod1) {
                split = 0;
                mod1 = mod1 * 2;
                mod2 = mod1 * 2;
            }
        }
        insertionBucket = hTable.get(indexHash);
        insertAtInsertionBucket(key, value);
        itemsInserted++;
        return null;
    } // put

    /*
     * Adds a key, value pair at the insertion bucket, adding an overflow bucket if necessary.
     */
    private void insertAtInsertionBucket(K key, V value){

        if (insertionBucket.activeBucket.nKeys == SLOTS) { //create overflow bucket since insertion bucket is full
            newBucket = new Bucket();
            insertionBucket.activeBucket.next = newBucket;
            insertionBucket.activeBucket = newBucket;
        }
        //Now insert value and increment counter.
        insertTuple(key, value, insertionBucket.activeBucket, insertionBucket.activeBucket.nKeys);
        insertionBucket.activeBucket.nKeys++;
    }

    /* This method splits a bucket and distributes the items to existing buckets based on the new modulus.
     * @param: The bucket to be split and replaced by the new bucket with increased modulus.
     */
    private void reallocate(Bucket splitBucket) {

        //replace the split bucket with a new bucket.
        newBucket = new Bucket();
        newBucket.activeBucket = newBucket;
        hTable.set(split, newBucket);

        while (splitBucket != null) {
            for (int i = 0; i < splitBucket.nKeys; i++) {
                int indexHash = h2(splitBucket.key[i]);  //by high resolution
                if (indexHash > hTable.size()) {
                    indexHash = h(splitBucket.key[i]);
                }
                insertionBucket = hTable.get(indexHash);
                insertAtInsertionBucket(splitBucket.key[i], splitBucket.value[i]);
            }
            splitBucket = splitBucket.next;
        }
    }

    /********************************************************************************
     * Put the key-value pair in the bucket at index.
     * @param key    the key to insert
     * @param value  the value to insert
     * @param currentBucket the bucket in which to be inserted
     * @param index the index in the bucket at which to be inserted
     */
    private void insertTuple (K key, V value, Bucket currentBucket, int index) {
        currentBucket.key[index] = key;
        currentBucket.value[index] = value;
    }
    /********************************************************************************
     * Return the size (SLOTS * number of home buckets) of the hash table.
     * @return  the size of the hash table
     */
    public int size ()
    {
        return SLOTS * (mod1 + split);
    } // size

    /********************************************************************************
     * Print the hash table.
     */
    private void print ()

    {

        out.println ("Hash Table (Linear Hashing)");

        out.println ("-------------------------------------------");

        

        for(int i = 0; i < hTable.size(); i++) { // Iterate through all buckets and print contents

        out.print("BUCKET "+i+ " --> ");

        for(Bucket b = hTable.get(i); b!= null; b = b.next) {

        out.print("[ ");

        for(int j = 0; j < b.nKeys; j++) {

        out.print(b.key[j]+" -> "+b.value[j]+ ", ");

        }

        out.print("]");

        }

        out.println("");

        }

        out.println ("\n-------------------------------------------");

    } // print

    /********************************************************************************
     * Hash the key using the low resolution hash function.
     * @param key  the key to hash
     * @return  the location of the bucket chain containing the key-value pair
     */
    private int h (Object key)
    {
        return Math.abs(key.hashCode () % mod1);
    } // h

    /********************************************************************************
     * Hash the key using the high resolution hash function.
     * @param key  the key to hash
     * @return  the location of the bucket chain containing the key-value pair
     */
    private int h2 (Object key)
    {
        return Math.abs(key.hashCode () % mod2);
    } // h2

    /********************************************************************************
     * The main method used for testing.
     * @param  the command-line arguments (args [0] gives number of keys to insert)
     */
    public static void main (String [] args)
    {

        int totalKeys    = 50;
        boolean RANDOMLY = false;

        LinHashMap <Integer, Integer> ht = new LinHashMap <> (Integer.class, Integer.class);
        if (args.length == 1) totalKeys = Integer.valueOf (args [0]);

        if (RANDOMLY) {
            Random rng = new Random ();
            for (int i = 1; i <= totalKeys; i += 2) ht.put (rng.nextInt (2 * totalKeys), i * i);
        } else {
            for (int i = 1; i <= totalKeys; i += 2) ht.put (i, i * i);
        } // if

        ht.print ();
        for (int i = 0; i <= totalKeys; i++) {
            out.println ("key = " + i + " value = " + ht.get (i));
        }
        //
        out.println ("-------------------------------------------");
        out.println(ht.count);
        out.println ("Average number of buckets accessed = " + ht.count / (double) totalKeys);
    } // main

} // LinHashMap class
