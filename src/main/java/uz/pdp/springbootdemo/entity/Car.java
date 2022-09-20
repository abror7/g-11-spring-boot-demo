package uz.pdp.springbootdemo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "cars")
@Builder
public class Car {

    @Id // primary key bo'lishi uchun
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sequence yaratib berishi uchun
    private Integer id;

    @ManyToOne
    private Brand brand;

//    @Column(nullable = false) // null qiymatlarni qabul qilmasligi uchun
//    private String brand;

    @Column(nullable = false) // null qiymatlarni qabul qilmasligi uchun
    private String model;

    @Column(columnDefinition = "text")
    private String description;

    //....


}
