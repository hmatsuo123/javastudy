package ch23.ex02;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class CommandExecTest {

	@Test
	public void doCommandExecTest() {
		String[] test = {"java","-version"};
		try {
			new CommandExec().doCommandExec(test);
        } catch (IOException e) {
            fail(e.getMessage());
        }
	}

}
