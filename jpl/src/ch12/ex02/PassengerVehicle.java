package ch12.ex02;

public class PassengerVehicle {
	public double speed;
	public double direction;
	public String owner;
	private int seatCount;				//車のシートの数
	private int sittingPeopleCount;		//座っている人数

	public PassengerVehicle(double speed, double direction, String owner, int seatCount) {
		this.speed = speed;
		this.direction = direction;
		this.owner = owner;
		this.seatCount = seatCount;
	}

	public boolean setSittingPeopleCount(int sittingPeopleCount) {
		// 回答
		if (sittingPeopleCount < 0) {
			return false;
		}
		this.sittingPeopleCount = sittingPeopleCount;
		return true;
	}

	public int getSeatCount() {
		return this.seatCount;
	}

	public int getSittingPeopleCount() {
		return this.sittingPeopleCount;
	}
}
