import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int sz;
    private int filled;
    // construct an empty randomized queue
    public RandomizedQueue()
    {
        sz = 2;
        filled = 0;
        arr = (Item[]) new Object[sz];
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return filled==0;
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return filled;
    }

    // add the item
    public void enqueue(Item item)
    {
        if(item==null) throw new IllegalArgumentException();
        if(filled==sz) expand();
        arr[filled++] = item;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if(filled==0) throw new NoSuchElementException();
        if(filled==sz/4) contract();
        
        int i = StdRandom.uniform(0, filled);
        if(arr[i]!=null)
        {
            Item ret = arr[i];
            arr[i] = arr[filled-1];
            arr[filled-1] = null;
            filled--;
            return ret;
        }
        return null;
        
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if(filled==0) throw new NoSuchElementException();
        if(filled==sz/4) contract();
        int i = StdRandom.uniform(0, filled);
        if(arr[i]!=null)
        {
            Item ret = arr[i];
            return ret;
        }
        return null;
    }
    private void expand()
    {
        Item[] prev = arr;
        arr = (Item[]) new Object[sz*2];
        for(int i = 0; i < sz; i++)
        {
            arr[i] = prev[i];
        }
        sz = sz*2;
    }
    private void contract()
    {
        Item[] prev = arr;
        arr = (Item[]) new Object[sz/2];
        int k = 0;
        for(int i = 0; i < sz; i++)
        {
            if(prev[i]!=null) arr[k++] = prev[i];
        }
        sz = sz/2;
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new MyIter();
    }

    private class MyIter implements Iterator<Item>
    {
        Item[] p;
        int i;
        public MyIter()
        {
            p = (Item[])new Object[filled];
            int k = 0;
            for(int j = 0; j < sz; j++)
            {
                if(arr[j]!=null) p[k++] = arr[j];
            }
            StdRandom.shuffle(p);
            i = 0;
        }
        public boolean hasNext()
        {
            return i < filled;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        public Item next()
        {
            if(!hasNext()) throw new NoSuchElementException();
            return p[i++];
        }
    }
    // unit testing (required)
    public static void main(String[] args)
    {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        if(q.isEmpty()) StdOut.println("The bag is empty!");
        if(q.size()==0) StdOut.println("The size is zero!");
        q.enqueue("god");
        StdOut.println(q.sample());
        q.dequeue();
        q.enqueue("is");
        q.enqueue("one");
        Iterator<String> iter = q.iterator();
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
            StdOut.println("Sorry! Remove is not permitted!");
        }
    }

}