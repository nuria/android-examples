package com.async;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SlowWorker extends AchoTaskBase implements AchoTask {

	private int workerId;

	public SlowWorker(AchoTaskCallback callback, int workerId) {
		super(callback);
		this.workerId = workerId;
	}

	protected final boolean _run() {
		try {
			System.out.println("I am worker #" + this.workerId);
			System.out.println("==== working working, working ====== ");
			Thread.sleep(2000);
			System.out.println("==== working working, working ====== ");
		} catch (InterruptedException e) {
			System.out.println("We have an exception");
		}
		// let's return fail one out of ten
		Random r = new Random();

		if (r.nextInt(5) == 1) {
			return false;
		}
		return true;
	}

	@Override
	public String getTaskId() {
		return "slow_worker";
	}

}