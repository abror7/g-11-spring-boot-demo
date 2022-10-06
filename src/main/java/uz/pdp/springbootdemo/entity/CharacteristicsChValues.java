package uz.pdp.springbootdemo.entity;

import lombok.*;
import uz.pdp.springbootdemo.entity.template.AbsEntity;
import uz.pdp.springbootdemo.entity.template.AbsNameEntity;

import javax.persistence.*;

@Entity(name = "characteristics_ch_values")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"characteristic_id", "characteristic_value_id"})
})
public class CharacteristicsChValues extends AbsEntity {

    @ManyToOne
    private Characteristic characteristic;

    @ManyToOne
    private CharacteristicValue characteristicValue;




}
