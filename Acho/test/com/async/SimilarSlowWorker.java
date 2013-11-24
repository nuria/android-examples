package com.async;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimilarSlowWorker extends AchoTaskBase implements AchoTask {

	private int workerId;

	public SimilarSlowWorker(AchoTaskCallback callback, int workerId) {
		super(callback);
		this.workerId = workerId;
	}

	protected final boolean _run() {
		try {
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