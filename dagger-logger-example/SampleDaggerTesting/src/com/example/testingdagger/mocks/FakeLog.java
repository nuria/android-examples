package com.example.testingdagger.mocks;

import com.example.interfaces.Logger;

/** Does not do anything. Fake log to stub tests out **/
public class FakeLog implements Logger {


	
	private static class Singleton {
		static final Logger instance = new FakeLog();
	}

	public static Logger getLogger() {
		return Singleton.instance;
	}

	@Override
	public void v(String tag, String msg) {
		System.out.println(tag + msg);

	}

	@Override
	public void v(String tag, String msg, Throwable tr) {
		System.out.println(tag + msg);
	}

	@Override
	public void d(String tag, String msg) {
		System.out.println(tag + msg);
	}

	@Override
	public void d(String tag, String msg, boolean report) {
		System.out.println(tag + msg);
	}

	@Override
	public void d(String tag, String msg, Throwable tr) {
		System.out.println(tag + msg);
	}

	@Override
	public void d(String tag, String msg, Throwable tr, boolean report) {
		// TODO Auto-generated method stub

	}

	@Override
	public void i(String tag, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void i(String tag, String msg, boolean report) {
		// TODO Auto-generated method stub

	}

	@Override
	public void i(String tag, String msg, Throwable tr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void i(String tag, String msg, Throwable tr, boolean report) {
		// TODO Auto-generated method stub

	}

	@Override
	public void e(String tag, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void e(String tag, String msg, boolean report) {
		// TODO Auto-generated method stub

	}

	@Override
	public void e(String tag, String msg, Throwable tr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void e(String tag, String msg, Throwable tr, boolean report) {
		// TODO Auto-generated method stub

	}

	@Override
	public void w(String tag, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void w(String tag, String msg, boolean report) {
		// TODO Auto-generated method stub

	}

	@Override
	public void r(String tag, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void r(String tag, String msg, Throwable tr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void p(String tag, long time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void p(String tag, String msg, long time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pt(String tag, String msg, long time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tr(String tag, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCalledFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVerboseEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getCallStack() {
		// TODO Auto-generated method stub
		return null;
	}

}
