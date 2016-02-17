package ch02.ex05;

public class VehiclePrintln {

	public static void main(String[] args) {
		class Vehicle {
			public double speed;
			public double direction;
			public String owner;
		}

		Vehicle vehicle1 = new Vehicle();
		vehicle1.speed = 50;
		vehicle1.direction = 30;
		vehicle1.owner = "matsuo";

		Vehicle vehicle2 = new Vehicle();
		vehicle2.speed = 100;
		vehicle2.direction = 80;
		vehicle2.owner = "hiroki";

		System.out.println("乗り物1");
		System.out.println("速度：" + vehicle1.speed);
		System.out.println("方向：" + vehicle1.direction + "℃");
		System.out.println("所有者：" + vehicle1.owner);

		System.out.println("乗り物2");
		System.out.println("速度：" + vehicle2.speed);
		System.out.println("方向：" + vehicle2.direction + "℃");
		System.out.println("所有者：" + vehicle2.owner);
	}

}
