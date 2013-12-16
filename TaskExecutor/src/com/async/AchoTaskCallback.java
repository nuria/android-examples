package com.async;

/**
 * Receives notification of completition of an acho task
 * 
 * A callback might receive notifications of different tasks thus it should be
 * able to identify them. It does so via an identifing string
 * 
 * @author nuria
 * 
 */
public interface AchoTaskCallback {

	public abstract void onSucess(String taskId);

	public abstract void onFail(String taskId);

}