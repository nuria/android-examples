package com.async;

/**
 * Tasks do not know about Runnable so we can decouple submission from execution
 * 
 */
public interface Task {

	public void run();

	/**
	 * Will return true if two tasks are identical
	 * 
	 * @return
	 */
	public boolean equals(Task other);

	/**
	 * This is the id to identify tasks of the same kind can be generated at
	 * runtime and it does not have to be a constant
	 * 
	 * @return
	 */
	public String getTaskId();

	/**
	 * Callback associated to this task Be careful that it is implemented as a
	 * weak reference See AchoTaskBase
	 * 
	 * @return
	 */
	public TaskCallback getCallback();
}
