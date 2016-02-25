package ch03.ex01;

import ch02.ex01.Vehicle;

public class PassengerVehicle extends Vehicle {
	private int seatCount;				//車のシートの数
	private int sittingPeopleCount;	//座っている人数

	public PassengerVehicle(double speed, double direction, String owner, int seatCount) {
		this.speed = speed;
		this.direction = direction;
		this.owner = owner;
		this.seatCount = seatCount;
	}

	public void setSittingPeopleCount(int sittingPeopleCount) {
		this.sittingPeopleCount = sittingPeopleCount;
	}

	public int getSeatCount() {
		return this.seatCount;
	}

	public int getSittingPeopleCount() {
		return this.sittingPeopleCount;
	}

	public static void main(String[] args) {
		PassengerVehicle bus = new PassengerVehicle(100, 90, "matsuo", 30);
		bus.setSittingPeopleCount(20);
		PassengerVehicle car = new PassengerVehicle(80, 45, "hiroki", 4);
		car.setSittingPeopleCount(4);
		PassengerVehicle motorcycle = new PassengerVehicle(60, 30, "matsuo hiroki", 1);
		motorcycle.setSittingPeopleCount(1);

		System.out.println("・バス");
		System.out.printf("スピード：%f%n", bus.speed);
		System.out.printf("角度：%f%n", bus.direction);
		System.out.printf("シート数：%d%n", bus.getSeatCount());
		System.out.printf("現在座っている人数：%d%n%n", bus.getSittingPeopleCount());

		System.out.println("・車");
		System.out.printf("スピード：%f%n", car.speed);
		System.out.printf("角度：%f%n", car.direction);
		System.out.printf("シート数：%d%n", car.getSeatCount());
		System.out.printf("現在座っている人数：%d%n%n", car.getSittingPeopleCount());

		System.out.println("・バイク");
		System.out.printf("スピード：%f%n", motorcycle.speed);
		System.out.printf("角度：%f%n", motorcycle.direction);
		System.out.printf("シート数：%d%n", motorcycle.getSeatCount());
		System.out.printf("現在座っている人数：%d%n", motorcycle.getSittingPeopleCount());
	}
}
