package ch04.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnergySourceInterfaceTest {

	@Test
	public void test() {
		EnergySourceInterface energy = new EnergySourceInterface();
		EnergySourceInterface.Vehicle vehicle = energy.getVehicle();

		vehicle.start();
		assertEquals(vehicle.gasTunk.gasAmount, 20, 0);
		assertEquals(vehicle.battery.electrialamount, 20, 0);
	}

}
