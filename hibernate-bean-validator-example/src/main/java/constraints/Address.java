package constraints;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Address {

	@NotEmpty
	@Size(min = 2, max = 25)
	private String street;

	private String town;

	private String country;

	@PostCodeValidator(message = "This should be a PostCodeValiderImpl post code")
	private String postCode;

	@PostCodeValidator
	private String postCode2;
	
	public static void main(String[] args) {
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
	    Address address = new Address();
	    address.setStreet("Gold Avenue");
	    address.setPostCode("A15 1NP");
	    Set<ConstraintViolation<Address>> constraintViolations = validator.validate(address);
	    System.out.println(constraintViolations.size());

	    for(ConstraintViolation<Address> violation : constraintViolations){
	    	System.out.println(violation.getMessage());
	    }
	}
	public void setStreet(String street){
		this.street = street;
	}
	public void setPostCode(String post_code) {
		this.postCode = post_code;
	}

}