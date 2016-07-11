package ch20.ex05;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class FindString {
	public static void main(String[] args) {
		if (args.length != 2) {
			throw new IllegalArgumentException("need char and file");
		}

		LineNumberReader in;
		try {
			FileReader fileIn = new FileReader(args[1]);
			in = new LineNumberReader(fileIn);
		} catch (FileNotFoundException e) {
			System.out.println("指定されたファイルが見つかりません。");
			return;
		}
		String str;
		try {
			while ((str = in.readLine()) != null) {
				if (str.contains(args[0])) {
					System.out.printf("%3d: %s%n", in.getLineNumber(), str);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

/* このファイルに対して”new”で検索した結果
 *  11: 			throw new IllegalArgumentException("need char and file");
 *  16: 			fileIn = new FileReader(args[1]);
 *  17: 			in = new LineNumberReader(fileIn);
 * */