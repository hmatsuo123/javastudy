package ch04.ex03;

public class LinkedListInterface {
	/*
	 * 【回答】
	 * リストの数を制限したい場合は、インターフェースか抽象クラスにするのが良いと思います。
	 * キュー操作は、多重継承よりも装飾子によるアクセス制限の方が使用する可能性が高いと考えられるため、抽象クラスにするのが良いと思います。
	 * */
	interface LinkedList {
		int MAX = 10;
		Object[] dataArray = new Object[MAX];

		void add(Object data);
		void set(int idx);
		Object getNextDate();
	}

	public class LinkedListClass implements LinkedList {
		public int index = 0;
		public Object nextData = new Object();

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

}
