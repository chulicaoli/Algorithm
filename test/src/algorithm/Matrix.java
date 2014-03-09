package algorithm;

public class Matrix {
	// 向量点乘
	public static double dot(double[] x, double[] y) {
		double sum = 0.0;
		for (int i = 0; i < x.length; i++) {
			sum += x[i] * y[i];
		}
		return sum;
	}

	// 矩阵乘积
	public static double[][] mult(double[][] x, double[][] y) {
		int m = x.length;
		int k = x[0].length;
		int n = y[0].length;
		double[][] r = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int t = 0; t < k; t++) {
					r[i][j] += x[i][t] * y[t][j];
				}
			}
		}
		return r;
	}

	// 矩阵转置
	public static double[][] transpose(double[][] x) {
		int m = x.length;
		int n = x[0].length;
		double[][] r = new double[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				r[i][j] += x[j][i];
			}
		}
		return r;
	}

	// 矩阵和向量之积
	public static double[] multS(double[][] x, double[] a) {
		assert (x[0].length == a.length);
		double[] m = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				m[i] += x[i][j] * a[j];
			}
		}
		return m;
	}

	// 向量和矩阵之积
	public static double[] multST(double[] a, double[][] x) {
		assert (x.length == a.length);
		double[] m = new double[x[0].length];
		for (int i = 0; i < x[0].length; i++) {
			for (int j = 0; j < x.length; j++) {
				m[i] += x[j][i] * a[j];
			}
		}
		return m;
	}

	public static void main(String arg[]) {
		double[][] x = { { 1, 2 }, { 3, 4 }, { 1, 5 } };
		double[][] y = { { 5, 6 }, { 7, 8 } };
		
		//double[][] r = transpose(x);
//		for (int i = 0; i < r.length; i++) {
//			for (int j = 0; j < r[0].length; j++) {
//				System.out.print(r[i][j] + "  ");
//			}
//			System.out.println();
//		}
		
		double[] a = {2,6};
		//double[] ans = multS(x,a);
		double[] aa = {2,6,1};
		double[] ans = multST(aa,x);
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + "  ");
		}
	}
}
