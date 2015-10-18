package constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GigaBytesValidator.class)
public @interface GigaBytes{

  String message() default "{gigabytes.constraint}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}