package ch09.ex03;

/* より簡潔にできる案は思いつきませんでした */

public class PascalTriangle {
	final private int size;

	public PascalTriangle(int size) {
		this.size = size;
	}

	public int[][] create() {
		int[][] str = new int[size][];
		int jSize;
		for (int i = 0; i < size; i++) {
			str[i] = new int[i + 1];
			jSize = i + 1;
			for (int j = 0; j < jSize; j++) {
				if ((j == 0) || (j == (jSize - 1))) {
					str[i][j] = 1;
				} else {
					if (i != 0) {
						str[i][j] = str[i - 1][j - 1] + str[i - 1][j];
					}
				}
			}
		}
		return str;
	}

	public void show(int[][] str) {
		int spaceCount;
		for (int i = 0; i < str.length; i++) {
			//スペースを追加。真ん中に表示されるように数値(4桁)の半分のスペースを追加する
			spaceCount = (size - i) * 2;
			for (int j = 0; j < spaceCount; j++) {
				System.out.printf(" ");
			}

			for (int j = 0; j < str[i].length; j++) {
				System.out.printf("%4d", str[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] srgs) {
		PascalTriangle pascalTriangle = new PascalTriangle(12);
		pascalTriangle.show(pascalTriangle.create());
	}
}
