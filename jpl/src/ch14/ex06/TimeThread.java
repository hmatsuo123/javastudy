package ch14.ex06;

public class TimeThread extends Thread {
	// 経過時間[ms]
	private final int countMSecond = 1000;
	private ShowMessageThread showMessageThread;

	public TimeThread(int interval) {
		this.showMessageThread = new ShowMessageThread(interval);
		this.showMessageThread.start();
	}

	@Override
	public void run() {
		int count = 1;
		while(true) {
			System.out.println("count=" + count);
			count++;

			try {
				Thread.sleep(countMSecond);
				this.showMessageThread.notifyCountTime(countMSecond);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		//new TimeThread(15).start();
		new TimeThread(7).start();
    }
}
