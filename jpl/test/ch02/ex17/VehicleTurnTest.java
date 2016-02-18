package ch02.ex17;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTurnTest {

	@Test
	public void test() {
		double targetDirection = 90;
		VehicleTurn vehicleTurn = new VehicleTurn();
		vehicleTurn.turn(targetDirection);
		assertEquals(targetDirection, vehicleTurn.direction, 0);

		vehicleTurn.turn(VehicleTurn.Vehicle.TURN_LEFT);
		assertEquals(VehicleTurn.Vehicle.TURN_LEFT, vehicleTurn.turnDirection);
	}

}
