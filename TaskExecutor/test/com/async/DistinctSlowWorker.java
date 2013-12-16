package com.async;

import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;

public class DistinctSlowWorker extends TaskBase implements WorkerInteractorInterface {

	private int workerId;

	protected ExecutorService executor;

	private CountDownLatch doneSignal;



	public DistinctSlowWorker(ExecutorService executor) {
		this.executor = executor;
	}

	public void execute(int workerId, TaskCallback callback, CountDownLatch doneSignal) {
		this.workerId = workerId;

		this.callback = new WeakReference<TaskCallback>(callback, CallbackReferenceQueueProvider.getInstance());

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
		return "distinct_worker_equal";
	}

	/** Any two tasks of this type are distinct **/
	public boolean same(Task other){
		return false;
	}
}