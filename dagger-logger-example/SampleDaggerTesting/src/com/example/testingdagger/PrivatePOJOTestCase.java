package com.example.testingdagger;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.dagger.simple.FakeActivity;
import com.example.dagger.simple.FakeApplication;
import com.example.secret.LoggerProvider;

/**
 * Regular unit test. Run as such, not as android unit test.
 * 
 * Make sure dagger and annotations are set up for this project:
 * http://www.thekeyconsultant.com/2013/09/adding-dagger-to-your-android-project.html
 *
 * @author nruiz
 *
 */
public class PrivatePOJOTestCase {

	private FakeApplication application;

	private FakeActivity activity;

	/**
	 * Let's pretend that Android is instantiating 
	 * our application and our app
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		application = new FakeApplication();
		activity = new FakeActivity();
		application.inject(activity);

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void TestSomething() {
		// if this logging works you should see this message on the screen when the test is executed
		// the graph we are using is the one on testing not the one on development 
		LoggerProvider.getLogger().d("TEST", "hola que tal");
		Assert.assertTrue(true);

	}

	@Test
	public void TestPublicDomainObjectGetsInjected() {

		Assert.assertTrue(activity.getBValue().equals("hola que tal soy private domain"));

	}


}
