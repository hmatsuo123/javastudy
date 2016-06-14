package ch14.ex09;

public class SleepThread extends Thread {
	public SleepThread(ThreadGroup group, String name) {
		super(group, name);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
	}
}
