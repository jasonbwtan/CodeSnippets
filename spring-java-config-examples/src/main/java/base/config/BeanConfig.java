package base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import base.model.Book;
import base.model.Engine;
import base.model.HelloWorld;
import base.model.HelloWorldImpl;
import base.model.Person;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
@ComponentScan(basePackages = "base.components")
public class BeanConfig {

	/* pulls property values. magically autowired */
    @Autowired
    private Environment env;
	
	@Bean(name = "helloBean")
	public HelloWorld helloWorld() {
		return new HelloWorldImpl();
	}
	
	@Bean(name = "personBean")
	public Person person(){
		Person person = new Person();
		person.setName(env.getRequiredProperty("person.name"));
		person.setEmail(env.getRequiredProperty("person.email"));
		return person;
	}
	
	@Bean(name = "bookBean")
	public Book book(){
		Book book = new Book();
		return book;
	}
	
	@Bean(name = "engineBean")
	public Engine engine(){
		Engine engine = new Engine();
		return engine;
	}
}
//@Profile("dev")
//System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
//ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
