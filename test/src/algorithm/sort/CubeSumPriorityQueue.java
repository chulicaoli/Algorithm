package algorithm.sort;

/*************************************************************************
 *  Compilation:  javac CubeSum.java
 *  Execution:    java CubeSum N
 *  Dependencies: MinPQ.java
 * 
 *  Print out integers of the form a^3 + b^3 in sorted order, where
 *  0 <= a <= b <= N.
 *
 *  % java CubeSum 10
 *  0 = 0^3 + 0^3
 *  1 = 0^3 + 1^3
 *  2 = 1^3 + 1^3
 *  8 = 0^3 + 2^3
 *  9 = 1^3 + 2^3
 *  ...
 *  1729 = 9^3 + 10^3
 *  1729 = 1^3 + 12^3
 *  ...
 *  3456 = 12^3 + 12^3
 *
 *  Remarks
 *  -------
 *   - Easily extends to handle sums of the form f(a) + g(b)
 *   - Prints out a sum more than once if it can be obtained
 *     in more than one way, e.g., 1729 = 9^3 + 10^3 = 1^3 + 12^3
 *
 *************************************************************************/

public class CubeSumPriorityQueue implements Comparable<CubeSumPriorityQueue> {
    private final int sum;
    private final int i;
    private final int j;

    public CubeSumPriorityQueue(int i, int j) {
        this.sum = i*i*i + j*j*j;
        this.i = i;
        this.j = j;
    }

    public int compareTo(CubeSumPriorityQueue that) {
        if (this.sum < that.sum) return -1;
        if (this.sum > that.sum) return +1;
        return 0;
    }

    public String toString() {
        return sum + " = " + i + "^3" + " + " + j + "^3";
    }


    public static void main(String[] args) {

        int N = 4;

        // initialize priority queue
        MinPQ<CubeSumPriorityQueue> pq = new MinPQ<CubeSumPriorityQueue>();
        for (int i = 0; i <= N; i++) {
            pq.insert(new CubeSumPriorityQueue(i, i));
        }

        // find smallest sum, print it out, and update
        while (!pq.isEmpty()) {
        	CubeSumPriorityQueue s = pq.delMin();
            System.out.println(s);
            if (s.j < N) pq.insert(new CubeSumPriorityQueue(s.i, s.j + 1));
        }
    }

}
