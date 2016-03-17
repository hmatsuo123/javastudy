package ch05.ex02;

import java.util.ArrayList;
import java.util.List;


public class BankAccount {
	private long number;
	private long balance;
	private History history = new History();

	/*
	 * 【回答】
	 * 銀行のアカウントごとに履歴を残すべきなので、ネストにした方がよい
	 * 個人情報は各インスタンスごとに管理するべきなので、Historyクラスはstaticにするべきではない
	 *  */
	public class History {
		public class Action {
			private String act;
			private long amount;

			Action(String act, long amount) {
				this.act = act;
				this.amount = amount;
			}

			public String toString() {
				return number + ": " + act + " " + amount;
			}
		}

		private final int listMaxCount = 10;
		private List<Action> actionList = new ArrayList<Action>(listMaxCount);
		//最新のデータ番号
		private int newDataIdx = -1;

		public void addHistory(String act, long amount) {
			if((newDataIdx + 1) == listMaxCount) {
				newDataIdx = 0;
			} else {
				newDataIdx++;
			}
			actionList.add(newDataIdx, new Action(act, amount));
			number++;
		}

		//最新データを返し、履歴から削除する
		public Action next() {
			Action value = null;
			if (actionList.size() != 0) {
				int lastDataIdx = getLastDataIdx();

				/*if (newDataIdx != lastDataIdx) {
					value = actionList.get(newDataIdx);
				}*/
				value = actionList.get(newDataIdx);
				actionList.remove(newDataIdx);

				//最新のデータ番号を調整
				if (newDataIdx != 0) {
					newDataIdx--;
				} else {
					newDataIdx = 9;
				}
			}
			number--;
			return value;
		}

		//Listの最後のデータ番号を取得する
		private int getLastDataIdx() {
			int lastDataIdx = newDataIdx;
			while (true) {
				lastDataIdx++;
				if ((lastDataIdx + 1) > listMaxCount) {
					lastDataIdx = 0;
				}

				try {
					if (actionList.get(lastDataIdx) != null) {
						break;
					}
				} catch (IndexOutOfBoundsException ex) {
					//listに値が存在しない場合
				}
			}

			return lastDataIdx;
		}
	}

	public void deposit(long amount) {
		balance += amount;
		history.addHistory("deposit", amount);
	}

	public void withdraw(long amount) {
		balance -= amount;
		history.addHistory("withdraw", amount);
	}

	public static void main(String[] args) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.deposit(100);
		bankAccount.showHistory(bankAccount.history.next());

		bankAccount.deposit(100);
		bankAccount.deposit(200);
		bankAccount.deposit(300);
		bankAccount.deposit(400);

		bankAccount.showHistory(bankAccount.history.next());
		bankAccount.showHistory(bankAccount.history.next());
		bankAccount.showHistory(bankAccount.history.next());
		bankAccount.showHistory(bankAccount.history.next());

	}

	//テスト用コード
	public void showHistory(BankAccount.History.Action value) {
		if (value != null) {
			System.out.println(value.toString());
		} else {
			System.out.println("null");
		}
	}
}
