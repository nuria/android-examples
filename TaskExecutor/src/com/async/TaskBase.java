package com.async;

import java.lang.ref.WeakReference;

/**
 * Base Class for acho tasks. It incorporates the notion of a callback Note it
 * asuumes all tasks want a callback but that might not be necessarily the case.
 * 
 * @author nuria
 * 
 */
public abstract class TaskBase implements Task {

	protected WeakReference<TaskCallback> callback;



	protected abstract boolean _run();



	/**
	 * Note that this might return null as reference to callback is stored like
	 * a WeakReference
	 */
	public TaskCallback getCallback() {
		return this.callback.get();
	}

	/**
	 * Very simple default implementation of logic to deduplicate tasks. If
	 * tasks ids are equal return true.
	 * Note that we could have the same task
	 * executed with different parameters in that cause you want to have those
	 * into account when writing your implementation of equals
	 */
	public boolean equals(Task other) {
		return this.getTaskId().equals(other.getTaskId());
	}

	public void run() {
		boolean success = this._run();

		/**
		 * If there was a GC collect of the listener no callback will be invoked
		 **/

		if (this.getCallback() != null) {
			if (success) {
				this.getCallback().onSucess(this.getTaskId());

			} else {
				this.getCallback().onFail(this.getTaskId());
			}
		}
	}
}