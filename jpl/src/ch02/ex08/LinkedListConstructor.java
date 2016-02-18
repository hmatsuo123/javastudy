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

}
