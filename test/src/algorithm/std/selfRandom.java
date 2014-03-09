package algorithm.std;

import java.util.Random;

//使用java.util.Random类来产生一个随机数发生器。它有两种形式的构造函数，分别是Random()和Random(long seed)。
//Random()使用当前时间即System.currentTimeMillis()作为发生器的种子，Random(long seed)使用指定的seed作为发生器的种子。
//
//随机数发生器(Random)对象产生以后，通过调用不同的method：nextInt()、nextLong()、nextFloat()、nextDouble()等获得不同类型随机数。
public class selfRandom {

	// 产生n个1-10之间的随机数
	public static void getRangeRandomNumber(int n) {
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			System.out.print((Math.abs(random.nextInt()) % 10 + 1) + "    ");
		}
		System.out.println();
	}

	// 0到n-1之间的正数
	public static int uniform(int n) {
		Random random = new Random();
		return Math.abs(random.nextInt()) % n;
	}

	// 随机洗牌
	public static void shuffle(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			// 将a[i]和a[i...N-1]中的任意一个元素交换
			int r = i + uniform(n - i);
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	public static void main(String arg[]) {
		getRangeRandomNumber(5);
		int N = 30;
		int M = 10;
		int[][] ans = new int[M][M];
		for (int t = 1; t <= N; t++) {
			int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			shuffle(a);
			for (int i = 0; i < a.length; i++) {
						ans[i][a[i]] += 1;

			}
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					System.out.print(ans[i][j] + "   ");
				}
				System.out.println();
			}
		}
	}
}
