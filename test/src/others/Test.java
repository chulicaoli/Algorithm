package others;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int j;
		while (T-- > 0) {
			int p = in.nextInt();
			int q = in.nextInt();
			int temp;
			for (j = 0; j < 16; j++) {
				temp = ((p << j) | (p >> (16 - j))) & 65535;
				if (temp == q) {
					System.out.println("YES");
					break;
				}
			}
			if (j >= 16) {
				System.out.println("NO");
			}
		}
	}

}
