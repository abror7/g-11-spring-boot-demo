package uz.pdp.springbootdemo.entity;

import lombok.*;
import uz.pdp.springbootdemo.entity.template.AbsEntity;

import javax.persistence.*;

@Entity(name = "ch_values")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class CharacteristicValue extends AbsEntity {

    private String value;




}
