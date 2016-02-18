package ch02.ex08;

import static org.junit.Assert.*;

import org.junit.Test;

public class ex08Test {

	@Test
	public void test() {
		Object targetDate1 = 1;
		Object targetDate2 = 2;
		Object[] dataArray = new Object[2];
		dataArray[0] = targetDate1;
		dataArray[1] = targetDate2;

		LinkedListConstructor linkedList = new LinkedListConstructor(dataArray);
		linkedList.set(0);
		assertEquals(linkedList.getNextDate(), targetDate2);
	}

}
