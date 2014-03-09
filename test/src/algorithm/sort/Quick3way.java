package algorithm.sort;

/**
 *  The Quick3way class provides static methods for sorting an
 *  array using quicksort with 3-way partitioning.
 *  在出现大量重复元素时，三向切分算法将排序时间从线性对数级降低到线性级别。
 *  其效率会远远高于一般快速排序和归并排序算法
 */
import algorithm.std.StdRandom;

/*************************************************************************
 * Compilation: javac Quick3way.java Execution: java Quick3way < input.txt
 * Dependencies: StdOut.java StdIn.java Data files:
 * http://algs4.cs.princeton.edu/23quicksort/tiny.txt
 * http://algs4.cs.princeton.edu/23quicksort/words3.txt
 * 
 * Sorts a sequence of strings from standard input using 3-way quicksort.
 * 
 * % more tiny.txt S O R T E X A M P L E
 * 
 * % java Quick3way < tiny.txt A E E L M O P R S T X [ one string per line ]
 * 
 * % more words3.txt bed bug dad yes zoo ... all bad yet
 * 
 * % java Quick3way < words3.txt all bad bed bug dad ... yes yet zoo [ one
 * string per line ]
 * 
 *************************************************************************/

public class Quick3way {

	private Quick3way() {
	}

	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
		assert isSorted(a);
	}

	// quicksort the subarray a[lo .. hi] using 3-way partitioning
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int lt = lo, gt = hi;
		Comparable v = a[lo];
		int i = lo;
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0)
				exch(a, lt++, i++);
			else if (cmp > 0)
				exch(a, i, gt--);
			else
				i++;
		}

		// a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
		assert isSorted(a, lo, hi);
	}

	/***********************************************************************
	 * Helper sorting functions
	 ***********************************************************************/

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return (v.compareTo(w) < 0);
	}

	// does v == w ?
	private static boolean eq(Comparable v, Comparable w) {
		return (v.compareTo(w) == 0);
	}

	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	/***********************************************************************
	 * Check if array is sorted - useful for debugging
	 ***********************************************************************/
	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	// print array to standard output
	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + ",");
		}
		System.out.println();
	}

	/**
	 * Reads in a sequence of strings from standard input; 3-way quicksorts
	 * them; and prints them to standard output in ascending order.
	 */
	public static void main(String[] args) {
		String[] a = { "R", "B", "W", "W", "R", "W", "B", "R", "R", "W", "B",
				"R" };
		Quick3way.sort(a);
		show(a);
	}

}
