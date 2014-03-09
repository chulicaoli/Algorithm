package others;

import java.util.Scanner;

public class ReflectTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		while (n-- > 0) {
			String s1 = in.next();
			String s2 = in.next();
			if (s1.length() < s2.length()) {
				System.out.println("false");
			} else {
				if (s1.concat(s1).indexOf(s2) != -1) {
					System.out.println("true");
				} else {
					System.out.println("false");
				}
			}
			in.nextLine();
		}

	}
}
