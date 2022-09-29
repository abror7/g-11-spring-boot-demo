package uz.pdp.springbootdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "passports")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Passport {
    @Id // primary key bo'lishi uchun
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sequence yaratib berishi uchun
    private Integer id;

    private String serial;

    private String number;

    @OneToOne
    private User owner;

    //....
    //....
    //....
    //....

}

