package worksApp;

import java.util.Arrays;
import java.util.List;

public class Problem1 {

	public int getMaxIntervalOverlapCount(List<Interval> intervals) {
		if(intervals==null||intervals.size()==0){
			return 0;
		}
		int size = intervals.size();
		int[] data = new int[size*2];
		int[] flag = new int[size*2];
		int[] count = new int[size*2];
		int max = 0;
		int index = 0;
		for(Interval it: intervals){
			data[index] = it.getBeginMinuteUnit();
			flag[index++] =1;
			data[index] = it.getEndMinuteUnit();
			flag[index++] =-1;
		}
		QuickSort.quickSort(data, flag, 0, data.length - 1);
		count[0] = flag[0];
		for(int i = 1;i<data.length;i++){
			count[i] = count[i-1]+flag[i];
			max=count[i]>max?count[i]:max;
		}
		return max;		
	}

	public void testProblem1Usage() {
		Problem1 p = new Problem1();
		// example: Figure 1
		Interval interval1 = new Interval("08:00", "12:00");
		Interval interval2 = new Interval("06:00", "09:00");
		Interval interval3 = new Interval("11:00", "13:30");
		List<Interval> figure1 = Arrays.asList(interval1, interval2, interval3);
		System.out.println(p.getMaxIntervalOverlapCount(figure1));
		// example: Figure 2
		List<Interval> figure2 = Arrays.asList(new Interval("09:00", "12:30"),
				new Interval("06:00", "09:30"), new Interval("12:00", "14:30"),
				new Interval("10:00", "10:30"), new Interval("11:00", "13:30"));
		System.out.println(p.getMaxIntervalOverlapCount(figure2));
	}
	public static void main(String[] args) {
		Problem1 p = new Problem1();
		p.testProblem1Usage();
	}
}
