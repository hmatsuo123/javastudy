package ch02.ex09;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleStaticTest {

	@Test
	public void test() {
		int maxUniqueID = 123;
		//VehicleStatic vehicle = new VehicleStatic();
		VehicleStatic.setUniqueID(10);
		VehicleStatic.setUniqueID(maxUniqueID);
		VehicleStatic.setUniqueID(15);
		assertEquals(maxUniqueID, VehicleStatic.getMaxUniqueID());
	}

}
