package com.example.dagger.simple;

import javax.inject.Inject;

import com.example.interfaces.Abc;

import dagger.ObjectGraph;


/**
 * Mock Application.
 * The only thing it does is to inject dependencies
 * 
 *
 */
public class FakeApplication {

	@Inject
	Abc abc;

	private ObjectGraph graph;

	public FakeApplication() {
		Object[] testModules = new Object[] { new DomainTestModule() };

		graph = ObjectGraph.create(testModules);
		graph.injectStatics();
	}

	public void inject(Object o) {
		graph.inject(o);
	}
}
