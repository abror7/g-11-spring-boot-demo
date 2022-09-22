package uz.pdp.springbootdemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<AgeValid, LocalDate> {
    @Override
    public boolean isValid(LocalDate tugilganSanasi, ConstraintValidatorContext context) {
        return Period.between(tugilganSanasi, LocalDate.now()).getYears() >= 18;
    }
}
