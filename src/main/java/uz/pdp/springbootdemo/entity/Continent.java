package uz.pdp.springbootdemo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "continents")
@Builder
public class Continent {

    @Id // primary key bo'lishi uchun
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sequence yaratib berishi uchun
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "continent", cascade = CascadeType.ALL)
    private List<Country> countryList;


}
