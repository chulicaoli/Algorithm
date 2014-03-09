package algorithm;

import java.util.Arrays;

public class NumberFilter {
	//数组中最大数
	public static int maxNumber(int[] a){
		int max  = Integer.MIN_VALUE;
		for(int i = 0;i<a.length;i++){
			max = Math.max(a[i], max);
		}
		return max;
	}
	
	//数组中最小数
	public static int minNumber(int[] a){
		int min  = Integer.MAX_VALUE;
		for(int i = 0;i<a.length;i++){
			min = Math.max(a[i], min);
		}
		return min;
	}
	
	//中位数
	public static int medianNumber(int[] a){
		Arrays.sort(a);
		int n = a.length;
		if(a.length%2==1){
			return a[n/2];
		}else {
			return (a[n/2]+a[n/2-1])/2;
		}
	}
	
	//平方和
	public static int squareTotal(int[] a){
		int sum = 0;
		for(int i = 0;i<a.length;i++){
			sum += a[i]*a[i];
		}
		return sum;
	}
	
	//平均值
	public static double average(int[] a){
		double avg = 0;
		for(int i = 0;i<a.length;i++){
			avg += a[i];
		}
		return avg/(1.0*a.length);
	}
}
