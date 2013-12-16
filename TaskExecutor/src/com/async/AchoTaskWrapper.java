package com.async;


final class AchoTaskWrapper implements Runnable {

	AchoTask task;

	public AchoTaskWrapper(AchoTask task) {
		this.task = task;
	}

	@Override
	public void run() {
		this.task.run();

	}

	public AchoTask getTask() {
		return this.task;
	}

	}
