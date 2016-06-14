package ch14.ex09;

public class SleepThread2 extends Thread {
	public SleepThread2(ThreadGroup group, String name) {
		super(group, name);
	}

	@Override
	public void run() {
		for (int i = 0; i< 2; i++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}
}
