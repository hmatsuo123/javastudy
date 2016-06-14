package ch14.ex07;

public class Babble extends Thread{
	static boolean doYield;
	static int howOften;
	private String word;

	Babble(String whatToSay) {
		word = whatToSay;
	}

	public void run() {
		for (int i = 0; i < howOften; i++) {
			System.out.println(word);
			if (doYield) {
				Thread.yield();
			}
		}
	}

	public static void main(String[] args) {
		doYield = new Boolean(args[0]).booleanValue();
		howOften = Integer.parseInt(args[1]);

		for (int i = 2; i < args.length; i++)
			new Babble(args[i]).start();
	}
}

/*
 * 結果
 * 入力値：true 2 aaa bbb
 * 【１回目】
 * bbb
 * bbb
 * aaa
 * aaa
 *
 * 【２回目】
 * bbb
 * bbb
 * aaa
 * aaa
 *
 *【３回目】
 * aaa
 * bbb
 * bbb
 * aaa
 *
 * 【４回目】
 * aaa
 * bbb
 * bbb
 * aaa
 *
 * 【５回目】
 * bbb
 * bbb
 * aaa
 * aaa
 * */