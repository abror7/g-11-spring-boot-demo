package uz.pdp.springbootdemo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface AgeValid {

    String message() default "You should be older than or equals to 18";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
