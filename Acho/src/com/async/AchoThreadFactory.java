package com.async;

import java.util.concurrent.ThreadFactory;

/**
 * Thread Factory for Acho Tasks
 * 
 * 
 */
public class AchoThreadFactory implements ThreadFactory {

	@Override
	@SuppressWarnings("NullableProblems")
	public Thread newThread(Runnable r) {
		return new AchoThread(r);

	}

	/**
	 * We override thread to set the priority and name
	 * 
	 * @author nruiz
	 * 
	 */
	private static class AchoThread extends Thread {
		/** Good to interpret memory dumps **/
		private static final String poolName = "ACHO_POOL";

		public AchoThread(Runnable r) {
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
