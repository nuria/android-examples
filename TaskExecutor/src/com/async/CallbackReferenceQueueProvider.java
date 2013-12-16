package com.async;

import java.lang.ref.ReferenceQueue;

/**
 * Reference queue for acho tasks callbacks, it is a singleton This class is
 * used to create weakreferences and receive a notification of when those
 * references have been GC collected
 * 
 * @author nuria
 * 
 */
public class CallbackReferenceQueueProvider extends ReferenceQueue<TaskCallback> {

	private static class Singleton {
		private static ReferenceQueue<TaskCallback> INSTANCE = new ReferenceQueue<TaskCallback>();
	}

	public static ReferenceQueue<TaskCallback> getInstance() {
		return Singleton.INSTANCE;
	}
}
