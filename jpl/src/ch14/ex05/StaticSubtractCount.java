package ch14.ex05;


public class StaticSubtractCount implements Runnable {
	private static int current = 50;
	private static Object lock = new Object();

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			subtract(1);
		}
	}

	public static void subtract(int num) {
		synchronized (lock) {
			current -= num;
			System.out.println("current num=" + current);
		}
	}

	public static void main(String[] args) {
		StaticSubtractCount staticSubtractCount = new StaticSubtractCount();
		new Thread(staticSubtractCount).start();
		new Thread(staticSubtractCount).start();
		new Thread(staticSubtractCount).start();
		new Thread(staticSubtractCount).start();
		new Thread(staticSubtractCount).start();
	}
}
