package ch02.ex17;

import ch02.ex01.Vehicle;

public class VehicleTurn extends Vehicle {
	enum Vehicle {TURN_LEFT, TURN_RIGHT }
	Vehicle turnDirection;

	public void turn(double direction) {
		this.direction = direction;
	}
	public void turn(Vehicle turnDirection) {
		this.turnDirection = turnDirection;
	}
}
