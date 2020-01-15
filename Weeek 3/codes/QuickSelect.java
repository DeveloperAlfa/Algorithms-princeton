import edu.princeton.cs.algs4.StdRandom;
public class QuickSelect
{
	private static int partition(int[] a, int lo, int hi)
	{
		if(lo>=hi) return -1;
		int i = lo+1, j = hi;
		while(i<=j)
		{
			while(i<=j && a[i]<=a[lo]) i++;
			while(i<=j && a[j]>a[lo]) j--;
			if(i>j) break;
			else
			{
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			} 
		}
		int temp = a[j];
		a[j] = a[lo];
		a[lo] = temp;
		return j;
	}
	private static int quickselect(int[] a, int k)
	{
		StdRandom.shuffle(a);
		int j = -1;
		int lo = 0, hi = a.length-1;
		while(j!=k)
		{
			j = partition(a, lo, hi);
			if (j > k) hi = j-1;
			else if(j < k) lo = j+1;
		}
		return a[k];
	}
	public static void main(String[] args) {
		int[] a = {1,6,3,9,2};
		System.out.println(quickselect(a, 3));
	}
}