package com.async;


final class TaskWrapper implements Runnable {

	Task task;

	public TaskWrapper(Task task) {
		this.task = task;
	}

	@Override
	public void run() {
		this.task.run();

	}

	public Task getTask() {
		return this.task;
	}
	
	

}
