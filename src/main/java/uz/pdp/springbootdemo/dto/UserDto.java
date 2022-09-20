package uz.pdp.springbootdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springbootdemo.validation.PasswordsMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    // TODO: 20/09/22 validate user's age
//    private LocalDate birthDate; use formatter
}
