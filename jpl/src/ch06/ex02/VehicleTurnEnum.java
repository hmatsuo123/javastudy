package ch06.ex02;


public class VehicleTurnEnum {
	public double speed;
	public double direction;
	public String owner;

	//修正量が少なくなる
	enum Vehicle {TURN_LEFT, TURN_RIGHT }
	Vehicle turnDirection;

	public void turn(double direction) {
		this.direction = direction;
	}
	public void turn(Vehicle turnDirection) {
		this.turnDirection = turnDirection;
	}

}
