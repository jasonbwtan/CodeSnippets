package base.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import base.model.Engine;

@Component("car")
public class Car {
	Engine engine;
	
	@Autowired
	public void setEngine(Engine engine){
		this.engine = engine;
		System.out.println("car engine set");

	}
}
