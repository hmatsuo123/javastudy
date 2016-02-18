package ch02.ex07;

import ch02.ex06.LinkedListAndVertical;

public class VehicleConstructor {

	//TODO:問題の意図が不明

	public static void main(String[] args) {
		class Vehicle {
			public double speed;
			public double direction;
			public String owner;
			public String firstOwner;

			public Vehicle() {

			}

			public Vehicle(String firstOwner) {
				this.firstOwner = firstOwner;
			}
		}

		String owner = "matsuo";

		Vehicle Vehicle1 = new Vehicle(owner);
		Vehicle1.speed = 50;
		Vehicle1.direction = 30;
		Vehicle1.owner = owner;

		/*LinkedListAndVertical linkedList = new LinkedListAndVertical();
		linkedList.add(new Vehicle(50, 45, "matsuo"));
		linkedList.add(new Vehicle(100, 90, "hiroki"));
		linkedList.add(new Vehicle(150, 180, "user"));

		//初期値を設定
		int index = 0;
		linkedList.set(index);
		Vehicle preDate = (Vehicle)linkedList.getNextDate();
		//2番目から表示
		System.out.println("乗り物" + (index + 1));
		System.out.println("速度：" + preDate.speed);
		System.out.println("角度：" + preDate.direction);
		System.out.println("所有者：" + preDate.owner);
		index++;
		linkedList.set(index);

		while (!preDate.equals(linkedList.getNextDate())) {
			preDate = (Vehicle)linkedList.getNextDate();
			System.out.println("乗り物" + (index + 1));
			System.out.println("速度：" + preDate.speed);
			System.out.println("角度：" + preDate.direction);
			System.out.println("所有者：" + preDate.owner);
			index++;
			linkedList.set(index);
		}*/

	}

}
