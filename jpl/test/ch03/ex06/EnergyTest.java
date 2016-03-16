package ch03.ex06;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnergyTest {

	@Test
	public void test() {
		Energy energy = new Energy();
		Energy.Vehicle vehicle = energy.getVehicle();

		vehicle.start();
		assertEquals(vehicle.gasTunk.gasAmount, 20, 0);
		assertEquals(vehicle.battery.electrialamount, 20, 0);
	}

}
