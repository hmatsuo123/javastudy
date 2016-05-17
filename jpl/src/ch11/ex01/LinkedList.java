package ch11.ex01;

public class LinkedList<E> {
	static final int MAX = 10;
	public Object[] dataArray = new Object[MAX];
	public E nextData;
	public int index = 0;

	public void add(E data) {
		dataArray[index] = data;
		index++;
	}

	public void set(int idx) {
		index = idx;
		if (dataArray[index + 1] != null)
			nextData = (E)dataArray[index + 1];
		else
			nextData = (E)dataArray[index];
	}

	public E getNextDate() {
		return nextData;
	}
}
