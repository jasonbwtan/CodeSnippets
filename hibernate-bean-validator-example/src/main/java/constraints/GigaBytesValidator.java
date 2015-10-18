package constraints;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GigaBytesValidator implements ConstraintValidator<GigaBytes, List<String>> {
	private Pattern memoryFormat;
	
	@Override
	public void initialize(GigaBytes constraintAnnotation) {
//(b|B)[0-9]{1,2} [0-9]{1}[a-zA-Z]{2}
//		if (memoryFormat == null) {
			memoryFormat = Pattern.compile("[0-9]{1,4}GB");
//		}
	}

	@Override
	public boolean isValid(List<String> list, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(list != null){
			for(String memory : list){
				Matcher matcher = memoryFormat.matcher(memory);
				if( !matcher.matches()){
					return false;
				}
			}
		}
		return true;
	}
}
