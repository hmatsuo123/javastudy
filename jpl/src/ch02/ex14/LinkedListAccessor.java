package ch02.ex14;

public class LinkedListAccessor {
	static final int MAX = 10;
	private Object[] dataArray = new Object[MAX];
	private Object nextData;
	private int index = 0;

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

	public Object[] getDataArray() {
		return this.dataArray;
	}
	public Object getNextData() {
		return this.nextData;
	}

	//LinkedListクラスは常に次の値を所持しているため、外部からのセットは不要
	/*public void setNextData(Object data) {
		this.nextData = data;
	}*/
	//全てのリストを入れ替えるなどの処理が必要でない限り、外部からのセットは不要
	/*public void setDataArray(Object[] dataArray) {
		this.dataArray = dataArray;
	}*/
}
