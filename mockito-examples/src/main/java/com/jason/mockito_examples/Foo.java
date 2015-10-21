package com.jason.mockito_examples;

import java.sql.Connection;

public interface Foo {
	String HELLO_WORLD = "Hello World";
	String greet();
	String greet2();
	public Connection getConnection();
}