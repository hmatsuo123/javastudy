package ch22.ex15;

import java.io.IOException;
import java.util.Stack;

public class Calculator {
	public static void main(String args[]) throws IOException {
		String[] str = {"1", "2", "+", "log"};

		System.out.println(new Calculator().calculate(str));
	}

	public Double calculate(String[] str) {
		Stack<Double> que = new Stack<Double>();
		Double a = 0.0;
		Double b = 0.0;
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals("+")) {
				a = que.pop();
				b = que.pop();
				que.add(b + a);
				continue;
			}else if (str[i].equals("-")) {
				a = que.pop();
				b = que.pop();
				que.add(b - a);
				continue;
			}else if (str[i].equals("*")) {
				a = que.pop();
				b = que.pop();
				que.add(b * a);
				continue;
			} else if (str[i].equals("/")) {
				a = que.pop();
				b = que.pop();
				que.add(b / a);
				continue;
			} else if (str[i].equals("%")) {
				a = que.pop();
				b = que.pop();
				que.add(b % a);
				continue;
			} else if (str[i].equals("sin")) {
				que.add(Math.sin(que.pop()));
				continue;
			} else if (str[i].equals("cos")) {
				que.add(Math.cos(que.pop()));
				continue;
			} else if (str[i].equals("tan")) {
				que.add(Math.tan(que.pop()));
				continue;
			} else if (str[i].equals("asin")) {
				que.add(Math.asin(que.pop()));
				continue;
			} else if (str[i].equals("acos")) {
				que.add(Math.acos(que.pop()));
				continue;
			} else if (str[i].equals("atan")) {
				que.add(Math.atan(que.pop()));
				continue;
			} else if (str[i].equals("atan2")) {
				a = que.pop();
                b = que.pop();
                que.add(Math.atan2(a, b));
				continue;
			} else if (str[i].equals("toRadians")) {
				que.add(Math.toRadians(que.pop()));
				continue;
			} else if (str[i].equals("toDegrees")) {
				que.add(Math.toDegrees(que.pop()));
				continue;
			} else if (str[i].equals("exp")) {
				que.add(Math.exp(que.pop()));
				continue;
			} else if (str[i].equals("sinh")) {
				que.add(Math.sinh(que.pop()));
				continue;
			} else if (str[i].equals("cosh")) {
				que.add(Math.cosh(que.pop()));
				continue;
			} else if (str[i].equals("tanh")) {
				que.add(Math.tanh(que.pop()));
				continue;
			} else if (str[i].equals("pow")) {
				que.add(Math.pow(que.pop(), que.pop()));
				continue;
			} else if (str[i].equals("log")) {
				que.add(Math.log(que.pop()));
				continue;
			} else if (str[i].equals("log10")) {
				que.add(Math.log10(que.pop()));
				continue;
			} else if (str[i].equals("sqrt")) {
				que.add(Math.sqrt(que.pop()));
				continue;
			} else if (str[i].equals("cbrt")) {
				que.add(Math.cbrt(que.pop()));
				continue;
			} else if (str[i].equals("signum")) {
				que.add(Math.signum(que.pop()));
				continue;
			} else if (str[i].equals("ceil")) {
				que.add(Math.ceil(que.pop()));
				continue;
			} else if (str[i].equals("floor")) {
				que.add(Math.floor(que.pop()));
				continue;
			} else if (str[i].equals("srintin")) {
				que.add(Math.rint(que.pop()));
				continue;
			} else if (str[i].equals("round")) {
				que.add((double) Math.round(que.pop()));
				continue;
			} else if (str[i].equals("abs")) {
				que.add(Math.abs(que.pop()));
				continue;
			} else if (str[i].equals("max")) {
				a = que.pop();
                b = que.pop();
                que.add(Math.max(a, b));
				continue;
			} else if (str[i].equals("min")) {
				a = que.pop();
                b = que.pop();
                que.add(Math.min(a, b));
				continue;
			} else if (str[i].equals("hypot")) {
				a = que.pop();
                b = que.pop();
                que.add(Math.hypot(a, b));
				continue;
			}

			que.push(Double.parseDouble(str[i]));
		}

		return que.pop();
	}
}
