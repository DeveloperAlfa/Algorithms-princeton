public class LargestNumber
{
	private int[] parent;
	private int[] sz;
	private int[] largest;
	public LargestNumber(int N)
	{
		parent = new int[N+2];
		sz = new int[N+2];
		largest = new int[N+2];
		for(int i = 0; i <= N; i++)
		{
			parent[i] = i;
			sz[i] = 1;
			largest[i] = i;
		}
	}
	public int largest(int q)
	{
		int parent_q = findParent(q);
		return largest[parent_q];
	}
	public int findParent(int q)
	{
		if(parent[q]==q) return q;
		parent[q] = findParent(parent[q]);
		return parent[q];
	}
	public void union(int p, int q)
	{
		int pp = findParent(p);
		int pq = findParent(q);
		if(pp==pq) return;
		if(sz[pp]>sz[pq])
		{
			largest[pp] = Math.max(largest[pp], largest[pq]);
			sz[pp]+=sz[pq];
			sz[pq] = 0;
			parent[pq] = pp;
		}
		else
		{
			if(sz[pp]>sz[pq])
			{
				largest[pq] = Math.max(largest[pp], largest[pq]);
				sz[pq]+=sz[pp];
				sz[pp] = 0;
				parent[pp] = pq;
			}
		}
	}
}