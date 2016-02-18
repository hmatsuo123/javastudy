package ch02.ex02;

public class LinkedList {
	static final int MAX = 10;
	public Object[] dataArray = new Object[MAX];
	public Object nextData;
	public int index = 0;

	public void add(Object data) {
		dataArray[index] = data;
		index++;
	}

	public void set(int idx) {
		index = idx;
		if (dataArray[index + 1] != null)
			nextData = dataArray[index + 1];
		else
			nextData = dataArray[index];
	}

	public Object getNextDate() {
		return nextData;
	}
}
