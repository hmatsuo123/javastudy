package ch03.ex10;

import static org.junit.Assert.*;

import org.junit.Test;


public class LinkedListCloneTest {

	@Test
	public void test() {
		LinkedListClone linkedListClone = new LinkedListClone();
		linkedListClone.add(1);

		try {
			LinkedListClone clone = linkedListClone.clone();
			if (linkedListClone.dataArray[0] != clone.dataArray[0]) {
				fail("複製失敗");
			}

			clone.add(2);
			if ((linkedListClone.dataArray[1] != null) && (linkedListClone.dataArray[1] != clone.dataArray[1])) {
				fail("浅い複製失敗");
			}

		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
}
