package base.components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("calculator")
public class Calculator {
 private Adder adder;

 @Autowired
 public void setAdder(Adder adder) {
     this.adder = adder;
 }

 public void makeAnOperation(){
     int r1 = adder.add(1,2);
     System.out.println("r1 = " + r1);
 }
}

