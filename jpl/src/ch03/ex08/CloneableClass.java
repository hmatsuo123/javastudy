package ch03.ex08;


public class CloneableClass {
	public static void main(String[] args) {
		class Vehicle implements Cloneable {
			public double speed;
			public double direction;
			public String owner;

			/*
			 * 【回答】
			 * 一つ目の「cloneをサポートする」が良い
			 * どのフィールドもクローン元データと共有する必要はないため
			 *   */
			public Vehicle clone() throws CloneNotSupportedException {
				return (Vehicle)super.clone();
			}
		}

		class PassengerVehicle implements Cloneable {
			public double speed;
			public double direction;
			public String owner;
			private int seatCount;				//車のシートの数
			private int sittingPeopleCount;	//座っている人数

			/*
			 * 【回答】
			 * 一つ目の「cloneをサポートする」が良い
			 * どのフィールドもクローン元データと共有する必要はないため
			 *   */
			public PassengerVehicle clone() throws CloneNotSupportedException {
				return (PassengerVehicle)super.clone();
			}

			public PassengerVehicle() {

			}

			public PassengerVehicle(double speed, double direction, String owner, int seatCount) {
				this.speed = speed;
				this.direction = direction;
				this.owner = owner;
				this.seatCount = seatCount;
			}

			final public void setSittingPeopleCount(int sittingPeopleCount) {
				this.sittingPeopleCount = sittingPeopleCount;
			}

			public int getSittingPeopleCount() {
				return this.sittingPeopleCount;
			}

			final public int getSeatCount() {
				return this.seatCount;
			}

			final public void setSeatCount(int seatCount) {
				this.seatCount = seatCount;
			}
		}


		Vehicle vehicle = new Vehicle();
		vehicle.speed = 100;
		vehicle.direction = 50;
		vehicle.owner = "matsuo";

		try {
			Vehicle clone = vehicle.clone();
			System.out.println("[Vehicle]");
			System.out.println("origin: speed=" + String.valueOf(vehicle.speed) + " direction=" + String.valueOf(vehicle.direction)
					+ " owner=" + vehicle.owner);
			System.out.println("origin: speed=" + String.valueOf(clone.speed) + " direction=" + String.valueOf(clone.direction)
					+ " owner=" + clone.owner);

			System.out.println("clone");
			vehicle.speed = 10;
			vehicle.owner = "hiroki";
			System.out.println("origin: speed=" + String.valueOf(vehicle.speed) + " direction=" + String.valueOf(vehicle.direction)
					+ " owner=" + vehicle.owner);
			System.out.println("origin: speed=" + String.valueOf(clone.speed) + " direction=" + String.valueOf(clone.direction)
					+ " owner=" + clone.owner);
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}


		PassengerVehicle passengerVehicle = new PassengerVehicle();
		passengerVehicle.speed = 100;
		passengerVehicle.direction = 50;
		passengerVehicle.owner = "matsuo";
		passengerVehicle.setSeatCount(5);
		passengerVehicle.setSittingPeopleCount(3);

		try {
			PassengerVehicle clone = passengerVehicle.clone();
			System.out.println("[PassengerVehicle]");
			System.out.println("origin: speed=" + String.valueOf(passengerVehicle.speed) + " direction=" + String.valueOf(passengerVehicle.direction)
					+ " owner=" + passengerVehicle.owner + " seatCount=" + String.valueOf(passengerVehicle.getSeatCount()) + " sittingPeopleCount=" + String.valueOf(passengerVehicle.getSittingPeopleCount()));
			System.out.println("origin: speed=" + String.valueOf(clone.speed) + " direction=" + String.valueOf(clone.direction)
					+ " owner=" + clone.owner + " seatCount=" + String.valueOf(clone.getSeatCount()) + " sittingPeopleCount=" + String.valueOf(clone.getSittingPeopleCount()));

			System.out.println("clone");
			passengerVehicle.speed = 10;
			passengerVehicle.setSittingPeopleCount(5);
			System.out.println("[PassengerVehicle]");
			System.out.println("origin: speed=" + String.valueOf(passengerVehicle.speed) + " direction=" + String.valueOf(passengerVehicle.direction)
					+ " owner=" + passengerVehicle.owner + " seatCount=" + String.valueOf(passengerVehicle.getSeatCount()) + " sittingPeopleCount=" + String.valueOf(passengerVehicle.getSittingPeopleCount()));
			System.out.println("origin: speed=" + String.valueOf(clone.speed) + " direction=" + String.valueOf(clone.direction)
					+ " owner=" + clone.owner + " seatCount=" + String.valueOf(clone.getSeatCount()) + " sittingPeopleCount=" + String.valueOf(clone.getSittingPeopleCount()));
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
}
