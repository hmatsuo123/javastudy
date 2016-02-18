package ch02.ex08;

import ch02.ex02.LinkedList;

public class LinkedListConstructor extends LinkedList {

	//一度に複数のデータを追加できるコンストラクタ
	public LinkedListConstructor(Object[] dataArray) {
		for (Object data : dataArray) {
			this.dataArray[index] = data;
			index++;
		}
	}

	public static void main(String[] args) {
		Object targetDate1 = 1;
		Object targetDate2 = 2;
		Object[] dataArray = new Object[2];
		dataArray[0] = targetDate1;
		dataArray[1] = targetDate2;

		LinkedListConstructor linkedList = new LinkedListConstructor(dataArray);
		linkedList.set(0);
	}

}
