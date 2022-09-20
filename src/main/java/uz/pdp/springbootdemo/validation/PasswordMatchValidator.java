package uz.pdp.springbootdemo.validation;

import uz.pdp.springbootdemo.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordsMatch, UserDto> {
    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {

        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }
}
