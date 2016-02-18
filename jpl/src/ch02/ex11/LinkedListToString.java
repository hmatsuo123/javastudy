package ch02.ex11;

import ch02.ex02.LinkedList;

public class LinkedListToString extends LinkedList {

	public String toString() {
		return "リストの数は" + (index + 1) + "個です";
    }

	public static void main(String[] args) {
		LinkedListToString linkedList = new LinkedListToString();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		System.out.println(linkedList);
	}

}
