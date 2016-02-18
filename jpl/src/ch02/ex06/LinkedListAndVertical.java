package ch02.ex06;

import ch02.ex01.Vehicle;
import ch02.ex02.LinkedList;

public class LinkedListAndVertical extends LinkedList {

	public static void main(String[] args) {
		LinkedListAndVertical linkedList = new LinkedListAndVertical();
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
		}

	}

}
