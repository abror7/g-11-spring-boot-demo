package uz.pdp.springbootdemo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "countries")
@Builder
public class Country {

    @Id // primary key bo'lishi uchun
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sequence yaratib berishi uchun
    private Integer id;

    private String name;

    @ManyToOne
    private Continent continent;



}
