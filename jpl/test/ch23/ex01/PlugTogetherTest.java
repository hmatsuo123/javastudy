package ch23.ex01;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class PlugTogetherTest {

	@Test
	public void PlugTogetherTest() {
		try {
            Process proc = ProcessPlugTogether.userProg("java -version");
            System.out.println(proc.toString());
        } catch (IOException e) {
            fail();
        }
	}

}
