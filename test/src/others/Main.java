package others;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), j, length1, length2;
		String s1, s2;

		for (int i = 0; i < n; i++) {
			s1 = Integer.toBinaryString(sc.nextInt());
			length1 = s1.length();
			for (j = 0; j < 16 - length1; j++) {
				s1 = "0" + s1;
			}
			s2 = Integer.toBinaryString(sc.nextInt());
			length2 = s2.length();
			for (j = 0; j < 16 - length2; j++) {
				s2 = "0" + s2;
			}
			if (test(s1, s2)) {
				System.out.println("YES");
			} else {
				if (test(s2, s1)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}

	}

	static boolean test(String s1, String s2) {
		boolean flag = false;
		for (int i = 0; i < 16; i++) {
			if (s2.equals(s1.substring(i) + s1.substring(0, i))) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
