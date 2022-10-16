package uz.pdp.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootdemo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
