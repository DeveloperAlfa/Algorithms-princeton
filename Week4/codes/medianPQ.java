public class medianPQ
{
	private int median;
	private minPQ<Integer> right = new minPQ<>();
	private maxPQ<Integer> left = new maxPQ<>();

	private int len;

	public medianPQ()
	{
		median = -1;
		len = 0;
	}
	public void push(int a) throws IllegalAccessException
	{
		if(len==0)
		{
			median = a;
			len++;
		}
		else if(a>median)
		{
			if(left.size()==right.size()) right.push(a);
			else
			{
				left.push(median);
				right.push(a);
				median = right.getMin();
				right.pop();
			}
			len++;
		}
		else
		{
			if(left.size()==right.size())
			{
				left.push(median);
				right.push(a);
				median = right.getMin();
				right.pop();
			}
			else left.push(a);
			len++;
		}
	}
	public int getMedian()
	{
		return median;
	}
	public void pop() throws IllegalAccessException
	{
		int ret = median;
		if(left.size()==right.size())
		{
			median = left.getMax();
			left.pop();
		}
		else if(right.size()>0)
		{
			median = right.getMin();
			right.pop();
		}
		else
		{
			median = -1;
		}
	}

	public static void main(String[] args) {
		try
		{
			medianPQ med = new medianPQ();
			med.push(1);
			med.push(2);
			med.push(3);
			med.push(4);
			System.out.println(med.getMedian());
			med.pop();
			System.out.println(med.getMedian());
		}
		catch(IllegalAccessException e)
		{
			System.out.println(e);
		}
		
	}
}