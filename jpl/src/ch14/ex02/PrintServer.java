package ch14.ex02;

public class PrintServer implements Runnable {
	private final PrintQueue requests = new PrintQueue();
	private static long threadId;

	public PrintServer() {
		Thread thread = new Thread(this);
		threadId = thread.getId();
		thread.start();
	}

	public void print(PrintJob job) {
		requests.add(job);
	}

	@Override
	public void run() {
		if (threadId == Thread.currentThread().getId()) {
			for (;;) {
				try {
					realPrint(requests.remove());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("run()が不正に呼び出されました。");
		}
	}

	private void realPrint(PrintJob job) {
		job.outPutPrint();
	}

	public static void main(String[] args) {
		PrintServer printServer = new PrintServer();
		printServer.print(new PrintJob("job1"));
		printServer.print(new PrintJob("job2"));
		printServer.run();
	}
}
