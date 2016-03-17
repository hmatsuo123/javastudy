package ch03.ex10;

public class LinkedListClone implements Cloneable {
	static final int MAX = 10;
	public Object[] dataArray = new Object[MAX];
	public Object nextData;
	public int index = 0;

	public LinkedListClone clone() throws CloneNotSupportedException {
		LinkedListClone nObj = (LinkedListClone)super.clone();
		//nObj.dataArray = dataArray.clone();
		return nObj;
	}

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
