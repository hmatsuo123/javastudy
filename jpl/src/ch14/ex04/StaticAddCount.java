package ch14.ex04;


public class StaticAddCount implements Runnable {
	private static int current = 0;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			add(1);
		}
	}

	public static synchronized void add(int num) {
		current += num;
		System.out.println("current num=" + current);
	}

	public static void main(String[] args) {
		StaticAddCount addCount = new StaticAddCount();
		new Thread(addCount).start();
		new Thread(addCount).start();
		new Thread(addCount).start();
		new Thread(addCount).start();
		new Thread(addCount).start();
	}
}
