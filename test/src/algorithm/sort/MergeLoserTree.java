package algorithm.sort;

import java.util.Scanner;

public class MergeLoserTree {

	public static int LEN = 10; // 最大归并段长
	public static int MINKEY = -1; // 默认全为正数
	public static int MAXKEY = 100; // 最大值,当一个段全部输出后的赋值

	//如果在主程序中new Array()，出现No enclosing instance of type MergeLoserTree is accessible.
	//因为main是static方法，Array在没有static修饰时，是动态方类，不能被静态方法调用
	private static class Array {
		int[] arr = new int[LEN];
		int num;
		int pos;
	}

	public static int k = 0;
	public static int count = 0;;
	public static int[] LoserTree;
	public static int[] External;

	public static void Adjust(int s) {
		int t = (s + k) / 2;
		int temp;
		while (t > 0) {
			if (External[s] > External[LoserTree[t]]) {
				temp = s;
				s = LoserTree[t];
				LoserTree[t] = temp;
			}
			t = t / 2;
		}
		LoserTree[0] = s;
	}

	public static void CreateLoserTree() {
		External[k] = MINKEY;
		int i;
		for (i = 0; i < k; i++)
			LoserTree[i] = k;
		for (i = k - 1; i >= 0; i--)
			Adjust(i);
	}

	public static void K_Merge(Array[] A) {
		int i, p;
		for (i = 0; i < k; i++) {
			p = A[i].pos;
			External[i] = A[i].arr[p];
			// cout<<External[i]<<",";
			A[i].pos++;
		}
		CreateLoserTree();
		int NO = 0;
		while (NO < count) {
			p = LoserTree[0];
			System.out.print(External[p] + ",");
			NO++;
			if (A[p].pos >= A[p].num)
				External[p] = MAXKEY;
			else {
				External[p] = A[p].arr[A[p].pos];
				A[p].pos++;
			}
			Adjust(p);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int i, j;
		count = 0;
		k = in.nextInt();
		Array[] A = new Array[k];
		for (i = 0; i < k; i++) {
			A[i] = new Array();
			A[i].num = in.nextInt();
			count = count + A[i].num;
			for (j = 0; j < A[i].num; j++) {
				A[i].arr[j] = in.nextInt();
			}
			A[i].pos = 0;
		}
		LoserTree = new int[k];
		External = new int[k + 1];

		K_Merge(A);
	}
}