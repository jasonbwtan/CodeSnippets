package constraints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostCodeValidatorImpl implements ConstraintValidator<PostCodeValidator, String> {

	private static Pattern postCodePattern;

	/**
	 * Implementing this method is optional and is usually blank in example code. Use it to setup your
	 * constraint validator. In this case, I've created a Pattern object to test the post code.
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(PostCodeValidator constraintAnnotation) {

		if (postCodePattern == null) {
			postCodePattern = Pattern.compile("(b|B)[0-9]{1,2} [0-9]{1}[a-zA-Z]{2}");
		}
	}

	/**
	 * Use this method to test the constraint.
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 *      javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		boolean result = false;

		if (isNotNull(value)) {
			result = matchPostCode(value);
		}

		return result;
	}

	private static boolean isNotNull(Object obj) {

		return obj != null;
	}

	private boolean matchPostCode(String value) {

		Matcher matcher = postCodePattern.matcher(value);
		return matcher.matches();
	}
}