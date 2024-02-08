public class SequentialSearch <Key, Value> implements SymbolTable <Key, Value>
{

    private Node head;
    private int size;
    private class Node
    {
        Key key;
        Value value;
        Node next;

        //constructor
        public Node (Key key, Value value, Node next)
        {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public SequentialSearch()
    {
        head = null;
        size = 0;
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
        //if the key is not in the list, put it up front at the head
        //if the key is already in the list, replace the value in that node

        //loop through the nodes to see if the key is there
        Node current = head;
        while (current != null)
        {
            if (key.equals(current.key))
            {
                //we found it, replace the value
                current.value = val;
                return; //break out of the loop
            }
            //march on to the next Node
            current = current.next;
        }

        //if we got here, we know key doesn't exist in the list, so make a new node and put it at the front
        head = new Node(key, val, head);
        size++;
    }

    /**
     * Returns the value paired with the given key.
     *
     * @param key
     */
    @Override
    public Value get(Key key)
    {
        Node current = head;
        while (current != null)
        {
            if (key.equals(current.key))
            {
                return current.value;
            }
            current = current.next;
        }
        //if I got here, I know key isn't in the list
        return null;
    }

    /*
    Another implementation:
    @Override
    public Value get(Key key)
    {
        for(Node current = head; current != null; current = current.next)
        {
            if (key.equals(current.key))
            {
                return current.value;
            }
        }
        return null;
    }
     */

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
        Queue<Key> q = new LinkedQueue<>();

        Node current = head;

        while (current != null)
        {
            q.enqueue(current.key);
            current = current.next;
        }

        return q;
    }
}