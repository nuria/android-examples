package com.async;

public class PretendAchoMain {

	public static void main(String[] args) {

		System.out.println("starting....");

		PretendActivityAndSlowWorkerListener activity = new PretendActivityAndSlowWorkerListener();
		activity.doSomething();

	}

}
