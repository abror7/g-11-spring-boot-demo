package uz.pdp.springbootdemo.entity;

import lombok.*;
import uz.pdp.springbootdemo.entity.template.AbsEntity;
import uz.pdp.springbootdemo.entity.template.AbsNameEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "characteristics")
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Characteristic extends AbsNameEntity{




}
