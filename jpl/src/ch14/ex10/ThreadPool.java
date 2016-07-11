package ch14.ex10;

import java.util.LinkedList;

/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 */

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to
 * be exectued by a thread.
 *
 * [Instruction]
 *  Implement one constructor and three methods.
 *  Don't forget to write a Test program to test this class.
 *  Pay attention to @throws tags in the javadoc.
 *  If needed, you can put "synchronized" keyword to methods.
 *  All classes for implementation must be private inside this class.
 *  Don't use java.util.concurrent package.
 */
public class ThreadPool {
	private WorkerThread[] threads;
	private ThreadPoolQueue<Runnable> queue;
	private boolean isThreadPooltarted = false;

	/**
	 * Constructs ThreadPool.
	 *
	 * @param queueSize the max size of queue
	 * @param numberOfThreads the number of threads in this pool.
	 *
	 * @throws IllegalArgumentException if either queueSize or numberOfThreads
	 *         is less than 1
	 */
	public ThreadPool(int queueSize, int numberOfThreads) {
		if (queueSize < 1 || numberOfThreads < 1) {
			throw new IllegalArgumentException();
		}
		threads = new WorkerThread[numberOfThreads];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new WorkerThread();
		}
		queue = new ThreadPoolQueue<Runnable>(queueSize);
	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException if threads has been already started.
	 */
	public void start() {
		if (isThreadPooltarted) {
			throw new IllegalStateException("既に実行されています。");
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].start();
			} catch (IllegalThreadStateException e) {
				throw new IllegalStateException(e);
			}
		}
		isThreadPooltarted = true;
	}

	/**
	 * Stop all threads and wait for their terminations.
	 *
	 * @throws IllegalStateException if threads has not been started.
	 */
	public void stop() {
		for (WorkerThread thread : threads) {
			if (thread.isAlive()) {
				thread.stopThread();
				try {
					thread.join();
				} catch (InterruptedException e) {
					System.err.println("タスクが中断されました。");
				}
			} else {
				throw new IllegalStateException();
			}
		}
	}

	/**
	 * Executes the specified Runnable object, using a thread in the pool.
	 * run() method will be invoked in the thread. If the queue is full, then
	 * this method invocation will be blocked until the queue is not full.
	 *
	 * @param runnable Runnable object whose run() method will be invoked.
	 *
	 * @throws NullPointerException if runnable is null.
	 * @throws IllegalStateException if this pool has not been started yet.
	 */
	public synchronized void dispatch(Runnable runnable) {
		if (runnable == null) {
			throw new NullPointerException();
		}
		if (!isThreadPooltarted) {
			throw new IllegalStateException("ThreadPoolが実行されていません。");
		}
		queue.add(runnable);
	}

	private class WorkerThread extends Thread {
		private boolean isActive = true;

		@Override
		public void run() {
			Runnable runnable = null;
			while (isActive) {
				runnable = queue.poll();
				if (runnable != null)
					runnable.run();
			}
		}

		private void stopThread() {
			isActive = false;
			queue.stop();
		}
	}

	class ThreadPoolQueue<T> {

		private final int size;
		private LinkedList<T> list;

		public ThreadPoolQueue(int size) {
			if (size < 1) {
				throw new IllegalArgumentException();
			}
			this.size = size;
			list = new LinkedList<T>();
		}

		public synchronized boolean add(T task) {
			boolean result;
			while (list.size() >= size)
				try {
					// タスクが実行されるまで待機
					wait();
				} catch (InterruptedException e) {
					return false;
				}
			result = list.add(task);
			notifyAll();

			return result;
		}

		public synchronized T poll() {
			if (list.isEmpty()) {
				try {
					// タスクが追加されるまで待機
					wait();
				} catch (InterruptedException e) {
					return null;
				}
			}
			T task = list.poll();
			notifyAll();

			return task;
		}

		public synchronized void stop() {
			notifyAll();
		}
	}
}
