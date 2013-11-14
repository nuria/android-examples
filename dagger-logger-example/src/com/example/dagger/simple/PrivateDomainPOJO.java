package com.example.dagger.simple;

import com.example.interfaces.Abc;
import com.example.secret.LoggerProvider;

class PrivateDomainPOJO implements Abc {





	@Override
	public int getA() {
		// TODO Auto-generated method stub
		return 23;
	}

	@Override
	public String getB() {
		// TODO Auto-generated method stub
		LoggerProvider.getLogger().d("TEST", "hola que tal");
		return "hola que tal soy private domain";
	}

	@Override
	public String[] getC() {
		// TODO Auto-generated method stub
		return new String[] { "1", "2" };
	}

	private int secret() {
		return 23;
	}

}

