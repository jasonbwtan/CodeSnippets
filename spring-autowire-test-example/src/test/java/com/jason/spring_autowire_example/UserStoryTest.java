package com.jason.spring_autowire_example;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import components.HelloService;

import beans.UserStory;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/beans.xml"})
public class UserStoryTest {

	@Autowired
	private UserStory userStory;

	@Autowired
	private HelloService helloService;
	
	@Test
	public void testAutowireBean() {
		assertEquals("User Story 1", userStory.name);
	}

	@Test
	public void testAutowireWithConstructorParam(){
		assertEquals("User Story 1 Constructor Arg",userStory.arg);
		assertEquals("SuperGenius User2",userStory.user2.getRole());
	}
	
	@Test
	public void testComponent(){
		assertEquals("Hello world!", helloService.sayHello());
	}
}
