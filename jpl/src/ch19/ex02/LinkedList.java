package ch19.ex02;

/**
 * 要素のリストと次の要素を保持するクラス。
 *
 * @author hiroki matsuo
 * @version 1.0
 */
public class LinkedList {
	/** 保持できる要素の最大数 */
	private static final int MAXELEMENTCOUNT = 10;
	/** 要素のリスト */
	public Object[] dataArray = new Object[MAXELEMENTCOUNT];
	/** 次の要素 */
	public Object nextData;
	/** リストの要素数 */
	public int elemCount = 0;

	/**
	 * 与えられた要素をリストに追加する。
	 */
	public void add(Object data) {
		dataArray[elemCount] = data;
		elemCount++;
	}

	/**
	 * 与えられたインデックスとそれに対する次の要素をセットする。
	 * 与えられたインデックスがリストの最後の要素だった場合、最後の要素を次の要素にセットする。
	 * @throws ArrayIndexOutOfBoundsException 指定されたインデックスがリストの範囲外の場合
	 */
	public void setIndex(int idx) {
		if (idx > MAXELEMENTCOUNT - 1 || idx < 0) {
			throw new ArrayIndexOutOfBoundsException("不正なインデックスが指定されました。");
		}else if (dataArray[idx + 1] != null) {
			nextData = dataArray[idx + 1];
		}
		else {
			nextData = dataArray[idx];
		}
	}

	/**
	 * 次の要素を取得する。
	 */
	public Object getNextDate() {
		return nextData;
	}

	/**
	 * 要素数を取得する。
	 */
	public int getIndexCount() {
		return elemCount > MAXELEMENTCOUNT - 1 ? MAXELEMENTCOUNT : elemCount;
	}
}
