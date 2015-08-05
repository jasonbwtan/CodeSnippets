package com.jason.service;

public class HelloWorldImpl extends HelloWorld {
	public HelloWorldImpl(String s) {
		// TODO Auto-generated constructor stub
		System.out.println(s);
	}
	
	@Override
	public void sayGoodbye(){
		System.out.println("goodbye");
	}
}
