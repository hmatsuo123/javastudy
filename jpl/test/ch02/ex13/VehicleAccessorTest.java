package ch02.ex13;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleAccessorTest {

	@Test
	public void test() {
		double targetSpeed = 50;
		double targetDirection = 90;
		String targetOwner = "matsuo";
		VehicleAccessor vehicleAccessor = new VehicleAccessor();
		vehicleAccessor.setSpeed(targetSpeed);
		vehicleAccessor.setDirectiond(targetDirection);
		vehicleAccessor.setOwner(targetOwner);

		assertEquals(targetSpeed, vehicleAccessor.getSpeed(), 0);
		assertEquals(targetDirection, vehicleAccessor.getDirection(), 0);
		assertEquals(targetOwner, vehicleAccessor.getOwner());
	}

}
