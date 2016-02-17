package ch01.ex08;

import static org.junit.Assert.*;

import org.junit.Test;

public class ex08Test {

	@Test
	public void test() {
		double targetX = 1;
		double targetY = 2;
		Point point = new Point();
		point.setPoint(targetX, targetY);
		assertEquals(targetX, point.x, 0);
		assertEquals(targetY, point.y, 0);
	}

}
