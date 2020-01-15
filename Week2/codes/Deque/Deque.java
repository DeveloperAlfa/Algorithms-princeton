import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
public class Deque<Item> implements Iterable<Item> {

    private class Node<Item>
    {
        public Item value;
        public Node<Item> next;
        public Node<Item> prev;
        public Node(Item i)
        {
            value = i;
            next = null;
            prev = null;
        }
    }
    private Node<Item> first, last;
    private int sz;
    public Deque()
    {
        first = null;
        last = null;
        sz = 0;
    }
    // is the deque empty?
    public boolean isEmpty()
    {
        return first==null;
    }
    // return the number of items on the deque
    public int size()
    {
        return sz;
    }
    // add the item to the front
    public void addFirst(Item item)
    {
        if(item==null) throw new IllegalArgumentException();
        if(first==null)
        {
            first = new Node<Item>(item);
            last = first;
        }
        else
        {
            Node<Item> ref = new Node<Item>(item);
            ref.next = first;
            first.prev = ref;
            first = ref;
        }
        sz++;
    }
    // add the item to the back
    public void addLast(Item item)
    {
        if(item==null) throw new IllegalArgumentException();
        if(first==null)
        {
            first = new Node<Item>(item);
            last = first;
        }
        else
        {
            last.next = new Node<Item>(item);
            Node<Item> ref = last;
            last = last.next;
            last.prev = ref;
        }
        sz++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if(first==null) throw new NoSuchElementException();
        Item value = first.value;
        if(first.next==null)
        {
            last = null;
            first = null;
        }
        else
        {
            first = first.next;
            first.prev = null;
        }
        sz--;
        return value;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if(first==null) throw new NoSuchElementException();
        Item value = last.value;
        if(last.prev==null)
        {
            first = null;
            last = null;
        }
        else
        {
            last = last.prev;
            last.next = null;
        }
        sz--;
        return value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new Iter();
    }
    private class Iter implements Iterator<Item> 
    {
        private Node<Item> iter;
        public Iter()
        {
            iter = first;
        }
        public boolean hasNext()
        {
            return iter!=null;
        }
        public void remove(){throw new UnsupportedOperationException();}
        public Item next()
        {
            if(iter==null) throw new NoSuchElementException();
            Item ret = iter.value;
            iter = iter.next;
            return ret;
        }
    }
    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<Integer> q = new Deque<Integer>();
        if(q.isEmpty()) StdOut.println("Ok! Initially queue is empty");
        if(q.size()==0) StdOut.println("The size of queue is zero!");
        q.addFirst(1);
        q.addLast(2);
        for(int i: q) StdOut.println(i+"");
        q.removeFirst();
        Iterator<Integer> iter = q.iterator();
        while(iter.hasNext())
        {
            StdOut.println(iter.next());
        }
        q.removeLast();
        iter = q.iterator();
        while(iter.hasNext())
        {
            StdOut.println(iter.next());
        }
        iter = q.iterator();
        try
        {
            iter.remove();
        }
        catch(UnsupportedOperationException e)
        {
            StdOut.println("Remove is illegal!");
        }
    }

}