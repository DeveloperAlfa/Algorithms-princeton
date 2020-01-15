import edu.princeton.cs.algs4.StdRandom;
public class QuickSort1
{
	public static void QuickSort(int[] a)
	{
		int n = a.length;
		StdRandom.shuffle(a);
		sort(a, 0, n-1);
	}
	private static void sort(int[] a, int start, int end)
	{
		if(start>=end) return;
		int pivot = a[start];
		int i = start+1, j = end;
		while(i<=j)
		{
			while(i<=j && a[i]<=pivot) i++;
			while(i<=j && a[j]>pivot) j--;
			if(i>j) break;
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
		a[start] = a[j];
		a[j] = pivot;
		sort(a, start, j-1);
		sort(a, j+1, end);
	}
	public static void main(String[] args) 
	{
		int a[] = {7, 7, 6, 6, 5, 4, 3};
		QuickSort(a);
		for(int i: a) System.out.print(i+" "); 
	}
}