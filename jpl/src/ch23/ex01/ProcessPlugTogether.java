package ch23.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProcessPlugTogether {
	public static Process userProg(String cmd) throws IOException {
        Process proc = Runtime.getRuntime().exec(cmd);
        plugTogether(System.in, proc.getOutputStream());
        plugTogether(System.out, proc.getInputStream());
        plugTogether(System.err, proc.getErrorStream());
        return proc;
    }

	public static void plugTogether(InputStream in, OutputStream out) {
		PlugClass plug = new PlugClass(in, out);
        new Thread(plug).start();
    }

    public static void plugTogether(OutputStream out, InputStream in) {
    	PlugClass plug = new PlugClass(in, out);
        new Thread(plug).start();
    }
}
