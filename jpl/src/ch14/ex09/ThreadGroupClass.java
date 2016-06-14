package ch14.ex09;

public class ThreadGroupClass extends Thread {
	private final ThreadGroup group;
	private String prefix;

	public ThreadGroupClass(ThreadGroup group) {
		this.group = group;
	}

	@Override
    public void run() {
        while (printThreadName(group, "")) {
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
        }
    }

	private boolean printThreadName(ThreadGroup group, String prefix) {
		System.out.println(prefix + group.getName());

        Thread[] threads = new Thread[group.activeCount()];
        int threadsSum = group.enumerate(threads, false);
        if (threadsSum == 0) {
        	//アクティブなスレッドはないため終了
        	return false;
        }
        for (int i = 0; i < threadsSum; i++) {
            System.out.println(prefix + "\t" + threads[i].getName());
        }

        ThreadGroup[] groups = new ThreadGroup[group.activeGroupCount()];
        int groupsSum = group.enumerate(groups, false);
        for (int i = 0; i < groupsSum; i++) {
        	printThreadName(groups[i], prefix += "\t");
        }
        System.out.println();
        return true;
	}

	public static void main(String[] args) {
		ThreadGroup mainGroup = new ThreadGroup("mainGroup");
		new SleepThread(mainGroup, "thread1").start();
		new SleepThread2(mainGroup, "thread2").start();
		new SleepThread3(mainGroup, "thread3").start();
		ThreadGroup subGroup = new ThreadGroup(mainGroup, "subGroup");
		new SleepThread(subGroup, "thread3").start();
		ThreadGroup subGroup2 = new ThreadGroup(subGroup, "subGroup2");
		new SleepThread(subGroup2, "thread3").start();
		new ThreadGroupClass(mainGroup).start();
	}
}
