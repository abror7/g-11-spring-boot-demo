package uz.pdp.springbootdemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootdemo.entity.Car;
import uz.pdp.springbootdemo.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
