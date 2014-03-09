package worksApp;

import java.util.Arrays;

public class QuickSort {

	public static void quickSort(int[] data, int[] flag, int left, int right) {
		int i, j;
		int middle, temp, middleFlag,tempFlag;
		i = left;
		j = right;
		middle = data[left];
		middleFlag = flag[left];
		while (i < j) {
			while ((++i) < right  && data[i] <= middle)
				;
			while (j > left && data[j] >= middle){
				j--;
			}
			if (i >= j)
				break;
			temp = data[i];
			tempFlag = flag[i];

			data[i] = data[j];
			flag[i] = flag[j];

			data[j] = temp;
			flag[j] = tempFlag;

		}
		data[left] = data[j];
		data[j] = middle;
		flag[left]=flag[j];
		flag[j]=middleFlag;
		if (left < j)
			quickSort(data, flag, left, j);

		if (right > i)
			quickSort(data, flag, i, right);
	}

	public static void main(String[] args) {
		int[] data = new int[] { 38, 65, 97, 38,12, 27 ,100,49};
		int[] flag = new int[8];
		for(int i =0;i<4;i++){
			flag[i]=1;
		}
		for(int i =4;i<8;i++){
			flag[i]=-1;
		}
		quickSort(data, flag, 0, data.length - 1);
		System.out.println(Arrays.toString(data));
		System.out.println(Arrays.toString(flag));

	}

}