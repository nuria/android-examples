package com.async;

import java.lang.ref.WeakReference;
import java.util.Random;

public class DistinctSlowWorker extends AchoTaskBase implements AchoTask {

	private int workerId;

	public DistinctSlowWorker(AchoTaskCallback callback, int workerId) {
		super(callback);
		this.workerId = workerId;
	}

	protected final boolean _run() {
		try {
			System.out.println("I am worker #" + this.workerId);
			System.out.println("==== working working, working ====== ");
			Thread.sleep(500);
			System.out.println("==== working working, working ====== ");
		} catch (InterruptedException e) {
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