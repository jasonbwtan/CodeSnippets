package com.jason.spring_autowire_example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.TextEditor;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

		TextEditor te = (TextEditor) context.getBean("textEditor");
		
		te.spellCheck();
	}
}