package ch23.ex02;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class CommandExec {
	public void doCommandExec(String[] args) throws IOException{
		Process proc = Runtime.getRuntime().exec(args);

		InputStreamReader streamR = new InputStreamReader(proc.getErrorStream());
		LineNumberReader tmp = new LineNumberReader(streamR);

		String str;
		while ((str = tmp.readLine()) != null) {
			System.out.printf("%3d: %s%n", tmp.getLineNumber(), str);
		}
	}
	public static void main(String[] args) throws IOException {
		new CommandExec().doCommandExec(args);
	}
}
