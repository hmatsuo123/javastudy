package ch02.ex15;

import ch02.ex01.Vehicle;

public class VehicleChangeAndStopSpeed extends Vehicle {

	public void changeSpeed(double speed) {
		this.speed = speed;
	}

	public void stopSpeed() {
		this.speed = 0;
	}

}
