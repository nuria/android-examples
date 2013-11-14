package com.example.secret;

import javax.inject.Inject;

import com.example.interfaces.Logger;

public class LoggerProvider {

	@Inject
	static Logger logger;

	public LoggerProvider() {

	}

	public static Logger getLogger() {
		return logger;
	}
}
