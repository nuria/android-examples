package com.async;

import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;

public class SimilarSlowWorkerInteractor extends AchoTaskBase implements WorkerInteractorInterface {

	private int workerId;

	private CountDownLatch doneSignal;


	protected AchoExecutorService executor;
	
	public SimilarSlowWorkerInteractor(AchoExecutorService executor) {
		this.executor = executor;
	}

	/* (non-Javadoc)
	 * @see com.async.SlowWorkerInteractorInterface#execute(int, com.async.AchoTaskCallback, java.util.concurrent.CountDownLatch)
	 */
	@Override
	public void execute(int workerId, AchoTaskCallback callback, CountDownLatch doneSignal) {
		this.workerId = workerId;

		this.callback = new WeakReference<AchoTaskCallback>(callback, AchoCallbackReferenceQueueProvider.getInstance());

		this.doneSignal = doneSignal;

		/** doing the scheduling from here **/
		executor.execute(this);
	}

	protected final boolean _run() {
		try {
			doneSignal.countDown();

			System.out.println("I am worker #" + this.workerId);
			System.out.println("==== working working, working ====== ");
			Thread.sleep(3000);
			System.out.println("==== working working, working ====== ");

		} catch (InterruptedException e) {
			System.out.println("We have an exception");
		}
		
		return true;
	}

	@Override
	public String getTaskId() {
		return "slow_worker";
	}
	
	public boolean equals(AchoTask other){
		return this.getTaskId().equals(other.getTaskId());
	}

}