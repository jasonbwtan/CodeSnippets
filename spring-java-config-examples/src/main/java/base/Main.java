package base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import base.components.Calculator;
import base.components.Car;
import base.config.BeanConfig;
import base.model.HelloWorld;
import base.model.Person;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		HelloWorld obj = (HelloWorld) context.getBean("helloBean");
		obj.print("Spring4 Java Config");
		
		Person person = (Person) context.getBean("personBean");
		System.out.println(person.email);
		System.out.println(person.book);
		
		Calculator calculator = (Calculator) context.getBean("calculator");
		calculator.makeAnOperation();
		
		
	}
}
