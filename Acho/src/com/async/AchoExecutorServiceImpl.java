package com.async;

import java.lang.ref.ReferenceQueue;
/**
 * Decoupling submission from execution.
 * 
 * The internal threadpool and runnable tasks are not accessible directly
 * 
 * TODO, what happens when we loose connectivity and go, say, into airplane
 * mode?
 * 
 * @author nuria
 * 
 */
public class AchoExecutorServiceImpl implements AchoExecutorService {

	final AchoThreadPool threadPool;

	public AchoExecutorServiceImpl(ReferenceQueue<AchoTaskCallback> referenceQueue) {
		this.threadPool = new AchoThreadPool(referenceQueue);

	}

	/**
	 * Executes task, if an equal task is already scheduled it does not do
	 * anything.
	 * 
	 */
	@Override
	public void execute(AchoTask task) {
		this.threadPool.execute(task);
	}

	/*
	 * When would this be called?
	 */
	@Override
	public void shutdown() {
		this.threadPool.shutdown();
	}




}
