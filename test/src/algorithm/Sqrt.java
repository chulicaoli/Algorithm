package algorithm;

public class Sqrt {
	//牛顿迭代
	public static double sqrt(double c) {
		if(c<0){
			return Double.NaN;
		}
		double err = 1e-8;
		double t = c;
		while(Math.abs(t-c/t)>err*t){
			t = (t+c/t)/2.0;
		}
		return t;
	}
	public static void main(String arg[]) {
		System.out.printf("%.15f",sqrt(3));
	}
}
