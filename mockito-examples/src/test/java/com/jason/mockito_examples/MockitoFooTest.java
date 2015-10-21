package com.jason.mockito_examples;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;

import javax.rmi.CORBA.Stub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class MockitoFooTest {

	@Mock
	Foo foo;

	Connection con;
	
	@Test
	public void fooGreets() {
		when(foo.greet()).thenReturn("hi there");
		assertTrue(foo.greet().equals("hi there"));
	}
	
	@Test
	public void altfooGreets(){
		//same as using when (i prefer when)
		stub(foo.greet2()).toReturn("Yeah Boi");
		//verify that foo was called 0 time
		verify(foo, times(0)).greet2();
		
		foo.greet2();
		
		//verify that foo was called
		verify(foo).greet2();
		verify(foo, times(1)).greet2();

		
		System.out.println(foo.greet2());
	}
	@Test
	public void testGetConnection(){
		//you can potentially create your own impl and set as return e.g DB
		when(foo.getConnection()).thenReturn(con);
	}
}