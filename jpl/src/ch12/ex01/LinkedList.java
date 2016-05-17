package ch12.ex01;

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

	/**
	 *
	 * @param element 捜査対象要素
	 * @return 一致する要素
	 * @throws ObjectNotFoundException 一致する要素がない場合
	 */
	public E find(E element) throws ObjectNotFoundException {
		E result = null;
		for (Object elm : dataArray) {
			if ((E)elm == element) {
				result = (E)elm;
				break;
			}
		}
		if (result == null) {
			throw new ObjectNotFoundException(element);
		}

		return result;
	}

	public static void main(String[] args) {
		LinkedList<String> linkedList = new LinkedList<String>();
		linkedList.add("1");
		linkedList.add("2");
		linkedList.add("3");
		linkedList.add("4");

		String targetElm = "5";
		try {
			linkedList.find(targetElm);
			System.out.printf("findElement");
		} catch (ObjectNotFoundException e) {
			System.out.printf(e.getMessage());
		}
	}
}
