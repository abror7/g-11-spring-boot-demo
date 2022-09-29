package uz.pdp.springbootdemo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarByIdDto {

    private Integer id;

    @NotNull(message = "Brand tanlash shart!!!")
    private Integer brandId; // req

    private String brandName; // res

//    @NotEmpty(message = "Model nomi kiritilishi shart!!!")
    @NotBlank(message = "Model nomi kiritilishi shart!!! Hattoki probel ham mumkin emas!!!")
    private String model;


    //.....
    //.....
    //.....
    //.....

}
