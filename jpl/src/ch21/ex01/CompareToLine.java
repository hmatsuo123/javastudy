package ch21.ex01;

import java.io.IOException;
import java.io.StringReader;

public class CompareToLine {
    public static void main(String[] args) {
    	StringReader in = new StringReader("matsuo\nhiroki\n3\n1\ntest");
    	/*File file;
    	if (args.length !=0)
    		file = new File(args[0]);
    	else
    		file = new File(args[0]);
    	if (!file.exists()) {
            System.out.println("ファイルが存在しません。");
            return;
        }*/

    	OutPutLineFilterReader reader = new OutPutLineFilterReader(in);
		try {
			reader.printLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}
