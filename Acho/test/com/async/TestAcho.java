package com.async;

import static org.junit.Assert.fail;

import java.lang.ref.WeakReference;

import org.junit.*;

/**
 * Very LOW TECH Concurrency Tests
 * @author nuria
 *
 */
public class TestAcho {


	@BeforeClass
	public static void suiteSetup(){

	}

	/**
	 * Schedule 10 distinct tasks that should succeed
	 */
	@Test(timeout=10000)
	public void testSlowWorkerSuceedsTenTimes() {
		AchoExecutorServiceWrapper executor = new AchoExecutorServiceWrapper();

		AchoTaskCallbackMock callback = new AchoTaskCallbackMock();
		//now do the same thing but with the acho thread pool
		long taskCount =10;
		// how do we execute 

		for (int i = 0; i < taskCount; i++) {
			executor.execute(new DistinctSlowWorker(callback, i));
		}

		while(!executor.getThreadPool().getQueue().isEmpty()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //yuk, but what else?

		}


		executor.shutdown();
		while(executor.getThreadPool().isTerminating()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //yuk, but what else?
		}
		Assert.assertEquals(executor.getThreadPool().getCompletedTaskCount(),taskCount);

		// all tasks should succeed
		Assert.assertEquals(callback.getSucessCount(),taskCount);

	}


	/**
	 * Schedule 10 tasks of which 5 are the same
	 * only 6 should be executed
	 */
	@Test(timeout=10000)
	public void testSlowWorkerDuplicateScheduling() {
		AchoExecutorServiceWrapper executor = new AchoExecutorServiceWrapper();

		AchoTaskCallbackMock callback = new AchoTaskCallbackMock();
		//now do the same thing but with the acho thread pool
		long taskCount = 6;
		// how do we execute 

		for (int i = 0; i < taskCount; i++) {
			executor.execute(new DistinctSlowWorker(callback, i));
			executor.execute(new SimilarSlowWorker(callback, i));
		}

		while(!executor.getThreadPool().getQueue().isEmpty()){
			try {
				Thread.sleep(1000); //yuk, but what else?
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}


		executor.shutdown();
		
		while(executor.getThreadPool().isTerminating()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //yuk, but what else?
		}
		
		Assert.assertEquals(executor.getThreadPool().getCompletedTaskCount(),taskCount);

		// all tasks should succeed
		Assert.assertEquals(callback.getSucessCount(),taskCount);

	}

}
