package algorithm;

public class Palindrome {
	// Manacher算法计算最长回文串
	public int longestPalindrome(String s) {
		int max = 0;
		int id = -1;// 记录最长回文串中心位置
		int n = s.length();
		int[] p = new int[n];
		for (int i = 1; i < n; i++) {
			if (max > i) {
				p[i] = Math.min(p[2 * id - i], max - i);
			} else {
				p[i] = 1;
			}
			while (s.charAt(i + p[i]) == s.charAt(i - p[i])) {
				p[i]++;
			}
			if (p[i] + i > max) {
				max = p[i] + i;
				id = i;
			}
		}
		return max;
	}
}
