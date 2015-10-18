import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class Main {
	public static void main(String[] args) {
	      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	      Validator validator = factory.getValidator();
	      Car car = new Car( null, "DD-AB-123", 4 );

	      Set<ConstraintViolation<Car>> constraintViolations =
	      validator.validate( car );
	      System.out.println("number of violations:"+constraintViolations.size());
	}
}
