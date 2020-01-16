public class minPQ<Key extends Comparable<Key>>
{
	private Key[] internal_pq;
	private int N;
	private int i;
	public minPQ()
	{
		N = 2;
		internal_pq = (Key[]) new Comparable[N];
		i = 0;
	}
	public minPQ(Key[] init)
	{
		N = init.length*2;
		i = init.length;
		internal_pq = (Key[]) new Comparable[N];
		for(int i = (N-1)/2; i >= 0; i--)
		{
			swim(i);
		}
	}
	private void swim(int pos)
	{
		if(pos==0||pos>=i) return;
		else
		{
			int parent = (pos-1)/2;
			if(internal_pq[parent].compareTo(internal_pq[pos])>0)
			{
				Key temp = internal_pq[parent];
				internal_pq[parent] = internal_pq[pos];
				internal_pq[pos] = temp;
				swim(parent);
			}
			
		}
	}
	private void sink(int pos)
	{
		int left = pos*2 + 1;
		int right = pos*2 + 2;
		int child = pos;
		if(left<i)
		{
			if(internal_pq[left].compareTo(internal_pq[child])<0)
			{
				child = left;
			}
		}
		if(right<i)
		{
			if(internal_pq[right].compareTo(internal_pq[child])<0)
			{
				child = right;
			}
		}
		Key temp = internal_pq[pos];
		internal_pq[pos] = internal_pq[child];
		internal_pq[child] = temp;
		if(child!=pos) sink(child);
	}
	public Key getMin() throws IllegalArgumentException
	{
		if(i==0) throw new IllegalArgumentException();
		return internal_pq[0];
	}
	public int size()
	{
		return i;
	}
	public void push(Key item)
	{
		if(i==N) expand();
		internal_pq[i++] = item;
		swim(i-1);
	}
	public void pop() throws IllegalAccessException
	{
		if(i==0) throw new IllegalAccessException();
		internal_pq[0] = internal_pq[--i];
		sink(0);
		if(i==N/2) contract();
	}
	private void expand()
	{
		Key[] prev = internal_pq;
		internal_pq = (Key[]) new Comparable[2*N];
		for(int id = 0; id < N; id++)
		{
			internal_pq[id] = prev[id];
		}
		N = 2*N;
	}
	private void contract()
	{
		Key[] prev = internal_pq;
		internal_pq = (Key[]) new Comparable[N/2];
		for(int id = 0; id < i; id++)
		{
			internal_pq[id] = prev[id];
		}
		N = N/2;
	}
	public static void main(String[] args) {
		minPQ<Integer> q = new minPQ<>();
		try
		{
			q.push(1);
			q.push(2);
			q.push(3);
			q.push(0);
			System.out.println(q.getMin());
			q.pop();
			System.out.println(q.getMin());
		}
		catch(Exception e){}
	}
}