package uz.pdp.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootdemo.entity.Passport;
import uz.pdp.springbootdemo.entity.User;


public interface PassportRepo extends JpaRepository<Passport, Integer> {

}
