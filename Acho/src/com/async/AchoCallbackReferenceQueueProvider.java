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
public class AchoCallbackReferenceQueueProvider extends ReferenceQueue<AchoTaskCallback> {

	private static class Singleton {
		private static ReferenceQueue<AchoTaskCallback> INSTANCE = new ReferenceQueue<AchoTaskCallback>();
	}

	public static ReferenceQueue<AchoTaskCallback> getInstance() {
		return Singleton.INSTANCE;
	}
}
