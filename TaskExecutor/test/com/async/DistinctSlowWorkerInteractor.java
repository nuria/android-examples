package com.async;

import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;

public class DistinctSlowWorkerInteractor extends AchoTaskBase implements WorkerInteractorInterface {

	private int workerId;

	protected AchoExecutorService executor;

	private CountDownLatch doneSignal;



	public DistinctSlowWorkerInteractor(AchoExecutorService executor) {
		this.executor = executor;
	}

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

			System.out.println("==== working working, working ====== ");
		} catch (Exception e) {
			System.out.println("We have an exception");
		}
		//success every time
		return true;
	}

	@Override
	public String getTaskId() {
		return "slow_worker_equal";
	}

	public boolean equals(AchoTask other){
		return false;
	}
}