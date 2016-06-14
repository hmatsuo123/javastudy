package ch14.ex01;

public class MainThreadName {
	public static void main(String[] args) {
		Thread mainThread = Thread.currentThread();
		System.out.println("thread: Id=" + mainThread.getId() + ", Name=" + mainThread.getName());

		Thread thead1 = new Thread();
		Thread thead2 = new Thread();
		System.out.println("thread: Id=" + thead1.getId() + ", Name=" + thead1.getName());
		System.out.println("thread: Id=" + thead2.getId() + ", Name=" + thead2.getName());
		thead1.setName("test");
		thead2.setName("test");
		System.out.println("thread: Id=" + thead1.getId() + ", Name=" + thead1.getName());
		System.out.println("thread: Id=" + thead2.getId() + ", Name=" + thead2.getName());
		thead1.start();
		thead2.start();
		System.out.println("thread: Id=" + thead1.getId() + ", Name=" + thead1.getName());
		System.out.println("thread: Id=" + thead2.getId() + ", Name=" + thead2.getName());
	}
}
