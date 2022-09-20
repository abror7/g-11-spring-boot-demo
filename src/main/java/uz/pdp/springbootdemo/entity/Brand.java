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
@Entity(name = "brands")
@Builder
public class Brand {
    @Id // primary key bo'lishi uchun
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sequence yaratib berishi uchun
    private Integer id;

    private String name;

    private String description;

    private String ownerFullName;

    @OneToMany(mappedBy = "brand")
    private List<Car> carList;

//    @ManyToOne
//    private Country country;
}
