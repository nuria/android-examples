package com.async;

public interface AchoExecutorService {

	/**
	 * Executes task async.
	 * 
	 * If we want to execute atask and an identical task exists on the queue it
	 * is not executed. AchoTasks incorporate a callback for notification of
	 * task completition
	 * 
	 * @param task
	 */
	public void execute(AchoTask task);

	/**
	 * Shutdown should get called if we are going into offline mode?
	 */
	public void shutdown();

}