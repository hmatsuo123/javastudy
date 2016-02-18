package ch02.ex16;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListReturnIdxCountTest {

	@Test
	public void test() {
		LinkedListReturnIdxCount linkedList = new LinkedListReturnIdxCount();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		assertEquals(3, linkedList.getIndexCount());
	}

}
