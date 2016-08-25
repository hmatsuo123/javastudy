package ch22.ex06;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class NextGaussian {
	public static void main(String[] args) {
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		for (int i = 0; i < 10000; i++) {
			double num = new Random().nextGaussian();
			int key = (int) (num * 1);
			Integer count = map.get(key);
			if (count == null) count = 0;
			map.put(key, ++count);
		}
		for (int i : map.keySet())
			System.out.printf("%2d: %s%n", i , printAsterisk(map.get(i)));
	}

	private static String printAsterisk(int num) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++)
			if (i % 100 == 0)
				sb.append("*");

		return sb.toString();
	}
}
