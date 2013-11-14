package com.example.testingprivacy;

import com.example.interfaces.Abc;

public class PublicDomainPOJO implements Abc {

	private int a;
	private String b = " I am public domain";
	private String[] c;

	/* (non-Javadoc)
	 * @see com.example.testingprivacy.Abc#getA()
	 */
	@Override
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	/* (non-Javadoc)
	 * @see com.example.testingprivacy.Abc#getB()
	 */
	@Override
	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	/* (non-Javadoc)
	 * @see com.example.testingprivacy.Abc#getC()
	 */
	@Override
	public String[] getC() {
		return c;
	}

	public void setC(String[] c) {
		this.c = c;
	}
}
