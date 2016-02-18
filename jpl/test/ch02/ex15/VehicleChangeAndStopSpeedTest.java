package ch02.ex15;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleChangeAndStopSpeedTest {

	@Test
	public void test() {
		double targetChangeSpeed = 100;
		VehicleChangeAndStopSpeed vehicle = new VehicleChangeAndStopSpeed();
		vehicle.speed = 50;
		vehicle.changeSpeed(targetChangeSpeed);
		assertEquals(targetChangeSpeed, vehicle.speed, 0);
		vehicle.stopSpeed();
		assertEquals(0, vehicle.speed, 0);
	}

}
