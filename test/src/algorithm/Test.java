package algorithm;

public class Test {
	public static int mysteryAdd(int a, int b){
		if(b==0){
			return 0;
		}
		if(b%2==0){
			return mysteryAdd(a+a, b/2);
		}
		return mysteryAdd(a+a, b/2)+a;
	}
	public static int mysteryMul(int a, int b){
		if(b==0){
			return 1;
		}
		if(b%2==0){
			return mysteryMul(a*a, b/2);
		}
		return mysteryMul(a*a, b/2)*a;
	}
	public static boolean[][] ArraysNumber(int n){
		boolean[][] a = new boolean[n][n];
		for(int i = 1;i<n;i++){
			for(int j = 1;j<n;j++){
				if(GCD.gcd(i, j)==1){
					a[i][j] = true;
				}
			}
		}
		return a;
	}
	
	public static String mystery1(String s){
		int n = s.length();
		if(n<=1){
			return s;
		}
		String a = s.substring(0,n/2);
		String b = s.substring(n/2,n);
		return mystery1(b)+mystery1(a);
	}
	public static void main(String arg[]) {
//		double aa = Double.MAX_VALUE;
//		double bb = Double.POSITIVE_INFINITY;
//		System.out.println(mysteryAdd(2, 30));
//		System.out.println(mysteryMul(2, 5));
//		boolean[][] a = ArraysNumber(10);
//		for(int i = 0;i<a.length;i++){
//			for(int j = 0;j<a[0].length;j++){
//				System.out.print(a[i][j]+"  ");
//			}
//			System.out.println();
//		}
		
		
//		String s1 = "hhh";
//		String s2 = new String(s1);//等价于String s2 = s1;
//		s1 = "ggggg";
//		System.out.println(s1);
//		System.out.println(s2);
		
		Object a = new String();
		a = 10;
		System.out.println(a);
		Object[] aa = new String[10];
		aa[0] = 10;
		System.out.println(aa[0]);
		//System.out.println(mystery1("abcdefg"));
	}
}
