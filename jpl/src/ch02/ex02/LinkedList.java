package ch02.ex02;

public class LinkedList {
	static final int MAX = 10;
	public Object[] dateArray = new Object[MAX];
	public Object nextDate;
	private int index = 0;

	public void add(Object date) {
		dateArray[index] = date;
		index++;
	}

	public void set(int idx) {
		index = idx;
		if (dateArray[index + 1] != null)
			nextDate = dateArray[index + 1];
		else
			nextDate = dateArray[index];
	}

	public Object getNextDate() {
		return nextDate;
	}
}
