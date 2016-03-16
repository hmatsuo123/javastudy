package ch03.ex09;


public class VehicleGarage {
	class Vehicle {
		public double speed;
		public double direction;
		public String owner;
	}

	public Vehicle getVehicle() {
		return new Vehicle();
	}

	class Garage implements Cloneable {
		public Vehicle[] vehicleList = new Vehicle[10];

		public Garage clone() throws CloneNotSupportedException {
			Garage nObj = (Garage)super.clone();
			nObj.vehicleList = vehicleList.clone();
			return nObj;
		}

		//cloneチェックメソッド
		public boolean main(Garage targetGarage) {
			return this.equals(targetGarage);
		}
	}

	public Garage getGarage() {
		return new Garage();
	}
}
