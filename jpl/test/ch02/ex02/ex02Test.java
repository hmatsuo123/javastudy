package ch02.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

public class ex02Test {

	@Test
	public void test() {
		LinkedList linkedList = new LinkedList();
		Object targetDate1 = 1;
		Object targetDate2 = 2;

		linkedList.add(targetDate1);
		linkedList.add(targetDate2);
		linkedList.set(0);
		assertEquals(linkedList.getNextDate(), targetDate2);
	}

}
