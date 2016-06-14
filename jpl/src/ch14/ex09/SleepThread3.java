package ch14.ex09;

public class SleepThread3 extends Thread{
	public SleepThread3(ThreadGroup group, String name) {
		super(group, name);
	}

	@Override
	public void run() {
		for (int i = 0; i< 3; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}
}
