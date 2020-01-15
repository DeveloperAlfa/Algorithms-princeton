import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation 
{

	private int[][] grid;
	private int sz;
	private int top;
	private int bottom;
	private int open_count;
	private WeightedQuickUnionUF uf;
	private boolean isValid(int[] i)
	{
		return (i[0]>0 && i[0]<=sz && i[1]>0 && i[1]<=sz);
	}
	private int convert(int[] n)
	{
		return (n[0]-1)*sz + n[1];
	}
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
    	if(n<=0) throw new IllegalArgumentException();
    	grid = new int[n+1][n+1];
    	uf = new WeightedQuickUnionUF(n*n + 2);
    	top = 0;
    	bottom = n*n + 1;
    	for(int i = 1; i <= n; i++)
    	{
    		uf.union(0, i);
    		uf.union(n*n + 1, n*n -n + i);
    	}
        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= n; j++) grid[i][j] = 0;
        }
    	
    	sz = n;
    	open_count = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
    	int[] curr = new int[]{row, col};
    	if(!isValid(curr))throw new IllegalArgumentException();
        if(isOpen(row, col)) return;
    	grid[row][col] = 1;
    	
    	int[] up = new int[]{row-1, col};
    	int[] down = new int[]{row+1, col};
    	int[] left = new int[]{row, col-1};
    	int[] right = new int[]{row, col+1};
    	if(isValid(up) && isOpen(up[0], up[1])) uf.union(convert(curr), convert(up));
    	if(isValid(down) && isOpen(down[0], down[1])) uf.union(convert(curr), convert(down));
    	if(isValid(right) && isOpen(right[0], right[1])) uf.union(convert(curr), convert(right));
    	if(isValid(left) && isOpen(left[0], left[1])) uf.union(convert(curr), convert(left));
    	open_count++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
    	int[] curr = new int[]{row, col};
    	if(!isValid(curr))throw new IllegalArgumentException();
    	return (grid[row][col]==1);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
    	int[] curr = new int[]{row, col};
    	if(!isValid(curr))throw new IllegalArgumentException();
    	return isOpen(row, col) && uf.connected(top, convert(curr));
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
    	return open_count;
    }

    // does the system percolate?
    public boolean percolates()
    {
    	return uf.connected(top, bottom);
    }

    // test client (optional)
    public static void main(String[] args){}
};
