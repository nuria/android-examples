package com.example.android.ui;


import javax.inject.Inject;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.interfaces.Abc;
import com.example.interfaces.Logger;
import com.example.testingprivacy.R;

/**
 * Main activity access to domain objects via Dependency injector
 * 
 * To run this in eclipse make sure the three dagger jars are in your build and lib path
 * http://www.thekeyconsultant.com/2013/09/adding-dagger-to-your-android-project.html
 * 
 *
 */
public class MainActivity extends DemoBaseActivity {
	private TextView myText = null;

	@Inject
	Abc abc;

	@Inject
	Logger log;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myText = (TextView) findViewById(R.id.textView1);

		myText.setText(abc.getB());

		// cannot access to package private calls, it is located where it is injected.
		// PrivateDomain privateDomain = new PrivateDomain();
		// myText.append(privateDomain.getB());

		log.d("TEST", "logger message");


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

}
