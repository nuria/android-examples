package com.async;

import java.util.concurrent.atomic.AtomicInteger;

/** Mock acho task implemented as s singleton **/
public class AchoTaskCallbackMock implements AchoTaskCallback {

	AtomicInteger successCount = new AtomicInteger();
	
	@Override
	public void onSucess(String taskId) {
		System.out.println("SUCCESS");
		successCount.addAndGet(1);
	}

	@Override
	public void onFail(String taskId) {
		// TODO Auto-generated method stub
		System.out.println("FAIL");
	}

	
	public int getSucessCount(){
		return successCount.get();
	}
}
