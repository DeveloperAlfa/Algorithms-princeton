import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
public class BruteCollinearPoints {
	private LineSegment[] l;
	private int sz;
	private int filled;
   private double INF = Double.POSITIVE_INFINITY;
   private double NINF = Double.NEGATIVE_INFINITY;
   public BruteCollinearPoints(Point[] points)
   {
   		if(points==null) throw new IllegalArgumentException();
   		sz = 2;
   		filled = 0;
   		int n = points.length;
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
   		Arrays.sort(points);
   		l =  new LineSegment[sz];
   		for(int i = 0; i < n; i++)
   		{
   			for(int j = i+1; j < n; j++)
   			{
   				for(int k = j+1; k < n; k++)
   				{
   					for(int m = k+1; m < n; m++)
   					{
   						double s1 = points[i].slopeTo(points[j]);
   						double s2 = points[i].slopeTo(points[k]);
   						double s3 = points[i].slopeTo(points[m]);
   						if(s1==s2 && s2==s3)
   						{
   							push(new LineSegment(points[i], points[m]));
   						}
   					}
   				}
   			}
   		}
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
   		LineSegment[] ans = new LineSegment[filled];
         for(int i = 0; i < filled; i++) ans[i] = l[i];
         return ans; 
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
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}