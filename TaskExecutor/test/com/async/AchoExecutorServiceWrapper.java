package com.async;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Pretend singleton instance of 
 * @author nuria
 *
 */

public class AchoExecutorServiceWrapper extends AchoExecutorServiceImpl implements AchoExecutorService {


	

	public AchoExecutorServiceWrapper(){
		super(AchoCallbackReferenceQueueProvider.getInstance());
	}
	
	public AchoThreadPool getThreadPool(){
		return this.threadPool;
	}
	
	
	
	
}
