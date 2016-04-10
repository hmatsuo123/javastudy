package ch10.ex04;

public class PascalTriangle {
	final private int size;

	public PascalTriangle(int size) {
		this.size = size;
	}

	public int[][] create() {
		int[][] str = new int[size][];
		int jSize;
		int i = 0;
		while (i < size) {
			str[i] = new int[i + 1];
			jSize = i + 1;
			int j = 0;
			while (j < jSize) {
				if ((j == 0) || (j == (jSize - 1))) {
					str[i][j] = 1;
				} else {
					if (i != 0) {
						str[i][j] = str[i - 1][j - 1] + str[i - 1][j];
					}
				}
				j++;
			}
			i++;
		}
		return str;
	}

	public void show(int[][] str) {
		int spaceCount;
		int i = 0;
		while (i < str.length) {
			//スペースを追加。真ん中に表示されるように数値(4桁)の半分のスペースを追加する
			spaceCount = (size - i) * 2;
			int j = 0;
			while (j < spaceCount) {
				System.out.printf(" ");
				j++;
			}

			j = 0;
			while (j < str[i].length) {
				System.out.printf("%4d", str[i][j]);
				j++;
			}
			System.out.println();
			i++;
		}
	}

	public static void main(String[] srgs) {
		PascalTriangle pascalTriangle = new PascalTriangle(12);
		pascalTriangle.show(pascalTriangle.create());
	}
}
