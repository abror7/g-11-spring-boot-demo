package uz.pdp.springbootdemo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarDto {

    private Integer id;

    @NotNull(message = "Brand tanlash shart!!!")
    private Integer brandId; // req

    private String brandName; // res

//    @NotEmpty(message = "Model nomi kiritilishi shart!!!")
    @NotBlank(message = "Model nomi kiritilishi shart!!! Hattoki probel ham mumkin emas!!!")
    private String model;

    private String description;

//      You can also use pattern annotation for custom validation such as regex
//    @Pattern(regexp = "^()", message = "Email yoz baraka topgur...")
//    private String email;

}
