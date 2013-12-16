package com.async;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * Very LOW TECH Concurrency Tests
 * @author nuria
 *
 */
public class DedupTaskExecutorTest {


	@BeforeClass
	public static void suiteSetup(){

	}

	/**
	 * Schedule 10 distinct tasks that should succeed
	 */
	@Test(timeout=10000)
	public void testSlowWorkerSuceedsTenTimes() {
		/**
		 * A CountDownLatch initialized to <em>N</em>
		* can be used to make one thread wait until <em>N</em> threads have
		* completed some action, or some action has been completed N times.
		 */
		CountDownLatch doneSignal;
		ExecutorServiceMock executor = new ExecutorServiceMock();


		//now do the same thing but with the acho thread pool
		int taskCount = 10;
		// how do we execute 

		doneSignal = new CountDownLatch(taskCount);
		DistinctSlowWorker task1 = new DistinctSlowWorker(executor);
		TaskCallbackMock callback = new TaskCallbackMock();

		for (int i = 0; i < taskCount; i++) {

			task1.execute(i, callback, doneSignal);
		}

		try {
			doneSignal.await(taskCount, TimeUnit.SECONDS);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		executor.shutdown();
		while(executor.getThreadPool().isTerminating()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //yuk, but what else?
		}
		Assert.assertEquals(executor.getThreadPool().getCompletedTaskCount(),taskCount);

		// all tasks should succeed
		Assert.assertEquals(callback.getSucessCount(), taskCount);

	}


	/**
	 * Schedule 10 tasks of which 5 are the same
	 * at least 6 should be executed. 
	 */
	@Test(timeout=10000)
	public void testSlowWorkerDuplicateScheduling() {
		/**
		 * A CountDownLatch initialized to <em>N</em>
		* can be used to make one thread wait until <em>N</em> threads have
		* completed some action, or some action has been completed N times.
		 */
		CountDownLatch distinctDoneSignal;

		CountDownLatch doneSignal;
		
		ExecutorServiceMock executor = new ExecutorServiceMock();


		//now do the same thing but with the acho thread pool
		int distinctTaskCount = 5;

		/** amount of duplicated tasks we are scheduling **/
		int sameTaskCount = 5;

		distinctDoneSignal = new CountDownLatch(distinctTaskCount);
		doneSignal = new CountDownLatch(sameTaskCount);

		DistinctSlowWorker task1 = new DistinctSlowWorker(executor);
		WorkerInteractorInterface task2 = new SimilarSlowWorker(executor);

		TaskCallbackMock callback = new TaskCallbackMock();

		for (int i = 0; i < distinctTaskCount; i++) {
			task1.execute(i, callback, doneSignal);
			task2.execute(i, callback, doneSignal);
		}

		try {
			distinctDoneSignal.await(distinctTaskCount, TimeUnit.SECONDS);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		executor.shutdown();
		
		while(executor.getThreadPool().isTerminating()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //yuk, but what else?
		}
		
		Assert.assertTrue(executor.getThreadPool().getCompletedTaskCount() > distinctTaskCount);
		Assert.assertTrue(executor.getThreadPool().getCompletedTaskCount() < distinctTaskCount + sameTaskCount);

	}

}
