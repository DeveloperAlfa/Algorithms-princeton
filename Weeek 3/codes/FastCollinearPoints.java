import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
public class FastCollinearPoints 
{
	private int filled;
	private int sz;
	private LineSegment[] l;
	private int[] root;
	private int[] szs;
	private double INF = Double.POSITIVE_INFINITY;
   private double NINF = Double.NEGATIVE_INFINITY;
   public FastCollinearPoints(Point[] points)
   {
   		if(points==null) throw new IllegalArgumentException();
   		sz = 2;
   		filled = 0;
   		int n = points.length;
   		root = new int[n];
   		szs = new int[n];
   		for(int i = 0; i < n; i++)
   		{
   			root[i] = i;
   			szs[i] = 1;
   		}
   		for(int i = 0; i < n; i++)
   		{
   			if(points[i]==null) throw new IllegalArgumentException();
   		}
   		for(int i = 0; i < n; i++)
   		{
   			for(int j = i+1; j < n; j++)
   			{
   				if(points[i].slopeTo(points[j])==NINF) throw new IllegalArgumentException();
   			}
   		}
   		l = new LineSegment[2];
   		for(int j = 0; j < n; j++)
   		{
   			Point p = points[j];
   			Point[] p1 = new Point[n-j-1];
   			for(int i = j+1, k = 0; i < n; i++, k++)
   			{
   				p1[k] = points[i];
   			}
   			Arrays.sort(p1, p.slopeOrder());
   			double val = NINF;
   			int start = -1, end = -1;
   			for(int i = 0; i < p1.length; i++)
   			{
   				double s = p.slopeTo(p1[i]);
   				if(s==val&&!connected(j, i+j+1)) end = i;
   				else
   				{
   					if(end-start+1 > 2)
   					{
   						Point[] temp = new Point[end-start+2];
   						for(int k = start, b = 0; k <= end; k++, b++) 
   						{
   							temp[b] = p1[k];
   							union(j, k+j+1);
   						}
   						temp[end-start+1] = p;
   						Arrays.sort(temp);
   						push(new LineSegment(p, temp[end-start+1]));
   					}
   					val = s;
   					start = i;
   					end = i;
   				}
   			}
   			if(end-start+1 > 2)
			{
				Point[] temp = new Point[end-start+1];
				for(int k = start, b = 0; k <= end; k++, b++) temp[b] = p1[k];
				Arrays.sort(temp);
				push(new LineSegment(p, temp[end-start]));
			}
   		}
   }
   private int find(int p)
   {
   		if(root[p]==p) return p;
   		root[p] = find(root[p]);
   		return root[p];
   }
   private void union(int p, int q)
   {
   		p = find(p);
   		q = find(q);
   		if(p==q) return;
   		if(szs[p]>szs[q])
   		{
   			szs[p]+=szs[q];
   			szs[q] = 0;
   			root[q] = p;
   		}
   		else
   		{
   			szs[q]+=szs[p];
   			szs[p] = 0;
   			root[p] = q;
   		}
   }
   private boolean connected(int i, int j)
   {
   		return find(i)==find(j);
   }
   private void push(LineSegment item)
   {
   		if(filled==sz) expand();
   		l[filled++] = item;
   }
   private void expand()
   {
   		LineSegment[] prev = l;
   		l = new LineSegment[sz*2];
   		for(int i = 0; i < sz; i++)
   		{
   			l[i] = prev[i];
   		}
   		sz = sz*2;
   }
   public int numberOfSegments()
   {
   		return filled;
   }
   public LineSegment[] segments()
   {
   		return l;
   }
   public static void main(String[] args) 
   {
	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}