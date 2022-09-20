package uz.pdp.springbootdemo.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PasswordsMatch {

    String message() default "Parollar mos emas!!!"; //"Password do not match!!!"

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
