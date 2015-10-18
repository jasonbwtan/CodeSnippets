package constraints;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class UrlMapping {
	
	@GigaBytes
	public List<String> memory;
	
	public void setMemory(List<String> memory){
		this.memory = memory;
	}
	
	public static void main(String[] args) {
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
	    UrlMapping mapping = new UrlMapping();
	    mapping.setMemory(Arrays.asList("11111GB","2GB","3GB"));
	    Set<ConstraintViolation<UrlMapping>> constraintViolations = validator.validate(mapping);
	    System.out.println(constraintViolations.size());

	    for(ConstraintViolation<UrlMapping> violation : constraintViolations){
	    	System.out.println(violation.getMessage());
	    }
	}
}
