package com.async;

import java.util.concurrent.CountDownLatch;

public interface WorkerInteractorInterface {

	public abstract void execute(int workerId, AchoTaskCallback callback, CountDownLatch doneSignal);

}