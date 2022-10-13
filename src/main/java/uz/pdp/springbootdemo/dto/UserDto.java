package uz.pdp.springbootdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import uz.pdp.springbootdemo.validation.AgeValid;
import uz.pdp.springbootdemo.validation.PasswordsMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@PasswordsMatch (message = "Baraka topkur parollar mos bo'lsin!!!")
public class UserDto {

    private Integer id;

    @NotBlank
    private String username;

    @NotBlank
    private String fullName;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

//    @Email //optional
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "You should enter your birthdate")
    @AgeValid
    private LocalDate birthDate;
}
