public class UF
{
	int [] sz;
	int [] parent;
	public UF(int N)
	{
		sz = new int[N];
		parent = new int[N];
		for(int i = 1; i <= N; i++)
		{
			parent[i] = i;
			sz[i] = 1;
		}
	}
	public int find(int a)
	{
		if(parent[a]==a) return a;
		parent[a] = find(parent[a]);
		return parent[a];
	}
	public void union(int a, int b)
	{
		int pa = find(a), pb = find(b);
		int sa = sz[pa], sb = sz[pb];
		if(pa!=pb)
		{
			if(sa>sb)
			{
				sz[pa]+=sb;
				sz[pb] = 0;
				parent[pb] = pa;
			}
			else
			{
				sz[pb]+=sz[pa];
				sz[pa] = 0;
				parent[pa] = pb;
			}
		}
	}
	public static void main(String[] args) {
		
	}
}