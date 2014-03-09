package worksApp;

import java.util.Arrays;
import java.util.List;

//定义S[i,j]={a[k]∈S：f[i]≤s[k]<f[k]≤s[j]}，即S[i,j]是所有与a[i]和a[j]相容的集合。
//
//a[0]和a[n+1]，并定义f[0]=0,s[n+1]=∞。
//
//按照结束时间单调递增排列，即：f[0]≤f[1]≤f[2]≤...≤f[n]≤f[n+1]
//
//若i>=j,则S[i,j]=∅。S[0,n+1]中选择最大的相容子集。
//假设S[i,j]的一个解包括a[k],即f[i]≤s[k]<f[k]≤s[j]，两个子问题S[i,k]和S[k+1,j]
//
//设c[i,j]为S[i,j]最大相容子集的收益
//c[i,j]=0                       S[i,j]=∅
//c[i,j]=max{c[i,k]+c[k+1]+a[k]}    i<k<j S[i,j]≠∅

public class Problem2 {
	public int getMaxWorkingTime(List<Interval> intervals) {
		int size = intervals.size();
		int[] s = new int[size + 2];
		int[] e = new int[size + 2];
		int[][] C = new int[size + 2][size + 2];
		e[0] = 0;
		s[size + 1] = 24 * 60;
		e[size + 1] = 24 * 60;
		int index = 1;
		for (Interval it : intervals) {
			s[index] = it.getBeginMinuteUnit();
			e[index++] = it.getEndMinuteUnit();
		}
		QuickSort.quickSort(e, s, 0, e.length - 1);
		for (int i = 0; i <= size; i++) {
			C[i][i] = 0;
			C[i][i + 1] = 0;
		}
		C[size + 1][size + 1] = 0;

		for (int i = 0; i <= size - 1; i++) {
			for (int j = i + 2; j <= size + 1; j++) {
				// int temp = Integer.MIN_VALUE;
				for (int k = i + 1; k < j; k++) {
					if (e[i] <= s[k] && e[k] <= s[j]) {
						C[i][j] = Math.max(C[i][j], C[i][k] + C[k][j] + e[k]
								- s[k]);
					}
				}
			}
		}
		return C[0][size + 1];
	}

	public void testProblem1Usage() {
		Problem2 p = new Problem2();

//		Interval interval1 = new Interval("08:00", "12:00");
//		Interval interval2 = new Interval("06:00", "09:00");
//		Interval interval3 = new Interval("11:00", "13:30");
//		List<Interval> figure1 = Arrays.asList(interval1, interval2, interval3);

		 Interval interval1 = new Interval("06:00", "08:30");
		 Interval interval2 = new Interval("09:00", "11:30");
		 Interval interval3 = new Interval("12:30", "14:00");
		 Interval interval4 = new Interval("09:00", "11:00");
		 Interval interval5 = new Interval("08:00", "09:00");
		 Interval interval6 = new Interval("10:30", "14:00");
		 List<Interval> figure1 = Arrays.asList(interval1, interval2,
		 interval3,
		 interval4, interval5, interval6);
		System.out.println(p.getMaxWorkingTime(figure1));

	}

	public static void main(String[] args) {
		Problem2 p = new Problem2();
		p.testProblem1Usage();
	}
}
