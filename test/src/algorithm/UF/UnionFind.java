package algorithm.UF;

import java.util.Scanner;

/****************************************************************************
 * Compilation: javac UnionFind.java Execution: java UnionFind < input.txt
 * Dependencies: StdIn.java StdOut.java Data files:
 * http://algs4.cs.princeton.edu/15uf/tinyUF.txt
 * http://algs4.cs.princeton.edu/15uf/mediumUF.txt
 * http://algs4.cs.princeton.edu/15uf/largeUF.txt
 * 
 * Weighted quick-union by rank with path compression by halving.
 * 
 * % java UnionFind < tinyUF.txt 4 3 3 8 6 5 9 4 2 1 5 0 7 2 6 1 2 components
 * 
 ****************************************************************************/

public class UnionFind {
	private int[] id; // id[i] = parent of i
	private byte[] rank; // rank[i] = rank of subtree rooted at i (cannot be more than 31)
	private int count; // number of components

	public UnionFind(int N) {
		if (N < 0)
			throw new IllegalArgumentException();
		count = N;
		id = new int[N];
		rank = new byte[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			rank[i] = 0;
		}
	}

	/**
	 * Returns the component identifier for the component containing site p
	 * 
	 * @param p
	 *            the integer representing one object
	 * @return the component identifier for the component containing site p
	 * @throws java.lang.IndexOutOfBoundsException
	 */
	public int find(int p) {
		if (p < 0 || p >= id.length)
			throw new IndexOutOfBoundsException();
		while (p != id[p]) {
			id[p] = id[id[p]]; // path compression by halving
			p = id[p];
		}
		return p;
	}

	/**
	 * Returns the number of components.
	 * 
	 * @return the number of components (between <tt>1</tt> and <tt>N</tt>)
	 */
	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	/**
	 * Merges the component containing site p and q
	 * 
	 * @param p
	 *            the integer representing one site
	 * @param q
	 *            the integer representing the other site
	 * @throws java.lang.IndexOutOfBoundsException
	 */
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if (i == j)
			return;

		// make root of smaller rank point to root of larger rank
		if (rank[i] < rank[j])
			id[i] = j;
		else if (rank[i] > rank[j])
			id[j] = i;
		else {
			id[j] = i;
			rank[i]++;
		}
		count--;
	}

	/**
	 * Reads in a an integer N and a sequence of pairs of integers (between 0
	 * and N-1) from standard input, where each integer in the pair represents
	 * some site; if the sites are in different components, merge the two
	 * components and print the pair to standard output.
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		UnionFind uf = new UnionFind(N);
		while (in.hasNext()) {
			int p = in.nextInt();
			int q = in.nextInt();
			if (uf.connected(p, q))
				continue;
			uf.union(p, q);
			System.out.println(p + " " + q);
		}
		System.out.println(uf.count() + " components");
	}
}
