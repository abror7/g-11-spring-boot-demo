package uz.pdp.springbootdemo.entity;

import lombok.*;
import uz.pdp.springbootdemo.entity.enums.PermissionEnum;
import uz.pdp.springbootdemo.entity.enums.RoleEnum;
import uz.pdp.springbootdemo.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "permissions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Permission extends AbsEntity {

    @Enumerated(EnumType.STRING)
    private PermissionEnum name;
}
