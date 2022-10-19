package uz.pdp.springbootdemo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailDto {

    private String to;
    private String subject;
    private String message;
    private String name;

}
