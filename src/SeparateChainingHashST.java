public class SeparateChainingHashST<Key, Value> implements SymbolTable<Key, Value>
{
    //private fields
    //array of linked lists
    private SequentialSearch<Key, Value>[] table;
    private int tableSize; //size of the array
    private int size; //number of actual keys in the table

    //parameterized constructor
    public SeparateChainingHashST(int tableSize)
    {
        this.tableSize = tableSize;
        size = 0;

        //creates an array (each element is defaulted to null)
        table = new SequentialSearch[tableSize];

        //loop through array, replace null with an empty linked list object
        for (int i = 0; i < tableSize; i++)
        {
            table[i] = new SequentialSearch();
        }
    }

    //default constructor
    public SeparateChainingHashST()
    {
        //call the other constructor and set up to 997 buckets; 997 is arbitrary, we just want a lot.
        this(997);
    }

    //private helper method - the hash function
    private int hash(Key key)
    {
        //take a key and generate an index number
        //a simpler way of doing this: key.hashCode() % tableSize
        return (key.hashCode() & 0x7fffffff) % tableSize;
    }

    /**
     * Put a key-value pair into the table
     *
     * @param key
     * @param val
     */
    @Override
    public void put(Key key, Value val)
    {
        //if the table doesn't contain the key, bump the size up
        if (!table[hash(key)].contains(key))
        {
            size++;
        }
        //add the new key,
        //or replace the value associated with the key if it's already there
        table[hash(key)].put(key, val);
    }

    /**
     * Returns the value paired with the given key.
     *
     * @param key
     */
    @Override
    public Value get(Key key)
    {
        //get to the bucket, pull the item outta there
        return table[hash(key)].get(key);
    }

    /**
     * Returns the number of key-value pairs in the table.
     *
     * @return
     */
    @Override
    public int size()
    {
        return size;
    }

    /**
     * Returns an iterator that refers to all the keys
     * in the table.
     */
    @Override
    public Iterable<Key> keys()
    {
        //create an empty queue as a collector/container
        Queue<Key> q = new LinkedQueue<>();

        //loop through table and collect keys
        for (int i = 0; i < tableSize; i++)
        {
            for (Key singleKey: table[i].keys())
            {
                q.enqueue(singleKey);
            }
        }
        return q;
    }
}
