package uz.pdp.springbootdemo.entity;

import lombok.*;
import uz.pdp.springbootdemo.entity.template.AbsEntity;
import uz.pdp.springbootdemo.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity(name = "products")
public class Product extends AbsNameEntity {

    private BigDecimal price;

    private String mainImgUrl;

    private Integer amountInStock;

    @ManyToMany
    private List<CharacteristicsChValues> characteristicsChValuesList;


}
