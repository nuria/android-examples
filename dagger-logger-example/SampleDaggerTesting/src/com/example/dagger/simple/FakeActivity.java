package com.example.dagger.simple;

import javax.inject.Inject;

import com.example.interfaces.Abc;

/**
 * Does not do anything
 * Proves the point that an object gets injected here 
 *
 *
 */
public class FakeActivity {

	@Inject
	Abc abc;

	/**
	 * Wrapper to injected object
	 * @return
	 */
	public String getBValue() {
		return abc.getB();
	}

}
