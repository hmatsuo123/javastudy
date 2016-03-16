package ch03.ex09;

import static org.junit.Assert.*;

import org.junit.Test;

import ch03.ex09.VehicleGarage.Garage;
import ch03.ex09.VehicleGarage.Vehicle;

public class VehicleGarageTest {

	@Test
	public void test() {
		Garage garage = new VehicleGarage().getGarage();
		Vehicle vehicle = new VehicleGarage().getVehicle();
		vehicle.speed = 100;
		vehicle.direction = 50;
		vehicle.owner = "matsuo";
		garage.vehicleList[0] = vehicle;

		Vehicle vehicle2 = new VehicleGarage().getVehicle();
		vehicle2.speed = 50;
		vehicle2.direction = 30;
		vehicle2.owner = "hiroki";
		garage.vehicleList[0] = vehicle2;


		try {
			Garage clone = garage.clone();
			if (garage.main(clone)) {
				fail("複製失敗");
			}

			clone.vehicleList[0].speed = 80;
			if (garage.main(clone)) {
				fail("深い複製失敗");
			}

		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}

	}

}
