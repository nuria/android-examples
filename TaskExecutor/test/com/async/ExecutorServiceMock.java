package com.async;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Pretend singleton instance of 
 * @author nuria
 *
 */

public class ExecutorServiceMock extends ExecutorServiceImpl implements ExecutorService {


	

	public ExecutorServiceMock(){
		super(CallbackReferenceQueueProvider.getInstance());
	}
	
	public DedupThreadPool getThreadPool(){
		return this.threadPool;
	}
	
	
	
	
}
