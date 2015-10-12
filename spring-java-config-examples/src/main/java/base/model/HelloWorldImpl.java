package base.model;

public class HelloWorldImpl implements HelloWorld {

	@Override
	public void print(String string) {
		System.out.println("Called successfully: "+string);
	}

}
