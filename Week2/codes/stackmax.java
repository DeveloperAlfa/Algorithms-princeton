public class stackmax
{
	Mystack<Integer> stack, maxs;
	public stackmax()
	{
		stack = new Mystack<Integer>();
		maxs = new Mystack<Integer>();
	}
	public void push(int x)
	{
		if(maxs.empty()) maxs.push(x);
		else if(maxs.top()<=x) maxs.push(x);
		stack.push(x);
	}
	public void pop()
	{
		if(!maxs.empty())
		{
			if(maxs.top()==stack.top())
			{
				maxs.pop();
			}
		}
		if(!stack.empty()) stack.pop();
	}
	public int top()
	{
		return stack.top();
	}
	public int max()
	{
		return maxs.top();
	}
}