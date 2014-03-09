package algorithm;

import java.util.Stack;

//假设为省略任何括号，数字和字符以空格分开
public class ExpressionDijkstra {
	public static double evaluate(String s) {
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();
		String[] parts = s.split(" ");
		for (int i = 0; i < parts.length; i++) {
			String ss = parts[i];
			if (ss.equals("(")) {
				;
			} else if (ss.equals("+") || ss.equals("-") || ss.equals("*")
					|| ss.equals("/") || ss.equals("sqrt")) {
				ops.push(ss);
			} else if (ss.equals(")")) {
				String op = ops.pop();
				double v = vals.pop();
				if (op.equals("+")) {
					v = vals.pop() + v;
				} else if (op.equals("-")) {
					v = vals.pop() - v;
				} else if (op.equals("*")) {
					v = vals.pop() * v;
				} else if (op.equals("/")) {
					v = vals.pop() / v;
				} else if (op.equals("sqrt")) {
					v = Math.sqrt(v);
				}
				vals.push(v);

			} else {
				vals.push(Double.parseDouble(ss));
			}
		}
		return vals.pop();
	}
	public static void main(String arg[]) {
		double d = evaluate("( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )");
		System.out.println(d);
	}
}
