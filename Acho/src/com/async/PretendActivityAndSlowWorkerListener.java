package com.async;

import java.lang.ref.WeakReference;

/**
 * Receiver of async callbacks executed from AchoExecuitorService
 * 
 * For an AchoTask
 * 
 * @author nruiz
 * 
 */
public class PretendActivityAndSlowWorkerListener implements AchoTaskCallback {

	/**
	 * This will try to retrieve something that is in turn retrieved in an sync
	 * fashion if needed
	 * 
	 */
	public void doSomething() {

		// now do the same thing but with the acho thread pool

		AchoExecutorService aes = new AchoExecutorServiceImpl(AchoCallbackReferenceQueueProvider.getInstance());

		// how do we execute

		for (int i = 0; i < 10; i++) {
			aes.execute(new SlowWorker(this, i));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.async.AchoTaskListener#onSucess()
	 */
	@Override
	public void onSucess(String taskId) {
		System.out.println("There was sucess");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.async.AchoTaskListener#onFail()
	 */
	@Override
	public void onFail(String taskId) {
		System.out.println("ALARM!!! There was a fail!");
	}
}
