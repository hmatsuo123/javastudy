package ch22.ex09;

public class CSVAnalysisTest {
	private static String pattern1 = "(.*),(.*)";
	private static String pattern2 = "([^,]*),([^,]*)";
	private static String pattern3 = "(.?*),(.?*)";
	private static String pattern4 = "(.+*),(.+*)";

	public static void main(String[] args) {
		String str1 = "a,b";
		String str2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa,bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";

		System.out.println("pattern1_short: " + analysis(pattern1, str1));
		System.out.println("pattern1_long: " + analysis(pattern1, str2));
		System.out.println("pattern2_short: " + analysis(pattern2, str1));
		System.out.println("pattern2_long: " + analysis(pattern2, str2));
		// TODO:作成途中
		/*System.out.println("pattern3_short: " + analysis(pattern3, str1));
		System.out.println("pattern3_long: " + analysis(pattern3, str2));
		System.out.println("pattern4_short: " + analysis(pattern4, str1));
		System.out.println("pattern4_long: " + analysis(pattern4, str2));*/
	}

	private static long analysis(String pattern, String source) {
		long startTime = System.nanoTime();
		for (int i = 0; i < 10000; i++)
			source.matches(pattern);

		return System.nanoTime() - startTime;
	}
}
