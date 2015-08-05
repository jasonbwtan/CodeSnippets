package com.jason;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

import com.jason.config.AppConfig;
import com.jason.service.HelloWorld;

public class App {

	public static void main(String[] args) {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	    HelloWorld obj2 = (HelloWorld) context.getBean("helloBean");
	    obj2.sayGoodbye();
		((ConfigurableApplicationContext) context).close();

	}
}