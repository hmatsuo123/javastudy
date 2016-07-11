package ch20.ex03;

import java.io.IOException;


public class EncryptTestClass {
	public static void main(String[] args) {
		DecryptInputStream input = new DecryptInputStream(System.in);
        EncryptOutputStream output = new EncryptOutputStream(System.out);
        try {
            int c;
            while ((c = input.read()) != -1) {
                System.out.println("Encoded: " + c);
                System.out.print("Decoded: ");
                output.write(c);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}