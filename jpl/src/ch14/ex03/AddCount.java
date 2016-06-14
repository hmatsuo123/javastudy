package ch14.ex03;

public class AddCount implements Runnable {
	private int current = 0;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			add(1);
		}
	}

	public synchronized void add(int num) {
		current += num;
		System.out.println("current num=" + current);
	}

	public static void main(String[] args) {
		AddCount addCount = new AddCount();
		new Thread(addCount).start();
		new Thread(addCount).start();
		new Thread(addCount).start();
		new Thread(addCount).start();
		new Thread(addCount).start();
	}

}
