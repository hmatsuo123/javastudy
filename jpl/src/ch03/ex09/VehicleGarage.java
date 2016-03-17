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
			for (int i = 0; i < targetGarage.vehicleList.length; i++) {
				if (targetGarage.vehicleList[i] == null) {
					break;
				}
				if ((targetGarage.vehicleList[i].speed != vehicleList[i].speed) ||
					(targetGarage.vehicleList[i].direction != vehicleList[i].direction) ||
					(targetGarage.vehicleList[i].owner != vehicleList[i].owner)) {
					return false;
				}
			}

			return true;
			//return this.equals(targetGarage);
		}
	}

	public Garage getGarage() {
		return new Garage();
	}
}
