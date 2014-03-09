package algorithm;


public class BinarySearch {
	// 存在时，返回第一次找到的下标；不存在，返回-1
	public static int binarySearch(int key, int[] a) {
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (key < a[mid]) {
				high = mid - 1;
			} else if (key > a[mid]) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	// 存在时，返回下标最小的；不存在，返回-1
	public static int binarySearchLow(int key, int[] a) {
		int low = 0;
		int high = a.length - 1;
		int flag = -1;// 标记是否找到
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (key < a[mid]) {
				high = mid - 1;
			} else if (key > a[mid]) {
				low = mid + 1;
			} else {
				flag = mid;
				high = mid - 1;
			}
		}
		return flag;
	}

	// 存在时，返回下标最大的；不存在，返回-1
	public static int binarySearchUp(int key, int[] a) {
		int low = 0;
		int high = a.length - 1;
		int flag = -1;// 标记是否找到
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (key < a[mid]) {
				high = mid - 1;
			} else if (key > a[mid]) {
				low = mid + 1;
			} else {
				flag = mid;
				low = mid + 1;
			}
		}
		return flag;
	}

	// 存在时，返回下标区间；不存在，返回[a.length，-1]
	public static int[] binarySearchRange(int key, int[] a) {
		int low = 0;
		int high = a.length - 1;
		int[] res = { a.length, -1 };// 标记是否找到

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (key < a[mid]) {
				high = mid - 1;
			} else if (key > a[mid]) {
				low = mid + 1;
			} else {
				res[0] = mid;
				high = mid - 1;
			}
		}
		low = 0;
		high = a.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (key < a[mid]) {
				high = mid - 1;
			} else if (key > a[mid]) {
				low = mid + 1;
			} else {
				res[1] = mid;
				low = mid + 1;
			}
		}
		return res;
	}

	// 在循环有序数组中查找指定元素
	public static int binarySearchCycle(int key, int[] a) {
		int low = 0;
		int high = a.length - 1;
		int flag = -1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if(a[low]<=a[mid]){
				if(key>=a[low]&&key<=a[mid]){
//					int[] b = Arrays.copyOfRange(a, low, mid+1);
//					return binarySearch(key, b);
					if(key==a[low]){
						flag = low;
					}
					if(key==a[mid]){
						flag = mid;
					}
					high = mid-1;
				}else{
//					int[] b = Arrays.copyOfRange(a, mid, high+1);
//					return mid+binarySearchCycle(key, b);
					low = mid+1;
				}
			}else{
				if(key>=a[mid]&&key<=a[high]){
//					int[] b = Arrays.copyOfRange(a, mid, high+1);
//					return mid+binarySearch(key, b);
					if(key==a[high]){
						flag = high;
					}
					if(key==a[mid]){
						flag = mid;
					}
					low = mid+1;
				}else{
//					int[] b = Arrays.copyOfRange(a, low, mid+1);
//					return binarySearchCycle(key, b);
					high = mid-1;
				}
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		int[] a = { 12,16,18,20,41,100,1,4,6,9};
		//int[] res = binarySearchRange(6, a);
		//System.out.println(res[0] + " " + res[1]);
		System.out.println(binarySearchCycle(100, a));
	}
}
