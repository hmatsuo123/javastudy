package ch23.ex02;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class CommandExec {
	public static void main(String[] args) {
		try {
			//Process proc = Runtime.getRuntime().exec(args);
			String[] test = {"java","-version"};
			Process proc = Runtime.getRuntime().exec(test);

			InputStreamReader streamR = new InputStreamReader(proc.getErrorStream());
			LineNumberReader tmp = new LineNumberReader(streamR);

			String str;
			while ((str = tmp.readLine()) != null) {
				System.out.printf("%3d: %s%n", tmp.getLineNumber(), str);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
