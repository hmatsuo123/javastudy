package ch22.ex14;

import java.util.StringTokenizer;

public class PrintTotal {
	public static void main(String[] args) {
        System.out.println(new PrintTotal().total("0.1 0.02 0.003 0.0004 1.0"));
    }

    public double total(String str) {
        StringTokenizer tokenizer = new StringTokenizer(str, " ");
        double total = 0;
        while (tokenizer.hasMoreTokens())
            total += Double.valueOf(tokenizer.nextToken());

        return total;
    }
}
