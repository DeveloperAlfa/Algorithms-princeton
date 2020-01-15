import edu.princeton.cs.algs4.StdRandom;
public class QuickSort3
{
	private static void QuickSort(int a[])
	{
		StdRandom.shuffle(a);
		int lo = 0, hi = a.length-1;
		sort(a, lo, hi);
	}
	private static int partition(int[] a, int lo, int hi)
	{
		int lt, i, j;
		lt = i = lo+1;
		j = hi;
		while(i<=j)
		{
			if(a[i]<a[lo])
			{
				int temp = a[lt];
				a[lt] = a[i];
				a[i] = temp;
				i++;
				lt++;
			}
			else if(a[i]==a[lo]) i++;
			else
			{
				int temp = a[j];
				a[j] = a[i];
				a[i] = temp;
				j--;
			}
		}
		int temp = a[j];
		a[j] = a[lo];
		a[lo] = temp;
		return j;
	}
	private static void sort(int[] a, int lo, int hi)
	{
		if(lo>=hi) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}
	public static void main(String[] args) {
		int a[] = {1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0};
		QuickSort(a);
		for(int i: a) System.out.print(i+" ");
	}
}