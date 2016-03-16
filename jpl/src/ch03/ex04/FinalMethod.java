package ch03.ex04;

public class FinalMethod {
	public class Vehicle {
		public double speed;
		public double direction;
		public String owner;

		/*
		 * 【回答】
		 * 全てのフィールドがpublicなので、finalメソッドは持たせるべきではない
		*/
	}

	public class PassengerVehicle {
		public double speed;
		public double direction;
		public String owner;
		private int seatCount;				//車のシートの数
		private int sittingPeopleCount;	//座っている人数

		public PassengerVehicle(double speed, double direction, String owner, int seatCount) {
			this.speed = speed;
			this.direction = direction;
			this.owner = owner;
			this.seatCount = seatCount;
		}

		/* 【回答】 */
		final public void setSittingPeopleCount(int sittingPeopleCount) {
			this.sittingPeopleCount = sittingPeopleCount;
		}

		/* 【回答】 */
		public int getSittingPeopleCount() {
			return this.sittingPeopleCount;
		}

		/* 【回答】 */
		final public int getSeatCount() {
			return this.seatCount;
		}

		/* 【回答】 */
		final public void setSeatCount(int seatCount) {
			this.seatCount = seatCount;
		}

		/* 【回答】 */
		//空いている席を取得する
		final public int getVacantSeatCount() {
			return this.seatCount - this.sittingPeopleCount;
		}

	}
}
