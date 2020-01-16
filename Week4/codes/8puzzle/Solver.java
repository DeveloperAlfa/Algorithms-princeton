import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
public class Solver {

    private Board board;
    private Board[] steps;
    private boolean solvable;
    private boolean solved;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        board = initial;
        solved = false;
        solvable = isSolvable();
        solved  = true;
    }
    private class BKey implements Comparable<BKey>
    {
        private Board self;
        private int priority;

        public BKey(Board self, int priority)
        {
            this.self = self;
            this.priority = priority;
        }
        
        public int compareTo(Solver.BKey that)
        {
            if(this.priority<that.priority) return -1;
            else if(this.priority==that.priority) return 0;
            return 1;
        }

        public Board getBoard()
        {
            return self;
        }
    }
    private class Solve
    {
        private Board self;
        private Board prev;
        private MinPQ<BKey> q;
        private Board[] steps;
        int sz = 2;
        int moves;
        public Solve(Board b)
        {
            self = b;
            prev = b;
            q = new MinPQ<BKey>();
            moves = 0;
            steps = new Board[sz];
        }
        private Board[] nextGen()
        {
            Iterator<Board> iter = self.neighbors().iterator();
            Board[] boards = new Board[4];
            int i = 0;
            while(iter.hasNext())
            {
                Board b = iter.next();
                if(!b.equals(prev)) boards[i++] = b;
            }
            return boards;
        }
        public Board next()
        {
            Board[] next = nextGen();
            for(Board b: next)
            {
                if(b!=null)
                {
                    q.insert(new BKey(b, moves + b.manhattan()));
                }

            }
            prev = self;
            self = q.delMin().getBoard();
            if(moves==sz) expand();
            steps[moves++] = self;
            return self;
        }
        private void expand()
        {
            Board[] prev = steps;
            steps = new Board[2*sz];
            for(int i = 0; i < sz; i++)
            {
                steps[i] = prev[i];
            }
            sz = sz*2;
        }
        public Board[] getSteps()
        {
            Board[] ans = new Board[moves];
            for(int i = 0; i < moves; i++)
            {
                ans[i] = steps[i];
            }
            return ans;
        }

    }
    
    public boolean isSolvable()
    {
        if(solved==true) return solvable;
        solved = true; 
        Board self = board.twin().twin();
        Board twin = self.twin();
        Solve s = new Solve(self);
        Solve t = new Solve(twin);
        while(!twin.isGoal() && !self.isGoal())
        {
            self = s.next();
            twin = t.next();
        }
        if(self.isGoal()) 
        {
            steps = s.getSteps();
            return true;
        }
        steps = t.getSteps();
        return false;
    }

    // min number of moves to solve initial board
    public int moves()
    {
        if(solved && solvable) return steps.length;
        if(!solved) 
        {
            solvable = isSolvable();
            return moves();
        }
        return -1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution()
    {
        return new MyIter();
    }

    private class MyIter implements Iterable<Board>
    {
        public Iterator<Board> iterator()
        {
            return new Iter();
        }
    }
    private class Iter implements Iterator<Board>
    {
        int i = -1;
        public boolean hasNext()
        {
            return solved && solvable && i<steps.length;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        public Board next()
        {
            if(i++==-1) return board;
            return steps[i-1];
        }
    }

    public static void main(String[] args) 
    {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}