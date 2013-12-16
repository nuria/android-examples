package com.async;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.LinkedHashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class DedupThreadPool extends ThreadPoolExecutor {

	private static final int DEFAULT_THREAD_COUNT = 3;

	private static final int DEFAULT_QUEUE_CAPACITY = 20;

	private final CleanupThread cleanupThread;

	/** Set to help us not to schedule duplicate petitions **/
	/**
	 * Set to help us cancel petitions to callbacks that might have been
	 * garbage collected
	 **/
	private final Set<TaskWrapper> dedupingTaskList;

	/**
	 * A LinkedBlockingQueue is an optionally-bounded blocking queue based
	 * on linked nodes This queue orders elements FIFO (first-in-first-out).
	 * Size of the queue and size of the pool must be tunned together
	 * 
	 * Queue has no priorities but the threadpool could be implemented with
	 * a priority queue In that case make sure you do not use a discard
	 * oldest rejection policy
	 * 
	 * Will be constructed vi a DI, it is really a singleton
	 * 
	 * It is constructed on a singlethreaded context
	 */
	public DedupThreadPool(ReferenceQueue<TaskCallback> referenceQueue) {
		super(DEFAULT_THREAD_COUNT, DEFAULT_THREAD_COUNT, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(
						DEFAULT_QUEUE_CAPACITY), new TaskThreadFactory());

		/**
		 * If size of queue is exceeded a RejectExecutionException will be
		 * thrown There are other policies available like discard oldest but
		 * the default one seems a good start.
		 */
		this.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

		
		this.dedupingTaskList = Collections.synchronizedSet(new LinkedHashSet<TaskWrapper>());
		this.cleanupThread = new CleanupThread(referenceQueue);
		this.cleanupThread.start();

	}

	/**
	 * Executes task, if an equal task is already scheduled it does not do
	 * anything.
	 * 
	 * If we used a priority queue we could bump up the priority but keeping
	 * things simple for now.
	 * 
	 * if it is not a duplicate it wraps AchoTask into a Runnable for
	 * execution
	 * 
	 * Decoupling submission from execution
	 * 
	 * This method may be called on a multithreaded context
	 * 
	 * @param task
	 */
	public void execute(Task task) {
		if (this.isDuplicateTask(task)) {

			return;
		}
		

		synchronized (this) {
			// rather than using the objects equals method
			// we will use the task same method to see if this task has already
			// been scheduled
			
			if (this.isDuplicateTask(task)) {
				return;
			}
			TaskWrapper wrapper = new TaskWrapper(task);
			this.dedupingTaskList.add(wrapper);
			this.execute(wrapper);
		}

		
	}

	/**
	 * Returns true if task is already on the queue
	 * 
	 * @param task
	 * @return This method may be called on a multithreaded context
	 * 
	 */
	private boolean isDuplicateTask(Task task) {

		if (!this.dedupingTaskList.isEmpty()){
			Iterator<TaskWrapper> it  = this.dedupingTaskList.iterator();
			while(it.hasNext()) {
				Task otherTask = it.next().getTask();
				if (task.same(otherTask)){
					// do not do anything this task is alredy scheduled
					// now becareful when implementing this "same" method
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * TODO Add variable pool size depending on strength of connection
	 * 'Borrow' idea from Picasso Adjust thread count depending on
	 * connection
	 * https://github.com/square/picasso/blob/master/picasso/src/main
	 * /java/com/squareup/picasso/PicassoExecutorService.java
	 * **/

	void adjustThreadCount() {
		setThreadCount(DEFAULT_THREAD_COUNT);
	}

	private void setThreadCount(int threadCount) {
		setCorePoolSize(threadCount);
		setMaximumPoolSize(threadCount);
	}

	/**
	 * Remove task from queue when its callback was collected. 
	 * 
	 * Called so far in a single threaded context as
	 * it is called only from cleanup thread
	 * 
	 * @param callback
	 */
	private void removeTask(TaskCallback callback) {
		
		if (!this.dedupingTaskList.isEmpty()) {
			Iterator<TaskWrapper> it = dedupingTaskList.iterator();
			while (it.hasNext()) {
				TaskWrapper wrapper = it.next();
				// is there a better thing to compare here than equals?
				// TODO maybe callbacks should have a id to identify them further
				if (wrapper.getTask().getCallback().equals(callback)){
					dedupingTaskList.remove(wrapper);
				}
				
			}
		}

	}

	/**
	 * After execution of a task, make sure to remove it from dedup set
	 * 
	 * This method is invoked by the thread that executed the task. It is
	 * being called on a multithreaded context
	 */
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		TaskWrapper wrapper = (TaskWrapper) r;
		this.dedupingTaskList.remove(wrapper);

	}



	/**
	 * Note this class is not static thus keeps a reference to its parent
	 * class
	 **/
	private class CleanupThread extends Thread {
		private final ReferenceQueue<TaskCallback> referenceQueue;

		CleanupThread(ReferenceQueue<TaskCallback> referenceQueue) {
			this.referenceQueue = referenceQueue;

			setDaemon(true);
			setName("refQueue");
		}

		@Override
		public void run() {

			// TODO set android thread priority
			// Process.setThreadPriority(THREAD_PRIORITY_BACKGROUND);
			while (true) {
				try {
					java.lang.ref.Reference<? extends TaskCallback> toRemove = referenceQueue.remove();
					if (toRemove.get() != null) {
						// TODO send message to thread pool executor to
						// clean up the runnable via handler
						removeTask(toRemove.get());
					}
				} catch (InterruptedException e) {
					break;
				} catch (final Exception e) {
					// TODO who do we notify of this?
					break;
				}
			}
		}

	}
}