import java.util.Scanner;
public class NextNumber
{
	int N;
	int[] belongs;
	int[] next;
	int[] parent;
	int[] sz;
	public NextNumber(int n)
	{
		N = n;
		belongs = new int[N+2];
		next = new int[N+2];
		parent = new int[N+2];
		sz = new int[N+2];
		for(int i = 1; i <= N; i++)
		{
			belongs[i] = 1;
			next[i] = i+1;
			parent[i] = i;
			sz[i] = 1;
		}
	}
	//Check if this number is in the set
	public boolean belongs(int k)
	{
		return belongs[k]==1;
	}
	//Find the next largest number
	public int findNext(int k)
	{
		return next[findParent(k)];
	}
	//Delete this number
	public void delete(int k)
	{
		if(k==1)
		{
			belongs[1] = 0;
			return;
		}
		int parent_km1 = findParent(k-1);
		int parent_k = findParent(k);
		parent[k-1] = parent_k;
		belongs[k] = 0; 
	}

	private int findParent(int k)
	{
		
		if(parent[k]==k) return k;
		parent[k] = findParent(parent[k]);
		return parent[k];
	}
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		NextNumber nn = new NextNumber(n);
		int t = sc.nextInt();
		while(t-->0)
		{
			int qt = sc.nextInt();
			if(qt==1)
			{
				int nex = sc.nextInt();
				System.out.println(nn.findNext(nex));
			}
			else
			{
				int del = sc.nextInt();
				nn.delete(del);
			}
		}
	}
}