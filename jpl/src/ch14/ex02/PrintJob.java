package ch14.ex02;

public class PrintJob {
	String text;

	public PrintJob(String text) {
		this.text = text;
	}

	public void outPutPrint() {
		System.out.println("outPutPrint:" + text);
	}
}
