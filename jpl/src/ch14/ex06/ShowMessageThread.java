package ch14.ex06;

public class ShowMessageThread extends Thread {
	private final int interval;
	private boolean isWait = true;
	private int count = 1;

	public ShowMessageThread(int interval) {
		this.interval = interval;
	}

	@Override
	public synchronized void run() {
		while(true) {
			while(isWait) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(interval + "秒経過");
			isWait = true;
		}
	}

	public synchronized void notifyCountTime(int mSecond) {
		count += mSecond / 1000;
		if (count % interval == 0) {
			isWait = false;
			notifyAll();
		}
	}
}
