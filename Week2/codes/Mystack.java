public class Mystack<Item>
{
	int sz;
	Item[] arr;
	int iter; 
	public Mystack()
	{
		sz = 2;
		arr = (Item[])new Object[sz];
		iter = 0;
	}
	public boolean empty()
	{
		return iter==0;
	}
	public int size()
	{
		return iter;
	}
	public void push(Item x)
	{
		if(iter==sz) expand();
		arr[iter++] = x;
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
	public void pop()
	{
		
		iter--;
		arr[iter] = null;
		if(iter==sz/4) contract();
	}
	public Item top()
	{
		return arr[iter-1];
	}
	private void contract()
	{
		Item[] prev = arr;
		sz = sz/2;
		arr = (Item[]) new Object[sz];
		for(int i = 0; i < sz; i++)
		{
			arr[i] = prev[i];
		}
	}
}