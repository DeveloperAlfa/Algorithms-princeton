
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
public class PercolationStats {

    private double[] x;
    private int T;
    private int N;
    private double m, sd;

    private double simulate(Percolation p)
    {
        while(!p.percolates())
        {
            int x = StdRandom.uniform(1, N+1);
            int y = StdRandom.uniform(1, N+1);
            //System.out.println(x+" "+y);
            if(!p.isOpen(x, y))
            {
                p.open(x, y);
            }            
        }
        return (double)(p.numberOfOpenSites())/(N*N);
    }
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if(n <= 0 || trials <= 0) throw new IllegalArgumentException();
        x = new double[trials];
        T = trials;
        N = n;
        m = -1;
        sd = -1;
        for (int i = 0; i < trials; i++) 
        {
            //System.out.println(i+"");
            x[i] = simulate(new Percolation(N));   
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        if(m!=-1) return m;
        m = StdStats.mean(x);
        return m;
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        if(sd!=-1) return sd;
        sd = StdStats.stddev(x);
        return sd;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean() - (1.96*stddev()/ (Math.sqrt(T)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + (1.96*stddev()/ (Math.sqrt(T)));
    }

   // test client (see below)
   public static void main(String[] args)
   {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(a, b);

        String confidence = p.confidenceLo() + ", " + p.confidenceHi();
        StdOut.println("mean                    = " + p.mean());
        StdOut.println("stddev                  = " + p.stddev());
        StdOut.println("95% confidence interval = " + confidence);
   }

}