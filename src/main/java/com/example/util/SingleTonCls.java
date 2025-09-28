package com.example.util;

public class SingleTonCls {

	private static volatile SingleTonCls instance = null;
	
	private SingleTonCls() {}
	
	public static SingleTonCls getInstance() {
		if(instance == null) {
			// To make thread safe
            synchronized (SingleTonCls.class)
            {
                // check again as multiple threads
                // can reach above step
                if (instance == null)
                	instance = new SingleTonCls();
            }
		}
		return instance;
	}
	
	public static void staticMethDisplay() {
		System.out.println("print static display method in singleton class.");
	}
	
	public void normalMethDisplay() {
		System.out.println("print normal display method in singleton class.");
	}
}
