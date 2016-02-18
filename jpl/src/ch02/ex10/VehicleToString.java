package ch02.ex10;

import ch02.ex01.Vehicle;

public class VehicleToString extends Vehicle {
	public String toString() {
		return this.owner + "の乗り物です";
    }

	public static void main(String[] args) {
		VehicleToString vehicle = new VehicleToString();
		vehicle.owner = "matsuo";
		System.out.println(vehicle);
	}

}
