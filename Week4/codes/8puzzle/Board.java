import java.util.Iterator;
public class Board {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private int[][] board;
    private int N;
    private int zx, zy;
    private int manhattan;
    public Board(int[][] tiles)
    {
        int n = tiles.length;
        N = n;
        manhattan = -1;
        board = new int[n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                board[i][j] = tiles[i][j];
                if(tiles[i][j]==0)
                {
                    zx = i;
                    zy = j;
                }
            }
        }
    }
                                           
    // string representation of this board
    public String toString()
    {
        StringBuilder ans = new StringBuilder();
        ans.append(N);
        ans.append('\n');
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                ans.append(board[i][j]+" ");
            }
            ans.append('\n');
        }
        return ans.toString();
    }

    // board dimension n
    public int dimension()
    {
        return N;
    }

    // number of tiles out of place
    public int hamming()
    {
        int ans = 0;
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(board[i][j]!=(N*i + j + 1) && board[i][j]!=0) ans++;
            }
        }
        return ans;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan()
    {
        if(manhattan!=-1) return manhattan;
        int ans = 0;
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                int val = board[i][j];
                val--;
                if(val!=-1)
                {
                    int posi = val/N;
                    int posj = val%N;
                    ans+=(Math.abs(posi-i) + Math.abs(posj-j));
                }
            }
        }
        manhattan = ans;
        return ans;
    }

    // is this board the goal board?
    public boolean isGoal()
    {
        return manhattan()==0;
    }

    private void swap(int i, int j, int i1, int j1)
    {
        if(i==i1 && j==j1) return;
        int temp = board[i][j];
        board[i][j] = board[i1][j1];
        board[i1][j1] = temp;
    }

    // does this board equal y?
    public boolean equals(Object Y)
    {
        if(Y instanceof Board)
        {
            Board y = (Board) Y;
            if(y.dimension()!=N) return false;
            String s = y.toString();
            String t = toString();
            for(int i = 0; i < t.length(); i++)
            {
                if(s.charAt(i)!=t.charAt(i)) return false;
            }
            return true;
        }
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors()
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
        Board[] b;
        int i;
        int n;
        public Iter()
        {
            n = N;
            b = new Board[4];
            i = 0;
            if(zx!=n-1)
            {
                swap(zx, zy, zx+1, zy);
                b[i++] = new Board(board);
                swap(zx, zy, zx+1, zy);
            }
            if(zx!=0)
            {
                swap(zx, zy, zx-1, zy);
                b[i++] = new Board(board);
                swap(zx, zy, zx-1, zy);
            }
            if(zy!=n-1)
            {
                swap(zx, zy, zx, zy+1);
                b[i++] = new Board(board);
                swap(zx, zy, zx, zy+1);
            }
            if(zy!=0)
            {
                swap(zx, zy, zx, zy-1);
                b[i++] = new Board(board);
                swap(zx, zy, zx, zy-1);
            }
            n = i;
            i = 0;
        }
        public boolean hasNext()
        {
            return i<n;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        public Board next()
        {
            return b[i++];
        }
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin()
    {
        int i1 = -1, j1 = -1, i2 = -1, j2 = -1;
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(board[i][j]!=0)
                {
                    i1 = i;
                    j1 = j;
                    break;
                }
            }
        }
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(board[i][j]!=0 && (i!=i1 || j!=j1))
                {
                    i2 = i;
                    j2 = j;
                    break;
                }
            }
        }
        swap(i1, j1, i2, j2);
        Board ans = new Board(board);
        swap(i1, j1, i2, j2);
        return ans;
    }

    // unit testing (not graded)
    public static void main(String[] args)
    {
        int n = 3;
        int[][] a = new int[n][n];
        int k = 0;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                a[i][j] = k++;
            }
        }
        Board b = new Board(a);
        Iterator<Board> iter = b.neighbors().iterator();
        while(iter.hasNext())
        {
            System.out.println(iter.next().toString());
        }
        System.out.println(b.toString());
        System.out.println(b.manhattan());
        System.out.println(b.hamming());

    }

}