package com.async;

import java.util.concurrent.ThreadFactory;

/**
 * Thread Factory for Acho Tasks
 * 
 * 
 */
public class TaskThreadFactory implements ThreadFactory {

	@Override
	@SuppressWarnings("NullableProblems")
	public Thread newThread(Runnable r) {
		return new TaskThread(r);

	}

	/**
	 * We override thread to set the priority and name
	 * 
	 * @author nruiz
	 * 
	 */
	private static class TaskThread extends Thread {
		/** Good to interpret memory dumps **/
		private static final String poolName = "DEDUP_EXECUTOR_POOL";

		public TaskThread(Runnable r) {
			super(r, poolName);
		}

		@Override
		public void run() {
			// TODO Set thread priority on Android
			// Process.setThreadPriority(THREAD_PRIORITY_BACKGROUND);
			super.run();
		}
	}

}
